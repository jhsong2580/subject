package subject.blog.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import subject.blog.enums.Sort;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class BlogRequestDTO {

    private String query;
    private Sort sort;
    private int page;
    private int size;

}
