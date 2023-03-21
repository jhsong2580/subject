package subject.blog.service;

import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;

public interface BlogRestService {

    BlogListResponseDTO getBlogs(BlogRequestDTO blogRequestDTO);
}
