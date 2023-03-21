package subject.blog.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import subject.blog.dto.response.QuerySearchResponseForm;
import subject.blog.service.QueryService;

@RestController
@RequiredArgsConstructor
public class QueryController {

    private final QueryService queryService;

    @GetMapping("/queries")
    public ResponseEntity<?> getTop10Queries() {
        List<QuerySearchResponseForm> top10Queries = queryService.getTop10Queries();

        return ResponseEntity.ok(top10Queries);
    }
}
