package subject.blog.acceptance.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class MockSettingDTO {

    private String uri;
    private HttpMethod httpMethod;
    private HttpStatus httpStatus;
    private String responseBody;

    public MockSettingDTO(String uri, HttpMethod httpMethod, HttpStatus httpStatus,
        String responseBody) {
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.httpStatus = httpStatus;
        this.responseBody = responseBody;
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
