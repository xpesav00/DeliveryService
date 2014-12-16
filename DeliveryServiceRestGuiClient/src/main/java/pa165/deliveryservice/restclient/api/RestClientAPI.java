package pa165.deliveryservice.restclient.api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Drimal
 */
public class RestClientAPI {
    protected String url;
    protected String action;
    protected RestTemplate rt ;
    protected List<HttpMessageConverter<?>> list;
    protected HttpHeaders headers;
    protected URI uri;
    
    public RestClientAPI(String url) {
       
        rt = new RestTemplate();
        list = new ArrayList<HttpMessageConverter<?>>();
        list.add(new MappingJacksonHttpMessageConverter());
        rt.setMessageConverters(list);
        headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        this.url = url;
    }
    public RestTemplate getRt() {
        return rt;
    }

    public void setRt(RestTemplate rt) {
        this.rt = rt;
    }

    public List<HttpMessageConverter<?>> getList() {
        return list;
    }

    public void setList(List<HttpMessageConverter<?>> list) {
        this.list = list;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
   
   
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
   
        public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
