package subject.blog.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.service.QueryDomainService;

@Component
@RequiredArgsConstructor
public class BlogRequestEventListener {

    private final QueryDomainService queryDomainService;

    @EventListener
    public void blogRequestEvent(BlogRequestEvent blogRequestEvent) {
        BlogRequestDTO blogRequestDTO = blogRequestEvent.getBlogRequestDTO();
        queryDomainService.save(blogRequestDTO.getQuery());
    }
}
