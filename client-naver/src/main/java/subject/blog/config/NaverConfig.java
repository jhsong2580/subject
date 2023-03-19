package subject.blog.config;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "naver")
public class NaverConfig {

    @NotBlank
    private final String clientId;

    @NotBlank
    private final String clientSecret;

    @NotBlank
    private final String host;

    @NotNull
    private final EndPoints endPoints;

    private String blogListURL;

    @PostConstruct
    public void init() {
        blogListURL = this.host + this.endPoints.blogListUri;
    }

    public String getBlogListURL() {
        return this.blogListURL;
    }

    @Getter
    @AllArgsConstructor
    @ConstructorBinding
    private static class EndPoints {

        @NotBlank
        private String blogListUri;
    }
}
