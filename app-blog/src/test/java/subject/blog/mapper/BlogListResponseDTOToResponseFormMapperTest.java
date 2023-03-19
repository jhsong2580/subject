package subject.blog.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.dto.response.BlogSearchListResponseForm;

class BlogListResponseDTOToResponseFormMapperTest {

    private BlogListResponseDTOToResponseFormMapper mapper = Mappers.getMapper(
        BlogListResponseDTOToResponseFormMapper.class);

    private BlogResponseDTO normalDTO;
    private BlogResponseDTO nullContentDTO;
    private BlogResponseDTO nullTitleDTO;

    @BeforeEach
    public void init() {
        normalDTO = makeBlogResponseDTO("title", "contents", "contentUrl", "blogName", "thumbNail",
            LocalDateTime.now());

        nullContentDTO = makeBlogResponseDTO("title", null, "contentUrl", "blogName", "thumbNail",
            LocalDateTime.now());

        nullTitleDTO = makeBlogResponseDTO(null, "contents", "contentUrl", "blogName", "thumbNail",
            LocalDateTime.now());
    }

    @Test
    @DisplayName("Mapper을 동작하면 객체는 바뀌나 매핑되는 값은 같다")
    public void NormalMapperTest() {
        //given
        BlogListResponseDTO blogListResponseDTO = BlogListResponseDTO.builder()
            .documents(
                Arrays.asList(normalDTO, nullContentDTO, nullTitleDTO)
            ).build();

        //when
        BlogSearchListResponseForm blogSearchListResponseForm = mapper.to(blogListResponseDTO);

        //then
        assertThat(blogSearchListResponseForm.getDocuments())
            .extracting("title", "contents", "contentUrl", "blogName", "thumbNailUrl",
                "contentWriteTime")
            .containsExactly(
                getTupleOf(normalDTO),
                getTupleOf(nullContentDTO),
                getTupleOf(nullTitleDTO)
            );
    }

    private Tuple getTupleOf(BlogResponseDTO blogResponseDTO) {
        return Tuple.tuple(blogResponseDTO.getTitle(),
            blogResponseDTO.getContents(), blogResponseDTO.getContentUrl(),
            blogResponseDTO.getBlogName(), blogResponseDTO.getThumbNailUrl(),
            blogResponseDTO.getContentWriteTime());
    }

    private BlogResponseDTO makeBlogResponseDTO(String title,
        String contents,
        String contentUrl,
        String blogName,
        String thumbNailUrl,
        LocalDateTime contentWriteTime) {
        return BlogResponseDTO.builder()
            .title(title)
            .contents(contents)
            .contentUrl(contentUrl)
            .blogName(blogName)
            .thumbNailUrl(thumbNailUrl)
            .contentWriteTime(contentWriteTime)
            .build();
    }
}