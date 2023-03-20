package subject.blog.controller;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.request.BlogSearchRequestForm;
import subject.blog.dto.response.BlogSearchListResponseForm;
import subject.blog.mapper.BlogRequestFormToBlogRequestDTOMapper;
import subject.blog.service.BlogService;

@RestController
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final BlogRequestFormToBlogRequestDTOMapper toBlogRequestDTOMapper;


    @GetMapping("/blogs")
    public ResponseEntity<?> getBlogs(
        @Validated @ModelAttribute BlogSearchRequestForm blogSearchRequestForm,
        BindingResult bindingResult
    ) {

        if(bindingResult.hasErrors()) {
            throw new IllegalArgumentException(
                bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList())
                    .toString()
            );
        }

        BlogRequestDTO blogRequestDTO = toBlogRequestDTOMapper.to(blogSearchRequestForm);

        BlogSearchListResponseForm blogSearchListResponseForm = blogService.getBlogInfo(
            blogRequestDTO);
        return ResponseEntity.ok(blogSearchListResponseForm);
    }
}
