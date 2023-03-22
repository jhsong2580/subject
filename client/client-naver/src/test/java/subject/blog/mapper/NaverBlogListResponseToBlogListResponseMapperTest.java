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
import subject.blog.dto.NaverBlogListResponseDTO;
import subject.blog.dto.NaverBlogRequestDTO;
import subject.blog.dto.NaverBlogResponseDTO;

class NaverBlogListResponseToBlogListResponseMapperTest {

    private NaverBlogListResponseToBlogListResponseMapper mapper = Mappers.getMapper(
        NaverBlogListResponseToBlogListResponseMapper.class);

    private NaverBlogResponseDTO normalBlogResponseDTO;
    private NaverBlogResponseDTO nullBlogNameResponseDTO;
    private NaverBlogResponseDTO nullDatetimeBlogResponseDTO;

    @BeforeEach
    public void init() {
        normalBlogResponseDTO = makeNaverBlogResponseDTO(
            "1title", "1contents", "1url", "1blogname",
            LocalDateTime.of(1, 1, 1, 1, 1, 1)
        );
        nullBlogNameResponseDTO = makeNaverBlogResponseDTO(
            "2title", "2contents", "2url", null,
            LocalDateTime.of(2, 2, 2, 2, 2, 2)
        );
        nullDatetimeBlogResponseDTO = makeNaverBlogResponseDTO(
            "3title", "3contents", "3url", "3blogname", null
        );
    }

    @Test
    @DisplayName("Mapper을 동작하면 객체는 바뀌나 매핑되는 값은 같다")
    public void NormalMapperTest() {
        //given
        NaverBlogListResponseDTO naverBlogListResponseDTO = NaverBlogListResponseDTO.builder()
            .items(
                Arrays.asList(normalBlogResponseDTO, nullBlogNameResponseDTO,
                    nullDatetimeBlogResponseDTO)
            ).build();
        NaverBlogRequestDTO blogRequestDTO = new NaverBlogRequestDTO(3, 4);
        //when
        BlogListResponseDTO blogListResponseDTO = mapper.to(naverBlogListResponseDTO, blogRequestDTO);

        //then
        assertThat(blogListResponseDTO.getDocuments())
            .extracting("title", "contents", "contentUrl", "blogName", "thumbNailUrl",
                "contentWriteTime")
            .containsExactly(
                getTupleOf(normalBlogResponseDTO),
                getTupleOf(nullBlogNameResponseDTO),
                getTupleOf(nullDatetimeBlogResponseDTO)
            );
    }

    private Tuple getTupleOf(NaverBlogResponseDTO naverBlogResponseDTO) {
        return Tuple.tuple(naverBlogResponseDTO.getTitle(),
            naverBlogResponseDTO.getDescription(), naverBlogResponseDTO.getBloggerlink(),
            naverBlogResponseDTO.getBloggername(), null,
            naverBlogResponseDTO.getPostdate());
    }

    private NaverBlogResponseDTO makeNaverBlogResponseDTO(
        String title,
        String contents,
        String url,
        String blogname,
        LocalDateTime datetime
    ) {
        return NaverBlogResponseDTO.builder()
            .title(title)
            .bloggername(blogname)
            .description(contents)
            .postdate(datetime)
            .bloggerlink(url)
            .build();
    }
}