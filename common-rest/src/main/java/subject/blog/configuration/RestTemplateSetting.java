package subject.blog.configuration;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import subject.blog.utils.RestApiHandler;

@Configuration
public class RestTemplateSetting {

    @Bean
    public RestTemplate generalRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestApiHandler());
        restTemplate.getMessageConverters()
            .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
