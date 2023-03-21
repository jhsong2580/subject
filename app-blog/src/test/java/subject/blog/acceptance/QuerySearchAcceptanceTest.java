package subject.blog.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.junit.jupiter.api.Assertions.assertAll;
import static subject.blog.acceptance.utils.request.BlogRestUtils.Blog_정보조회_요청하기;
import static subject.blog.acceptance.utils.request.QueryRestUtils.QueryTop10요청하기;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import subject.blog.enums.Sort;

public class QuerySearchAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    public void init() {
        super.init();
    }

    @AfterEach
    public void destroy() {
        super.destroy();
    }

    @Test
    public void 쿼리순위_정상_획득() {
        //given
        카카오_블로그내역조회_성공_MockServer_구동();
        Map<String, Integer> 블로그_정보_키워드와_횟수 = new HashMap<>();
        블로그_정보_키워드와_횟수.put("keyword1", 1);
        블로그_정보_키워드와_횟수.put("keyword2", 2);
        블로그_정보_키워드와_횟수.put("keyword3", 3);
        블로그_정보_키워드와_횟수.put("keyword4", 4);
        블로그_정보_키워드와_횟수.put("keyword5", 5);
        블로그_정보_키워드와_횟수.put("keyword6", 6);
        블로그_정보_키워드와_횟수.put("keyword7", 7);
        블로그_정보_키워드와_횟수.put("keyword8", 8);
        블로그_정보_키워드와_횟수.put("keyword9", 9);
        블로그_정보_키워드와_횟수.put("keyword10", 10);
        블로그_정보_키워드와_횟수.put("keyword11", 11);
        Blog_정보_조회하기(블로그_정보_키워드와_횟수);

        //when
        ExtractableResponse<Response> 응답 = QueryTop10요청하기();

        //then
        assertAll(
            () -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(응답.jsonPath().getMap("[0]")).contains(entry("hits", 11),
                entry("query", "keyword11")),
            () -> assertThat(응답.jsonPath().getMap("[9]")).contains(entry("hits", 2),
                entry("query", "keyword2"))
        );

    }

    @Test
    public void 쿼리순위_정상_획득_비동기() {
        //given
        카카오_블로그내역조회_성공_MockServer_구동();
        Map<String, Integer> 블로그_정보_키워드와_횟수 = new HashMap<>();
        블로그_정보_키워드와_횟수.put("keyword1", 10);
        Blog_정보_비동기로_조회하기(블로그_정보_키워드와_횟수);

        //when
        ExtractableResponse<Response> 응답 = QueryTop10요청하기();

        //then
        assertAll(
            () -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.OK.value()),
            () -> assertThat(응답.jsonPath().getMap("[0]")).contains(entry("hits", 10),
                entry("query", "keyword1"))
        );

    }


    private void Blog_정보_조회하기(Map<String, Integer> 블로그_정보_키워드와_횟수) {
        for (Entry<String, Integer> 탐색정보 : 블로그_정보_키워드와_횟수.entrySet()) {
            for (int i = 0; i < 탐색정보.getValue(); i++) {
                Blog_정보조회_요청하기(탐색정보.getKey(), 10, 1, Sort.ACCURACY);
            }
        }
    }

    private void Blog_정보_비동기로_조회하기(Map<String, Integer> 블로그_정보_키워드와_횟수) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (Entry<String, Integer> 탐색정보 : 블로그_정보_키워드와_횟수.entrySet()) {
            for (int i = 0; i < 탐색정보.getValue(); i++) {
                executorService.execute(() -> {
                    Blog_정보조회_요청하기(탐색정보.getKey(), 10, 10, Sort.ACCURACY);
                });
            }
        }

        // ExecutorService 종료를 요청합니다.
        executorService.shutdown();

        try {
            // 모든 작업이 완료될 때까지 대기합니다.
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            // 예외 처리 코드
        }

    }
}
