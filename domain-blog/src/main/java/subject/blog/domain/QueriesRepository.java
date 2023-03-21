package subject.blog.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QueriesRepository extends JpaRepository<Queries,Long> {
    Optional<Queries> findByKeyword(String keyword);

    List<Queries> findTop10ByOrderByHitsDesc();
}
