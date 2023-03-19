package subject.blog.mapper;

import org.mapstruct.Mapper;
import subject.blog.OneWayMapper;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.request.BlogSearchRequestForm;

@Mapper(componentModel = "spring")
public interface BlogRequestFormToBlogRequestDTOMapper extends
    OneWayMapper<BlogSearchRequestForm, BlogRequestDTO> {

    @Override
    BlogRequestDTO to(BlogSearchRequestForm blogSearchRequestForm);
}
