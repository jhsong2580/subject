package subject.blog.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import subject.blog.annotation.GetLock;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.service.QueryDomainService;

@Component
@RequiredArgsConstructor
public class BlogRequestEventListener {

    private final QueryDomainService queryDomainService;

    @EventListener
    @GetLock(key = "#blogRequestEvent.blogRequestDTO.query", waitTime = 5)
    public void blogRequestEvent(BlogRequestEvent blogRequestEvent) {
        BlogRequestDTO blogRequestDTO = blogRequestEvent.getBlogRequestDTO();
        queryDomainService.save(blogRequestDTO.getQuery());
    }
}
