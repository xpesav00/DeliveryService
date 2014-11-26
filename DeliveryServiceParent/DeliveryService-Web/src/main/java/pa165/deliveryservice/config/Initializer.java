package pa165.deliveryservice.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.jsp.jstl.core.Config;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Replaces web.xml file, initializes Spring MVC dispatcher servlet, that in turn uses MySpringMvcConfig class
 * for Spring MVC configuration.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //create Spring beans context configured in MySpringMvcConfig.class
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MySpringMvcConfig.class);

        //register Spring MVC main Dispatcher servlet
        ServletRegistration.Dynamic disp = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        disp.setLoadOnStartup(1);
        disp.addMapping("/");

        //register filter setting utf-8 encoding on all requests
        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding", CharacterEncodingFilter.class);
        encoding.setInitParameter("encoding", "utf-8");
        encoding.addMappingForUrlPatterns(null, false, "/*");

        //register bundle also for JSTL fmt: tags which are not behind DispatcherServlet
        servletContext.setInitParameter(Config.FMT_LOCALIZATION_CONTEXT,"Texts");
    }
}
