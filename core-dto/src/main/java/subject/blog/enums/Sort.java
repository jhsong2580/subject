package subject.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sort {
    ACCURACY("accuracy", "sim"), RECENCY("recency", "date");

    private String kakao;
    private String naver;
}
