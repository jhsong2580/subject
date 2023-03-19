package subject.blog.service.utils;


import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import io.swagger.models.HttpMethod;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.MediaType;

public class MockServerSetting {

    private ClientAndServer mockServer;

    public void main(int port, String uri, int statusCode, String body) {
        System.out.println("<<<<<<<MOCK SERVER START>>>>>>>");

        mockServer = ClientAndServer.startClientAndServer(port); // MockServer를 8080 포트로 시작합니다.

        MockServerClient mockServerClient = new MockServerClient("localhost", port);

        mockServerClient
            .when(
                request()
                    .withMethod(HttpMethod.GET.name())
                    .withPath(uri)
            ).respond(
                response()
                    .withStatusCode(statusCode)
                    .withContentType(MediaType.APPLICATION_JSON)
                    .withBody(body)
            );
    }

    public void stopMockServer() {
        System.out.println("<<<<<<<MOCK SERVER END>>>>>>>");
        mockServer.stop();
    }
}
