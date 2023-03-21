package subject.blog.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import subject.blog.domain.Queries;
import subject.blog.dto.QueryResponseDTO;

class QueriesToResponseMapperTest {

    private QueriesToResponseMapper mapper = Mappers.getMapper(
        QueriesToResponseMapper.class);

    @Test
    @DisplayName("Mapper을 동작하면 객체는 바뀌나 매핑되는 값은 같다")
    public void NormalMapperTest() {
        //given
        Queries query = new Queries("testKeyword");

        //when
        QueryResponseDTO queryResponseDTO = mapper.to(query);

        //then
        assertAll(
            () -> assertThat(queryResponseDTO.getQuery()).isEqualTo(query.getKeyword()),
            () -> assertThat(queryResponseDTO.getHits()).isEqualTo(query.getHits())

        );
    }
}