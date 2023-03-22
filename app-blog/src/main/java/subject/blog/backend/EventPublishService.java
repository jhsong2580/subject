package subject.blog.backend;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import subject.blog.event.BlogRequestEvent;

@Component
@RequiredArgsConstructor
public class EventPublishService {

    private final ApplicationEventPublisher eventPublisher;

    @Async("requestQuerySaveExecutor")
    public void publishBlogRequestEvent(BlogRequestEvent blogRequestEvent) {
        eventPublisher.publishEvent(blogRequestEvent);
    }
}
