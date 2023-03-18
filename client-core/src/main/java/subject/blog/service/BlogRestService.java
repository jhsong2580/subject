package subject.blog.service;

import java.util.List;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;

public interface BlogRestService {

    List<BlogResponseDTO> getBlogs(BlogRequestDTO blogRequestDTO);
}
