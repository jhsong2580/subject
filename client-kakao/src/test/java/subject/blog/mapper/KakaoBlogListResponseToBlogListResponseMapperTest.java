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
import subject.blog.dto.KakaoBlogListResponseDTO;
import subject.blog.dto.KakaoBlogResponseDTO;

class KakaoBlogListResponseToBlogListResponseMapperTest {

    private KakaoBlogListResponseToBlogListResponseMapper mapper = Mappers.getMapper(
        KakaoBlogListResponseToBlogListResponseMapper.class);

    private KakaoBlogResponseDTO normalKakaoBlogResponseDTO;
    private KakaoBlogResponseDTO nullThumbnailKakaoBlogResponseDTO;
    private KakaoBlogResponseDTO nullDatetimeKakaoBlogResponseDTO;

    @BeforeEach
    public void init() {
        normalKakaoBlogResponseDTO = makeKakaoBlogResponseDTO(
            "1title", "1contents", "1url", "1blogname", "1thumbnail",
            LocalDateTime.of(1,1,1,1,1,1)
        );
        nullThumbnailKakaoBlogResponseDTO = makeKakaoBlogResponseDTO(
            "2title", "2contents", "2url", "2blogname", null,
            LocalDateTime.of(2,2,2,2,2,2)
        );
        nullDatetimeKakaoBlogResponseDTO = makeKakaoBlogResponseDTO(
            "3title", "3contents", "3url", "3blogname", null,
            null
        );
    }

    @Test
    @DisplayName("Mapper을 동작하면 객체는 바뀌나 매핑되는 값은 같다")
    public void NormalMapperTest (){
        //given
        KakaoBlogListResponseDTO kakaoBlogListResponseDTO = KakaoBlogListResponseDTO.builder()
            .documents(
                Arrays.asList(normalKakaoBlogResponseDTO, nullThumbnailKakaoBlogResponseDTO,
                    nullDatetimeKakaoBlogResponseDTO)
            )
            .build();

        //when
        BlogListResponseDTO blogListResponseDTO = mapper.to(kakaoBlogListResponseDTO);


        //then
        assertThat(blogListResponseDTO.getDocuments())
            .extracting("title", "contents", "contentUrl", "blogName", "thumbNailUrl", "contentWriteTime")
            .containsExactly(
                getTupleOf(normalKakaoBlogResponseDTO),
                getTupleOf(nullThumbnailKakaoBlogResponseDTO),
                getTupleOf(nullDatetimeKakaoBlogResponseDTO)
            );
    }

    private Tuple getTupleOf(KakaoBlogResponseDTO kakaoBlogResponseDTO) {
        return Tuple.tuple(kakaoBlogResponseDTO.getTitle(),
            kakaoBlogResponseDTO.getContents(), kakaoBlogResponseDTO.getUrl(),
            kakaoBlogResponseDTO.getBlogname(), kakaoBlogResponseDTO.getThumbnail(),
            kakaoBlogResponseDTO.getDatetime());
    }


    private KakaoBlogResponseDTO makeKakaoBlogResponseDTO(
        String title,
        String contents,
        String url,
        String blogname,
        String thumbnail,
        LocalDateTime datetime
    ) {
        return KakaoBlogResponseDTO.builder()
            .title(title)
            .blogname(blogname)
            .contents(contents)
            .datetime(datetime)
            .thumbnail(thumbnail)
            .url(url)
            .build();
    }

}