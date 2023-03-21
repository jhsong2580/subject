package subject.blog.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.response.BlogSearchListResponseForm;
import subject.blog.event.BlogRequestEvent;
import subject.blog.mapper.BlogListResponseDTOToResponseFormMapper;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final List<BlogRestService> blogRestServices;
    private final BlogListResponseDTOToResponseFormMapper responseMapper;
    private final ApplicationEventPublisher eventPublisher;

    public BlogSearchListResponseForm getBlogInfo(BlogRequestDTO blogRequestDTO) {
        eventPublisher.publishEvent(new BlogRequestEvent(BlogService.class, blogRequestDTO));

        for (BlogRestService blogRestService : blogRestServices) {
            Optional<BlogListResponseDTO> blogListResponseDTO = executeRequest(blogRestService,
                blogRequestDTO);

            if (blogListResponseDTO.isPresent()) {
                return responseMapper.to(blogListResponseDTO.get());
            }
        }

        throw new RuntimeException("The query requested by the user cannot be investigated");
    }

    private Optional<BlogListResponseDTO> executeRequest(BlogRestService blogRestService,
        BlogRequestDTO blogRequestDTO) {
        try {
            return Optional.of(blogRestService.getBlogs(blogRequestDTO));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
