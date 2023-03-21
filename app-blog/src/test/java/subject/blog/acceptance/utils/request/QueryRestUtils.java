package subject.blog.acceptance.utils.request;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import subject.blog.enums.Sort;

public class QueryRestUtils {
    public static ExtractableResponse<Response> QueryTop10요청하기() {
        return RestAssured.given()
            .log().all()
            .accept(APPLICATION_JSON_VALUE)
            .get("http://localhost/queries")
            .then()
            .log().all().
            extract();
    }
}
