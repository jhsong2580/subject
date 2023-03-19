package subject.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import subject.blog.OneWayMapper;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.dto.NaverBlogListResponseDTO;
import subject.blog.dto.NaverBlogResponseDTO;

@Mapper(componentModel = "spring")
public interface NaverBlogListResponseToBlogListResponseMapper extends
    OneWayMapper<NaverBlogListResponseDTO, BlogListResponseDTO> {

    /* 객체 내 List에 들어갈 객체들 Mapping */
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "contents")
    @Mapping(source = "bloggerlink", target = "contentUrl")
    @Mapping(source = "bloggername", target = "blogName")
    @Mapping(source = "postdate", target = "contentWriteTime")
    BlogResponseDTO to(NaverBlogResponseDTO naverBlogResponseDTO);

    @Override
    @Mapping(source = "items", target = "documents")
    BlogListResponseDTO to(NaverBlogListResponseDTO naverBlogListResponseDTO);
}
