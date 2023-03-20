package subject.blog.acceptance.utils.assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;

public class BlogAssertionUtils {

    public static void 블로그_컨텐츠_조회됨(ExtractableResponse<Response> 응답, String... 블로그들의_컨텐츠) {
        String[] contents = 응답.jsonPath()
            .getList("documents.contents")
            .toArray(new String[0]);

        //then
        assertAll(
            () -> assertThat(응답.statusCode())
                .isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(contents)
                .containsExactlyInAnyOrder(블로그들의_컨텐츠)
        );
    }
}
