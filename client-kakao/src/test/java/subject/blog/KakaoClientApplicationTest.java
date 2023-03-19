package subject.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = KakaoSetting.class,
    properties = "spring.config.location=" +
        "classpath:/application-kakao-test.yml"
)
public class KakaoClientApplicationTest {

    @Test
    void contextLoads() {

    }
}
