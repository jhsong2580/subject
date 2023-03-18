package subject.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import subject.blog.utils.RestApiHandler;

@Configuration
public class RestTemplateSetting {

    @Bean
    public RestTemplate generalRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestApiHandler());

        return restTemplate;
    }
}
