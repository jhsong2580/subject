package subject.blog.service.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class MockSettingDTO {

    private final String uri;
    private final HttpMethod httpMethod;
    private final HttpStatus httpStatus;
    private final String responseBody;

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
