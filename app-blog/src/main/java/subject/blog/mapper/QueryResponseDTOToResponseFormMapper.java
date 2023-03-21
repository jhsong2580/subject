package subject.blog.mapper;

import org.mapstruct.Mapper;
import subject.blog.OneWayMapper;
import subject.blog.dto.QueryResponseDTO;
import subject.blog.dto.response.QuerySearchResponseForm;

@Mapper(componentModel = "spring")
public interface QueryResponseDTOToResponseFormMapper extends
    OneWayMapper<QueryResponseDTO, QuerySearchResponseForm> {

    @Override
    QuerySearchResponseForm to(QueryResponseDTO queryResponseDTO);
}
