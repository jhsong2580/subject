package subject.blog.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestApiHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
            || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String errorMessage = getErrorMessageFromResponse(response);

        throw new IOException(errorMessage);
    }

    private String getErrorMessageFromResponse(ClientHttpResponse response) {
        try (InputStream inputStream = response.getBody()) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            for (int data = inputStream.read(); data != -1; data = inputStream.read()) {
                byteArrayOutputStream.write(data);
            }

            return byteArrayOutputStream.toString();
        } catch (Exception e) {
            return "Can not Extract Error Message";
        }
    }
}
