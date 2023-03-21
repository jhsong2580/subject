package subject.blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.dto.response.BlogSearchListResponseForm;
import subject.blog.enums.Sort;
import subject.blog.mapper.BlogListResponseDTOToResponseFormMapper;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    private BlogService blogService;

    private BlogListResponseDTOToResponseFormMapper mapper = Mappers.getMapper(
        BlogListResponseDTOToResponseFormMapper.class);
    @Mock
    private BlogRestService kakaoBlogRestService;

    @Mock
    private BlogRestService naverBlogRestService;
    @Mock
    private ApplicationEventPublisher eventPublisher;

    private BlogRequestDTO blogRequestDTO;

    @BeforeEach
    public void init() {
        blogService = new BlogService(
            Arrays.asList(kakaoBlogRestService, naverBlogRestService),
            mapper,
            eventPublisher
        );

        //테스트에 영향을 주지 않는다.
        blogRequestDTO = BlogRequestDTO.builder()
            .query("query")
            .page(1)
            .size(10)
            .sort(Sort.ACCURACY)
            .build();
    }

    @Test
    @DisplayName("카카오 서비스에서 응답을 정상적으로 받을시 그대로 사용자에게 반환한다.")
    public void KakaoResponseTest() {
        //given
        BlogResponseDTO blogResponseDTO1 = BlogResponseDTO.builder().title("t1").build();
        BlogResponseDTO blogResponseDTO2 = BlogResponseDTO.builder().title("t2").build();
        BlogResponseDTO blogResponseDTO3 = BlogResponseDTO.builder().title("t3").build();
        doNothing().when(eventPublisher).publishEvent(any());
        when(kakaoBlogRestService.getBlogs(blogRequestDTO))
            .thenReturn(
                BlogListResponseDTO.builder()
                    .documents(
                        Arrays.asList(blogResponseDTO1, blogResponseDTO2, blogResponseDTO3)
                    ).build()
            );

        //when
        BlogSearchListResponseForm blogSearchListResponseForm = blogService.getBlogInfo(
            blogRequestDTO);

        //then
        assertThat(blogSearchListResponseForm.getDocuments())
            .extracting("title")
            .contains(
                blogResponseDTO1.getTitle(),
                blogResponseDTO2.getTitle(),
                blogResponseDTO3.getTitle()
            );
    }

    @Test
    @DisplayName("카카오 서비스에서 에러가 발생하면 네이버 서비스에서 조회한다.")
    public void NaverResponseTest() {
        //given
        BlogResponseDTO blogResponseDTO1 = BlogResponseDTO.builder().title("t1").build();
        BlogResponseDTO blogResponseDTO2 = BlogResponseDTO.builder().title("t2").build();
        BlogResponseDTO blogResponseDTO3 = BlogResponseDTO.builder().title("t3").build();
        doNothing().when(eventPublisher).publishEvent(any());
        when(kakaoBlogRestService.getBlogs(blogRequestDTO))
            .thenThrow(
                new RuntimeException("TEST EXCEPTION")
            );
        when(naverBlogRestService.getBlogs(blogRequestDTO))
            .thenReturn(
                BlogListResponseDTO.builder()
                    .documents(
                        Arrays.asList(blogResponseDTO1, blogResponseDTO2, blogResponseDTO3)
                    ).build()
            );


        //when
        BlogSearchListResponseForm blogSearchListResponseForm = blogService.getBlogInfo(
            blogRequestDTO);

        //then
        assertThat(blogSearchListResponseForm.getDocuments())
            .extracting("title")
            .contains(
                blogResponseDTO1.getTitle(),
                blogResponseDTO2.getTitle(),
                blogResponseDTO3.getTitle()
            );
    }

    @Test
    @DisplayName("Naver, Kakao 모두 에러가 발생할시 에러를 반환한다.")
    public void ExceptionTest() {
        //given
        doNothing().when(eventPublisher).publishEvent(any());
        when(kakaoBlogRestService.getBlogs(blogRequestDTO))
            .thenThrow(
                new RuntimeException("TEST EXCEPTION")
            );
        when(naverBlogRestService.getBlogs(blogRequestDTO))
            .thenThrow(
                new RuntimeException("TEST EXCEPTION")
            );

        //when && then
        assertThatThrownBy(
            () -> blogService.getBlogInfo(blogRequestDTO)
        ).isInstanceOf(RuntimeException.class)
            .hasMessage("The query requested by the user cannot be investigated");
    }
}