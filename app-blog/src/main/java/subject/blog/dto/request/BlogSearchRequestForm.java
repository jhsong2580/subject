package subject.blog.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import subject.blog.annotation.EnumCheck;
import subject.blog.enums.Sort;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class BlogSearchRequestForm {

    @NotEmpty(message = "query field must be required")
    private String query;

    @EnumCheck(message = "Field sort must be either ACCURACY or RECENCY value", enumClass = Sort.class)
    private String sort;

    @Range(min = 1, max = 50, message = "page field is a value between 1 and 50")
    private int page;

    @Range(min = 10, max = 50, message = "size field is a value between 10 and 50")
    private int size;

    @Builder
    public BlogSearchRequestForm(String query, String sort, Integer page, Integer size) {
        this.query = query;
        this.sort = sort != null ? sort : Sort.ACCURACY.name();
        this.page = page != null ? page : 1;
        this.size = size != null ? size : 10;
    }

    public Sort getSort() {
        return Sort.valueOf(this.sort.toUpperCase());
    }
}
