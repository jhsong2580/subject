package subject.blog.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BlogListResponseDTO {
    private List<BlogResponseDTO> documents;
    private int currentPage;
    private int pageSize;
    private long total;
}
