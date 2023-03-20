package subject.blog.acceptance.utils;


import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;

public class MockServerSetting {


    public static ClientAndServer startMockServer(int port) {
        System.out.println("<<<<<<<MOCK SERVER START>>>>>>>");

        return ClientAndServer.startClientAndServer(port); // MockServer를 8080 포트로 시작합니다.

    }

    public static void stopMockServer(ClientAndServer mockServer) {
        System.out.println("<<<<<<<MOCK SERVER END>>>>>>>");
        mockServer.stop();
    }

    public static MockServerClient getMockServerClientBy(int port) {
        return new MockServerClient("localhost", port);
    }
}
