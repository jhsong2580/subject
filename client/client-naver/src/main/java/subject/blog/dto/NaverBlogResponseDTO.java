package subject.blog.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subject.blog.config.NaverLocalDateTimeDeserializer;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NaverBlogResponseDTO {

    private String title;
    private String description;
    private String bloggerlink;
    private String bloggername;
    @JsonDeserialize(using = NaverLocalDateTimeDeserializer.class)
    private LocalDateTime postdate;
}
