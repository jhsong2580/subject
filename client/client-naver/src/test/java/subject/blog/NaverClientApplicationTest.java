package subject.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = NaverSetting.class,
    properties = "spring.config.location=" +
        "classpath:/application-naver-test.yml"
)
public class NaverClientApplicationTest {

    @Test
    void contextLoads() {

    }
}
