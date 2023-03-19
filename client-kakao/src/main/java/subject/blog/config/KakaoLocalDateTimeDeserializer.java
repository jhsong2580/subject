package subject.blog.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class KakaoLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser,
        DeserializationContext deserializationContext) throws IOException {
        return LocalDateTime.parse(jsonParser.getText(), DATE_FORMAT);
    }
}