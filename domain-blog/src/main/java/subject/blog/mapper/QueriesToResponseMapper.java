package subject.blog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import subject.blog.OneWayMapper;
import subject.blog.domain.Queries;
import subject.blog.dto.QueryResponseDTO;

@Mapper(componentModel = "spring")
public interface QueriesToResponseMapper extends OneWayMapper<Queries, QueryResponseDTO> {

    @Override
    @Mapping(source = "keyword", target = "query")
    @Mapping(source = "hits", target = "hits")
    QueryResponseDTO to(Queries queries);
}
