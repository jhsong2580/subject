package subject.blog.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import subject.blog.dto.QueryResponseDTO;
import subject.blog.dto.response.QuerySearchResponseForm;

class QueryResponseDTOToResponseFormMapperTest {

    private QueryResponseDTOToResponseFormMapper mapper = Mappers.getMapper(
        QueryResponseDTOToResponseFormMapper.class);

    @Test
    @DisplayName("Mapper을 동작하면 객체는 바뀌나 매핑되는 값은 같다")
    public void NormalMapperTest() {
        //given
        QueryResponseDTO queryResponseDTO = QueryResponseDTO.builder()
            .query("query")
            .hits(1L)
            .build();

        //when
        QuerySearchResponseForm querySearchResponseForm = mapper.to(queryResponseDTO);

        //then
        assertAll(
            () -> assertThat(querySearchResponseForm.getQuery()).isEqualTo(
                queryResponseDTO.getQuery()),
            () -> assertThat(querySearchResponseForm.getHits()).isEqualTo(
                queryResponseDTO.getHits())
        );
    }

}