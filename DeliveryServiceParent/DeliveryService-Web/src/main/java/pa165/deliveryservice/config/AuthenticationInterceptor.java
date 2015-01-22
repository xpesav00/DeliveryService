package pa165.deliveryservice.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import pa165.deliveryservice.api.dto.UserDto;

/**
 * Checks if user is authenticated. If not, redirect to login page.
 *
 * @author Martin Nekula
 */
@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (!uri.endsWith("login")) {
            UserDto userData = (UserDto) request.getSession().getAttribute("LOGGEDIN_USER");
            if (userData == null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }
}
