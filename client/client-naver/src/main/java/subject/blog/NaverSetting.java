package subject.blog;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import subject.blog.config.NaverConfig;

@ComponentScan
@Component
@EnableConfigurationProperties({NaverConfig.class})
public class NaverSetting {

}
