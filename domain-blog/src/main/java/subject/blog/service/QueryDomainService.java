package subject.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subject.blog.domain.Queries;
import subject.blog.domain.QueriesRepository;
import subject.blog.dto.QueryResponseDTO;
import subject.blog.mapper.QueriesToResponseMapper;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryDomainService {

    private final QueriesRepository queriesRepository;
    private final QueriesToResponseMapper responseMapper;

    @Transactional
    public void save(String keyword) {
        Optional<Queries> queryOptional = queriesRepository.findByKeyword(keyword);
        if (queryOptional.isPresent()) {
            Queries queries = queryOptional.get();
            queries.hit();
            return;
        }

        Queries queries = new Queries(keyword);
        queriesRepository.save(queries);
    }

    public List<QueryResponseDTO> findTop10Queries() {
        return queriesRepository.findTop10ByOrderByHitsDesc()
            .stream()
            .map(responseMapper::to)
            .collect(Collectors.toList());
    }

}
