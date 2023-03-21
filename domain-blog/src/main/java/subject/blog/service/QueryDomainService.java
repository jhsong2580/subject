package subject.blog.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import subject.blog.domain.Queries;
import subject.blog.domain.QueriesRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QueryDomainService {

    private final QueriesRepository queriesRepository;

    @Transactional
    public void save(String keyword) {
        Optional<Queries> queryOptional = queriesRepository.findByKeyword(keyword);
        if(queryOptional.isPresent()) {
            Queries queries = queryOptional.get();
            queries.hit();
            return;
        }

        Queries queries = new Queries(keyword);
        queriesRepository.save(queries);
    }

}
