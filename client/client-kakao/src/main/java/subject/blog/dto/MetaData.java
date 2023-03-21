package subject.blog.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MetaData {

    @JsonProperty("total_count")
    private int totalCount; // 검색된 문서 수
    @JsonProperty("pageable_count")
    private int pageableCount; // total_count중 노출가능 문서 수
    @JsonProperty("is_end")
    private boolean isEnd; // 현재 페이지가 마지막 페이지인지 여부
}
