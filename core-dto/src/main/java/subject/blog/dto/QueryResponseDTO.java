package subject.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class QueryResponseDTO {
    private String query;
    private long hits;
}
