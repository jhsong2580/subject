package subject.blog.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import subject.blog.dto.response.QuerySearchResponseForm;
import subject.blog.mapper.QueryResponseDTOToResponseFormMapper;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final QueryDomainService queryDomainService;
    private final QueryResponseDTOToResponseFormMapper responseMapper;

    public List<QuerySearchResponseForm> getTop10Queries() {
        return queryDomainService.findTop10Queries()
            .stream()
            .map(responseMapper::to)
            .collect(Collectors.toList());
    }

}
