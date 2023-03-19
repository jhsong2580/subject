package subject.blog.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.IterableMapping;
import org.mapstruct.Named;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class KakaoBlogListResponseDTO {
    private List<KakaoBlogResponseDTO> documents;
}
