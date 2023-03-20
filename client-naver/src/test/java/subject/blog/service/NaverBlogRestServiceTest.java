package subject.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static subject.blog.service.utils.MockServerSetting.startMockServer;
import static subject.blog.service.utils.MockServerSetting.stopMockServer;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import subject.blog.NaverSetting;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.enums.Sort;
import subject.blog.service.utils.MockServerSetting;
import subject.blog.service.utils.MockSettingDTO;

@ActiveProfiles("naver-test")
@SpringBootTest(classes = NaverSetting.class)
class NaverBlogRestServiceTest {

    @Autowired
    private BlogRestService naverBlogRestService;

    @Value("${naver-mock.port}")
    private int port;
    @Value("${naver-mock.end-points.blog-list-uri}")
    private String blogListURI;
    private ClientAndServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = startMockServer(port);
    }

    @AfterEach
    public void destroy() {
        stopMockServer(mockServer);
    }

    @Test
    public void getBlogListTest() {
        //given (Blog List Response를 위한 Naver Mock API Server 시작)
        startNaverMockServerGetBlogListSuccess();

        //given (Mock서버이기 때문에 query, page, sort, size 등의 정보는 무시된다)
        BlogRequestDTO blogRequestDTO = BlogRequestDTO.builder()
            .query("query")
            .page(1)
            .sort(Sort.ACCURACY)
            .size(10)
            .build();

        //when
        BlogListResponseDTO blogListResponseDTO = naverBlogRestService.getBlogs(blogRequestDTO);
        List<BlogResponseDTO> documents = blogListResponseDTO.getDocuments();

        //then
        assertThat(documents).extracting("title")
            .contains(
                "naverTitle1",
                "naverTitle2",
                "naverTitle3",
                "naverTitle4",
                "naverTitle5"
            );
    }

    private void startNaverMockServerGetBlogListSuccess() {
        MockSettingDTO naverGetBlogListSetting = new MockSettingDTO(blogListURI,
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
                + "            \"description\": \"3naverContent3\",\n"
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
        addMockResponse(Arrays.asList(naverGetBlogListSetting), port);
    }

    private void addMockResponse(List<MockSettingDTO> mockSettingDTOS, int port) {
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