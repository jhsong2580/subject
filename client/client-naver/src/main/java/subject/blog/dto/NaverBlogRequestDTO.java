package subject.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NaverBlogRequestDTO {

    private int page;
    private int size;
}
