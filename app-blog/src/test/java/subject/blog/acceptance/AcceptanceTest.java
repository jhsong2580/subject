package subject.blog.acceptance;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static subject.blog.acceptance.utils.MockServerSetting.startMockServer;
import static subject.blog.acceptance.utils.MockServerSetting.stopMockServer;

import io.restassured.RestAssured;
import java.util.List;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import subject.blog.acceptance.utils.MockServerSetting;
import subject.blog.acceptance.utils.MockSettingDTO;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class AcceptanceTest {

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
        }
        kakaoMockServer = startMockServer(kakaoMockPort);
        naverMockServer = startMockServer(naverMockPort);
    }

    protected void destroy() {
        stopMockServer(kakaoMockServer);
        stopMockServer(naverMockServer);
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
