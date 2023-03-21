package subject.blog.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import subject.blog.RestClient;
import subject.blog.config.KakaoConfig;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.KakaoBlogListResponseDTO;
import subject.blog.mapper.KakaoBlogListResponseToBlogListResponseMapper;

@Order(1)
@Component
@RequiredArgsConstructor
public class KakaoBlogRestService implements BlogRestService {

    private final KakaoBlogListResponseToBlogListResponseMapper listResponseToDTOMapper;
    private final RestClient restClient;
    private final KakaoConfig kakaoConfig;

    @Override
    public BlogListResponseDTO getBlogs(BlogRequestDTO blogRequestDTO) {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, kakaoConfig.getKey());

        ResponseEntity<KakaoBlogListResponseDTO> request = restClient
            .uri(kakaoConfig.getBlogListURL())
            .queryParam("query", blogRequestDTO.getQuery())
            .queryParam("sort", blogRequestDTO.getSort().getKakao())
            .queryParam("page", String.valueOf(blogRequestDTO.getPage()))
            .queryParam("size", String.valueOf(blogRequestDTO.getSize()))
            .uriBuild()
            .method(HttpMethod.GET)
            .headers(headers)
            .setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
            .build()
            .request(KakaoBlogListResponseDTO.class);

        BlogListResponseDTO blogListResponseDTO = listResponseToDTOMapper.to(request.getBody(), blogRequestDTO);

        return blogListResponseDTO;
    }
}
