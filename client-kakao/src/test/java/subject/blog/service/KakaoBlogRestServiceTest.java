package subject.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static subject.blog.service.utils.MockServerSetting.getMockServerClientBy;
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
import subject.blog.KakaoSetting;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.enums.Sort;
import subject.blog.service.utils.MockSettingDTO;

@ActiveProfiles("kakao-test")
@SpringBootTest(classes = KakaoSetting.class)
class KakaoBlogRestServiceTest {

    @Autowired
    private KakaoBlogRestService kakaoBlogRestService;
    private ClientAndServer mockServer;
    @Value("${kakao-mock.port}")
    private int port;
    @Value("${kakao-mock.end-points.blog-list-uri}")
    private String blogListURI = "/v2/search/blog";

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
        startKakaoMockServerGetBlogListSuccess();

        //given (Mock서버이기 때문에 query, page, sort, size 등의 정보는 무시된다)
        BlogRequestDTO blogRequestDTO = BlogRequestDTO.builder()
            .query("query")
            .page(1)
            .sort(Sort.ACCURACY)
            .size(10)
            .build();

        //when
        BlogListResponseDTO blogListResponseDTO = kakaoBlogRestService.getBlogs(blogRequestDTO);
        List<BlogResponseDTO> documents = blogListResponseDTO.getDocuments();

        //then
        assertThat(documents).extracting("contents")
            .contains(
                "kakaoContent1",
                "kakaoContent2",
                "kakaoContent3",
                "kakaoContent4",
                "kakaoContent5"
            );
    }

    private void startKakaoMockServerGetBlogListSuccess() {
        MockSettingDTO kakaoGetBlogListSetting = new MockSettingDTO(blogListURI,
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
        addMockResponse(Arrays.asList(kakaoGetBlogListSetting), port);
    }

    private void addMockResponse(List<MockSettingDTO> mockSettingDTOS, int port) {
        MockServerClient mockServerClient = getMockServerClientBy(
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