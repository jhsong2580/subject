package subject.blog.dto;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlogRequestDTO)) {
            return false;
        }
        BlogRequestDTO that = (BlogRequestDTO) o;
        return page == that.page && size == that.size && query.equals(that.query)
            && sort == that.sort;
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, sort, page, size);
    }
}
