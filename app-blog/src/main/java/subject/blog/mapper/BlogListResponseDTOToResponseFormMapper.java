package subject.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import subject.blog.OneWayMapper;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.dto.response.BlogSearchListResponseForm;
import subject.blog.dto.response.BlogSearchResponseForm;

@Mapper(componentModel = "spring")
public interface BlogListResponseDTOToResponseFormMapper extends
    OneWayMapper<BlogListResponseDTO, BlogSearchListResponseForm> {

    BlogSearchResponseForm to(BlogResponseDTO blogResponseDTO);

    @Override
    @Mapping(source = "documents", target = "documents")
    BlogSearchListResponseForm to(BlogListResponseDTO blogListResponseDTO);
}
