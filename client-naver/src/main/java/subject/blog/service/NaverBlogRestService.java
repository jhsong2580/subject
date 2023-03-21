package subject.blog.service;

import java.util.Arrays;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import subject.blog.RestClient;
import subject.blog.config.NaverConfig;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.NaverBlogListResponseDTO;
import subject.blog.mapper.NaverBlogListResponseToBlogListResponseMapper;

@Order(2)
@Component
@RequiredArgsConstructor
public class NaverBlogRestService implements BlogRestService {

    private final NaverBlogListResponseToBlogListResponseMapper listResponseToDTOMapper;
    private final RestClient restClient;
    private final NaverConfig naverConfig;

    @Override
    public BlogListResponseDTO getBlogs(BlogRequestDTO blogRequestDTO) {
        Map<String, String> authHeaders = naverConfig.getAuthHeaders();
        int start = (blogRequestDTO.getPage() -1) * blogRequestDTO.getSize() +1;


        ResponseEntity<NaverBlogListResponseDTO> request = restClient
            .uri(naverConfig.getBlogListURL())
            .queryParam("query", blogRequestDTO.getQuery())
            .queryParam("sort", blogRequestDTO.getSort().getNaver())
            .queryParam("display", String.valueOf(blogRequestDTO.getSize()))
            .queryParam("start", String.valueOf(start))
            .uriBuild()
            .method(HttpMethod.GET)
            .headers(authHeaders)
            .setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
            .build()
            .request(NaverBlogListResponseDTO.class);

        return listResponseToDTOMapper.to(request.getBody(), blogRequestDTO);
    }
}
