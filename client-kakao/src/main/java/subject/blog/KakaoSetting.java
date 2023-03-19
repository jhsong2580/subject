package subject.blog;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import subject.blog.config.KakaoConfig;

@ComponentScan
@Component
@EnableConfigurationProperties({KakaoConfig.class})
public class KakaoSetting {

}
