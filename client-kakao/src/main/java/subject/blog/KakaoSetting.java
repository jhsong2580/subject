package subject.blog;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import subject.blog.config.KakaoConfig;

@ComponentScan
@EnableConfigurationProperties({KakaoConfig.class})
public class KakaoSetting {

}
