package subject.blog.acceptance.utils.request;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import subject.blog.enums.Sort;

public class BlogRestUtils {

    public static ExtractableResponse<Response> Blog_정보조회_요청하기(
        String query,
        int size,
        int page,
        Sort sort
    ) {
        return RestAssured.given()
            .queryParam("query", query)
            .queryParam("size", size)
            .queryParam("page", page)
            .queryParam("sort", sort)
            .log().all()
            .accept(APPLICATION_JSON_VALUE)
            .get("http://localhost/blogs")
            .then()
            .log().all().
            extract();
    }
}
