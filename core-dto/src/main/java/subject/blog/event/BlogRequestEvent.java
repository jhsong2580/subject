package subject.blog.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import subject.blog.dto.BlogRequestDTO;

@Getter
public class BlogRequestEvent extends ApplicationEvent {

    private final BlogRequestDTO blogRequestDTO;

    public BlogRequestEvent(Class<?> eventPublishingClass, BlogRequestDTO blogRequestDTO) {
        super(eventPublishingClass);
        this.blogRequestDTO = blogRequestDTO;
    }
}
