package subject.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import subject.blog.OneWayTwoSourceMapper;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.dto.KakaoBlogListResponseDTO;
import subject.blog.dto.KakaoBlogResponseDTO;

@Mapper(componentModel = "spring")
public interface KakaoBlogListResponseToBlogListResponseMapper extends
    OneWayTwoSourceMapper<KakaoBlogListResponseDTO, BlogRequestDTO, BlogListResponseDTO> {

    /* 객체 내 List에 들어갈 객체들 Mapping */
    @Mapping(source = "title", target = "title")
    @Mapping(source = "contents", target = "contents")
    @Mapping(source = "url", target = "contentUrl")
    @Mapping(source = "blogname", target = "blogName")
    @Mapping(source = "thumbnail", target = "thumbNailUrl")
    @Mapping(source = "datetime", target = "contentWriteTime")
    BlogResponseDTO to(KakaoBlogResponseDTO kakaoBlogListResponseDTO);

    @Override
    @Mapping(source = "kakaoBlogListResponseDTO.documents", target = "documents")
    @Mapping(source = "kakaoBlogListResponseDTO.meta.totalCount", target = "total")
    @Mapping(source = "blogRequestDTO.page", target = "currentPage")
    @Mapping(source = "blogRequestDTO.size", target = "pageSize")
    BlogListResponseDTO to(KakaoBlogListResponseDTO kakaoBlogListResponseDTO,
        BlogRequestDTO blogRequestDTO);
}
