package subject.blog.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static subject.blog.acceptance.utils.assertions.BlogAssertionUtils.블로그_컨텐츠_조회됨;
import static subject.blog.acceptance.utils.request.BlogRestUtils.Blog_정보조회_요청하기;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import subject.blog.acceptance.utils.MockSettingDTO;
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

    private void 카카오_블로그내역조회_성공_MockServer_구동() {
        MockSettingDTO kakaoGetBlogListSetting = new MockSettingDTO(super.kakaoBlogListEndpoint,
            HttpMethod.GET,
            HttpStatus.OK,
            "{\n"
                + "    \"documents\": [\n"
                + "        {\n"
                + "            \"blogname\": \"kakaoBlogname1\",\n"
                + "            \"contents\": \"kakaoContent1\",\n"
                + "            \"datetime\": \"2023-03-02T21:42:49.000+09:00\",\n"
                + "            \"thumbnail\": \"kakaoThumbnail1\",\n"
                + "            \"title\": \"CSPNet <b>Review</b>\",\n"
                + "            \"url\": \"http://dlgari33.tistory.com/9\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"blogname\": \"kakaoBlogname2\",\n"
                + "            \"contents\": \"kakaoContent2\",\n"
                + "            \"datetime\": \"2023-03-09T11:00:47.000+09:00\",\n"
                + "            \"thumbnail\": \"kakaoThumbnail2\",\n"
                + "            \"title\": \"[Book <b>Review</b> : 자기 계발서] 인생 독해 _ 유수연\",\n"
                + "            \"url\": \"http://hui2sisters.tistory.com/20\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"blogname\": \"kakaoBlogname3\",\n"
                + "            \"contents\": \"kakaoContent3\",\n"
                + "            \"datetime\": \"2023-03-02T12:00:32.000+09:00\",\n"
                + "            \"thumbnail\": \"kakaoThumbnail3\",\n"
                + "            \"title\": \"Doong&#39;s <b>REVIEW</b> - &lt;루클라비&gt;\",\n"
                + "            \"url\": \"http://doong-s.tistory.com/359\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"blogname\": \"kakaoBlogname4\",\n"
                + "            \"contents\": \"kakaoContent4\",\n"
                + "            \"datetime\": \"2023-02-13T20:00:13.000+09:00\",\n"
                + "            \"thumbnail\": \"kakaoThumbnail4\",\n"
                + "            \"title\": \"[3DReconstruction] 02.meshroom tutorial <b>review</b>(2)\",\n"
                + "            \"url\": \"http://learn-and-give.tistory.com/68\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"blogname\": \"kakaoBlogname5\",\n"
                + "            \"contents\": \"kakaoContent5\",\n"
                + "            \"datetime\": \"2023-03-15T11:00:47.000+09:00\",\n"
                + "            \"thumbnail\": \"kakaoThumbnail5\",\n"
                + "            \"title\": \"[Book <b>Review</b> : 자기 계발서] 베스트 플레이어 _ 매슈 사이드\",\n"
                + "            \"url\": \"http://hui2sisters.tistory.com/23\"\n"
                + "        }\n"
                + "    ],\n"
                + "    \"meta\": {\n"
                + "        \"is_end\": false,\n"
                + "        \"pageable_count\": 800,\n"
                + "        \"total_count\": 1034604\n"
                + "    }\n"
                + "}"
        );
        super.addMockResponse(Arrays.asList(kakaoGetBlogListSetting), super.kakaoMockPort);
    }

    private void 네이버_블로그내역조회_성공_MockServer_구동() {
        MockSettingDTO naverGetBlogListSetting = new MockSettingDTO(super.naverBlogListEndpoint,
            HttpMethod.GET,
            HttpStatus.OK,
            "{\n"
                + "    \"lastBuildDate\": \"Mon, 20 Mar 2023 13:12:07 +0900\",\n"
                + "    \"total\": 1108490,\n"
                + "    \"start\": 1,\n"
                + "    \"display\": 5,\n"
                + "    \"items\": [\n"
                + "        {\n"
                + "            \"title\": \"naverTitle1\",\n"
                + "            \"link\": \"https://blog.naver.com/gml7337/223013499416\",\n"
                + "            \"description\": \"naverContent1\",\n"
                + "            \"bloggername\": \"순간들\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/gml7337\",\n"
                + "            \"postdate\": \"20230212\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"naverTitle2\",\n"
                + "            \"link\": \"https://202psj.tistory.com/2021\",\n"
                + "            \"description\": \"naverContent2\",\n"
                + "            \"bloggername\": \"알레폰드의 IT, 전자, 전기 이모저모\",\n"
                + "            \"bloggerlink\": \"https://202psj.tistory.com/\",\n"
                + "            \"postdate\": \"20230101\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"naverTitle3\",\n"
                + "            \"link\": \"https://blog.naver.com/6_bbang/222900061184\",\n"
                + "            \"description\": \"naverContent3\",\n"
                + "            \"bloggername\": \"Bonhomie:친절한 마음씨\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/6_bbang\",\n"
                + "            \"postdate\": \"20221014\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"naverTitle4\",\n"
                + "            \"link\": \"https://blog.naver.com/kolosh/223046962853\",\n"
                + "            \"description\": \"naverContent4\",\n"
                + "            \"bloggername\": \"지오파\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/kolosh\",\n"
                + "            \"postdate\": \"20230317\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"naverTitle5\",\n"
                + "            \"link\": \"https://blog.naver.com/crystal_blog/223049291442\",\n"
                + "            \"description\": \"naverContent5\",\n"
                + "            \"bloggername\": \"Crystal : Blog\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/crystal_blog\",\n"
                + "            \"postdate\": \"20230319\"\n"
                + "        }\n"
                + "    ]\n"
                + "}"
        );
        super.addMockResponse(Arrays.asList(naverGetBlogListSetting), super.naverMockPort);
    }

    private void 카카오_블로그내역조회_실패_MockServer_구동() {
        MockSettingDTO kakaoGetBlogListSetting = new MockSettingDTO(super.kakaoBlogListEndpoint,
            HttpMethod.GET,
            HttpStatus.BAD_REQUEST,
            "");
        super.addMockResponse(Arrays.asList(kakaoGetBlogListSetting), super.kakaoMockPort);
    }

    private void 네이버_블로그내역조회_실패_MockServer_구동() {
        MockSettingDTO naverGetBlogListSetting = new MockSettingDTO(super.kakaoBlogListEndpoint,
            HttpMethod.GET,
            HttpStatus.BAD_REQUEST,
            "");
        super.addMockResponse(Arrays.asList(naverGetBlogListSetting), super.naverMockPort);
    }
}
