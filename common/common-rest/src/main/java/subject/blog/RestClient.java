package subject.blog;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @throws IOException : Rest Request가 실패하였을때 발생한다. 메세지로 3rd-party API의 에러 메세지를 받아볼 수 있다.
 */

@Component
@RequiredArgsConstructor
public class RestClient {

    private final RestTemplate restTemplate;

    // 1단계: uri 빌드 ------------------------------------------------------------------------------

    public UriBuilder uri(String uri) {
        return new UriBuilder(uri, restTemplate);
    }

    public static class UriBuilder {

        private final HashMap<String, String> uriQueryMap = new HashMap<>();
        private final UriComponentsBuilder uriComponentsBuilder;
        private final RestTemplate restTemplate;

        private UriBuilder(String uri, RestTemplate restTemplate) {
            uriComponentsBuilder = UriComponentsBuilder.fromUriString(uri);
            this.restTemplate = restTemplate;
        }

        public UriBuilder queryParam(String key, String value) {
            uriComponentsBuilder.queryParam(key, value);
            return this;
        }

        public MethodBuilder uriBuild() {
            Components components = new Components();
            components.setUri(uriComponentsBuilder.buildAndExpand(uriQueryMap)
                .encode(StandardCharsets.UTF_8)
                .toUri());
            components.setRestTemplate(restTemplate);
            return new MethodBuilder(components);
        }
    }

    // 2단계: method 빌드 ---------------------------------------------------------------------------
    public static class MethodBuilder {

        private final Components components;

        private MethodBuilder(Components components) {
            this.components = components;
        }

        public <T> HttpEntityBuilder<T> method(HttpMethod httpMethod) {
            components.setHttpMethod(httpMethod);
            return new HttpEntityBuilder<>(components);
        }

    }

    // 3단계: http entity 빌드 ----------------------------------------------------------------------
    public static class HttpEntityBuilder<T> {

        private final Components components;
        private final HttpHeaders httpHeaders;
        private T body;

        private HttpEntityBuilder(Components components) {
            this.components = components;
            httpHeaders = new HttpHeaders();
        }

        public HttpEntityBuilder<T> headers(Map<String, String> headers) {
            if (nonNull(headers)) {
                headers.forEach(httpHeaders::add);
            }
            return this;
        }

        public HttpEntityBuilder<T> setAccept(List<MediaType> acceptableMediaTypes) {
            httpHeaders.setAccept(acceptableMediaTypes);
            return this;
        }

        public Request build() {
            if (isNull(body)) {
                components.setHttpEntity(new HttpEntity<T>(httpHeaders));
            } else {
                components.setHttpEntity(new HttpEntity<>(body, httpHeaders));
            }
            return new Request(components);
        }

    }

    // 4단계: Request ------------------------------------------------------------------------------
    public static class Request {

        private final int MAX_RETRY = 1;
        private final Components components;

        private Request(Components components) {
            this.components = components;
        }

        public ResponseEntity<String> request() {
            return request(String.class);
        }

        public <T> ResponseEntity<T> request(Class<T> clazz) {
            ResponseEntity<T> responseEntity = components.getRestTemplate().exchange(
                components.getUri(), components.getHttpMethod(), components.getHttpEntity(), clazz);
            return responseEntity;
        }
    }

    @Data
    private static class Components {

        private URI uri;
        private HttpMethod httpMethod;
        private HttpEntity<?> httpEntity;
        private RestTemplate restTemplate;
        private MediaType mediaType;
    }
}
