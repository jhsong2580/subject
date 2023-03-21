package subject.blog.acceptance;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static subject.blog.acceptance.utils.MockServerSetting.startMockServer;
import static subject.blog.acceptance.utils.MockServerSetting.stopMockServer;

import io.restassured.RestAssured;
import java.util.Arrays;
import java.util.List;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import subject.blog.acceptance.utils.DatabaseCleanup;
import subject.blog.acceptance.utils.MockServerSetting;
import subject.blog.acceptance.utils.MockSettingDTO;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class AcceptanceTest {
    @Autowired
    private DatabaseCleanup databaseCleanup;
    @Autowired
    private CacheManager cacheManager;

    @LocalServerPort
    int port;

    protected ClientAndServer kakaoMockServer;
    protected ClientAndServer naverMockServer;

    @Value("${naver-mock.port}")
    protected int naverMockPort;

    @Value("${naver-mock.end-points.blog-list-uri}")
    protected String naverBlogListEndpoint;

    @Value("${kakao-mock.port}")
    protected int kakaoMockPort;

    @Value("${kakao-mock.end-points.blog-list-uri}")
    protected String kakaoBlogListEndpoint;

    protected void init() {
        if (RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
            databaseCleanup.afterPropertiesSet();
        }
        databaseCleanup.execute();

        Cache blogs = cacheManager.getCache("blogs");
        Cache hotKeys = cacheManager.getCache("hotKeys");
        blogs.clear();
        hotKeys.clear();

        kakaoMockServer = startMockServer(kakaoMockPort);
        naverMockServer = startMockServer(naverMockPort);
    }

    protected void destroy() {
        stopMockServer(kakaoMockServer);
        stopMockServer(naverMockServer);
    }

    protected void 카카오_블로그내역조회_성공_MockServer_구동() {
        MockSettingDTO kakaoGetBlogListSetting = new MockSettingDTO(kakaoBlogListEndpoint,
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
        addMockResponse(Arrays.asList(kakaoGetBlogListSetting), kakaoMockPort);
    }

    protected void 네이버_블로그내역조회_성공_MockServer_구동() {
        MockSettingDTO naverGetBlogListSetting = new MockSettingDTO(naverBlogListEndpoint,
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
        addMockResponse(Arrays.asList(naverGetBlogListSetting), naverMockPort);
    }

    protected void 카카오_블로그내역조회_실패_MockServer_구동() {
        MockSettingDTO kakaoGetBlogListSetting = new MockSettingDTO(kakaoBlogListEndpoint,
            HttpMethod.GET,
            HttpStatus.BAD_REQUEST,
            "");
        addMockResponse(Arrays.asList(kakaoGetBlogListSetting), kakaoMockPort);
    }

    protected void 네이버_블로그내역조회_실패_MockServer_구동() {
        MockSettingDTO naverGetBlogListSetting = new MockSettingDTO(kakaoBlogListEndpoint,
            HttpMethod.GET,
            HttpStatus.BAD_REQUEST,
            "");
        addMockResponse(Arrays.asList(naverGetBlogListSetting), naverMockPort);
    }
    protected void addMockResponse(List<MockSettingDTO> mockSettingDTOS, int port) {
        MockServerClient mockServerClient = MockServerSetting.getMockServerClientBy(
            port);

        for (MockSettingDTO mockSetting : mockSettingDTOS) {
            mockServerClient
                .when(
                    request()
                        .withMethod(mockSetting.getHttpMethod().name())
                        .withPath(mockSetting.getUri())
                ).respond(
                    response()
                        .withStatusCode(mockSetting.getHttpStatus().value())
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(mockSetting.getResponseBody())
                );
        }
    }
}
