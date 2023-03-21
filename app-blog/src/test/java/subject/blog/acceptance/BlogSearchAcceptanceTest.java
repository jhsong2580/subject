package subject.blog.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static subject.blog.acceptance.utils.assertions.BlogAssertionUtils.블로그_컨텐츠_조회됨;
import static subject.blog.acceptance.utils.request.BlogRestUtils.Blog_정보조회_요청하기;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpStatus;
import subject.blog.enums.Sort;

public class BlogSearchAcceptanceTest extends AcceptanceTest {


    @BeforeEach
    public void init() {
        super.init();
    }

    @AfterEach
    public void destroy() {
        super.destroy();
    }

    @Test
    public void 카카오를통한_블로그_조회() {
        //given
        카카오_블로그내역조회_성공_MockServer_구동();
        네이버_블로그내역조회_성공_MockServer_구동();

        //when
        ExtractableResponse<Response> 응답 = Blog_정보조회_요청하기("query", 10, 1, Sort.ACCURACY);

        //then
        블로그_컨텐츠_조회됨(응답,
            10, 1,
            "kakaoContent1",
            "kakaoContent2",
            "kakaoContent3",
            "kakaoContent4",
            "kakaoContent5"
        );
    }

    @Test
    public void 네이버를통한_블로그_조회() {
        //given
        카카오_블로그내역조회_실패_MockServer_구동();
        네이버_블로그내역조회_성공_MockServer_구동();

        //when
        ExtractableResponse<Response> 응답 = Blog_정보조회_요청하기("query", 10, 1, Sort.ACCURACY);

        //then
        블로그_컨텐츠_조회됨(응답,
            10, 1,
            "naverContent1",
            "naverContent2",
            "naverContent3",
            "naverContent4",
            "naverContent5");
    }

    @Test
    public void 카카오_네이버_전부_장애일때_블로그_조회() {
        //given
        카카오_블로그내역조회_실패_MockServer_구동();
        네이버_블로그내역조회_실패_MockServer_구동();

        //when
        ExtractableResponse<Response> 응답 = Blog_정보조회_요청하기("query", 10, 1, Sort.ACCURACY);

        //then
        assertAll(
            () -> assertThat(응답.statusCode()).isEqualTo(
                HttpStatus.INTERNAL_SERVER_ERROR.value())
        );
    }

    @DisplayName("page size는 10~50 사이의 값이다.")
    @ParameterizedTest
    @CsvSource(value = {
        "9:400",
        "10:200",
        "15:200",
        "50:200",
        "51:400"
    }, delimiter = ':')
    public void 블로그조회_사이즈제한_테스트(int size, int httpStatusCode) {
        //given
        카카오_블로그내역조회_성공_MockServer_구동();

        //when
        ExtractableResponse<Response> 응답 = Blog_정보조회_요청하기("query", size, 1, Sort.ACCURACY);

        //then
        assertThat(응답.statusCode()).isEqualTo(httpStatusCode);
    }

    @DisplayName("page 번호는 1~50 사이의 값이다.")
    @ParameterizedTest
    @CsvSource(value = {
        "0:400",
        "1:200",
        "15:200",
        "50:200",
        "51:400"
    }, delimiter = ':')
    public void 블로그조회_페이지제한_테스트(int page, int httpStatusCode) {
        //given
        카카오_블로그내역조회_성공_MockServer_구동();

        //when
        ExtractableResponse<Response> 응답 = Blog_정보조회_요청하기("query", 10, page, Sort.ACCURACY);

        //then
        assertThat(응답.statusCode()).isEqualTo(httpStatusCode);
    }
}

