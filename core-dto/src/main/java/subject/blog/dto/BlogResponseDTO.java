package subject.blog.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BlogResponseDTO {

    private String title;
    private String contents;
    private String contentUrl;
    private String blogName;
    private String thumbNailUrl;
    private LocalDateTime contentWriteTime;

}
