# 블로그 검색 서비스

---

### 기능
1. 블로그 검색
    - Endpoint : http://localhost:8080/blogs
    - QueryParams
        - | QueryParam | 입력범위 | required | default value |
          |:----:|:--------:|:-------------:|:----|
          | query      |  -   |    true     |       -       |
          | page          |  1 ~ 50  |    false     |       1       |
          | size          |  10 ~ 50  |    false     |       10       |
          | sort          |  ACCURACY / RECENCY  |    false     |       ACCURACY       |
      
    - 응답 (같은 Query, Page, Size, Sort에 대해선 30초마다 갱신)
        - |  column     | description |
          |:---------:|:--------:|
          | documents.title        |   글 제목    |
          | documents.contents        |   글 요약    |
          | documents.contentUrl        |   글 URL    |
          | documents.blogName        |   블로그 이름    |
          | documents.thumbNailUrl        |  썸네일. 네이버를 통한 조회는 제공하지 않는다.    |
          | documents.contentWriteTime        |  글 작성 시간    |
          | currentPage        |  현재 페이지    |
          | pageSize        |  한 페이지에서 조회된 개수    |
          | total        |  전체 글 개수    |
      
    - 주의사항
        - Kakao 조회는 Page 50, Size 50으로 정상 검색이 가능하나, Naver는 시작 인덱스가 1000 이상이 될 수 없다.
        - Naver 시작 인덱스가 1000 이상이라면, 동일 사이즈로 조회 가능한 최대 Page를 계산하여 반환한다
            - ex) blogs?query=query&page=50&size=50   -> page=20, size=50인 응답을 반환

2. 인기 검색어 목록 조회
    - Endpoint : http://localhost:8080/queries
   
    - 응답 (20초마다 갱신)
        - | cloumn | description |
          |:---------:|:--------:|
          | query  |   키워드    |
          | hits      |   조회수    |

---
### 연관 모듈
- [client모듈](https://github.com/jhsong2580/subject/blob/main/client/README.md)
    - 외부 API에 대한 설정, 서비스로직을 갖는다.
- [Exception모듈](https://github.com/jhsong2580/subject/blob/main/exception-core/README.md)
    - 사용자 요청에 대한 에러가 발생했을때, Controller Advice를 통해 사용자에게 응답을 반환한다

- [common모듈](https://github.com/jhsong2580/subject/blob/main/common/README.md) :
    - 공통으로 사용하는 기능을 가져온다

- [DTO모듈](https://github.com/jhsong2580/subject/blob/main/core-dto/README.md) :
    - 다른 모듈과 통신하기 위한 공통 DTO 설정을 가져온다.

- [domain모듈](https://github.com/jhsong2580/subject/blob/main/domain-blog/README.md)
    - Database에 CRUD를 하기 위해 DB 서비스 로직 모듈을 가져온다.

---
### 사용한 외부 라이브러리
1. org.mapstruct:mapstruct:1.4.2.Final (Mapper)
   - 사용 모듈 : app-blog, client-kakao, client-naver, domain-blog
   - 사용 이유 : 각 모듈의 DTO와 공통 DTO 사이의 변환을 지원

2. org.mock-server:mockserver-netty:5.12.0 (Mock Server)
   - 사용 모듈 : app-blog, client-kakao, client-naver
   - 사용 이유 : 인수테스트, RestAPI테스트시 실제 Endpoint가 아닌 MockServer의 Endpoint로 테스트

3. com.github.ben-manes.caffeine:caffeine:3.0.0 (In-Memory Cache) 
   - 사용 모듈 : common-cache
   - 사용 이유 : In-Memory Cache를 지원하기 위해 사용