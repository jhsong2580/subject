package subject.blog.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subject.blog.config.KakaoLocalDateTimeDeserializer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class KakaoBlogResponseDTO {

    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    @JsonDeserialize(using = KakaoLocalDateTimeDeserializer.class)
    private LocalDateTime datetime;
}
