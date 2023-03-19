package subject.blog.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.request.BlogSearchRequestForm;
import subject.blog.enums.Sort;

class BlogRequestFormToBlogRequestDTOMapperTest {

    private BlogRequestFormToBlogRequestDTOMapper mapper = Mappers.getMapper(
        BlogRequestFormToBlogRequestDTOMapper.class);

    @Test
    @DisplayName("Mapper을 동작하면 객체는 바뀌나 매핑되는 값은 같다")
    public void NormalMapperTest() {
        //given
        BlogSearchRequestForm blogSearchRequestForm = BlogSearchRequestForm.builder()
            .query("query")
            .size(10)
            .page(1)
            .sort(Sort.ACCURACY)
            .build();

        //when
        BlogRequestDTO blogRequestDTO = mapper.to(blogSearchRequestForm);

        //then
        assertAll(
            () -> assertThat(blogRequestDTO.getPage()).isEqualTo(blogSearchRequestForm.getPage()),
            () -> assertThat(blogRequestDTO.getSize()).isEqualTo(blogSearchRequestForm.getSize()),
            () -> assertThat(blogRequestDTO.getSort()).isEqualTo(blogSearchRequestForm.getSort()),
            () -> assertThat(blogRequestDTO.getQuery()).isEqualTo(blogSearchRequestForm.getQuery())
        );
    }
}