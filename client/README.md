# 외부 API 모듈

- client-core : API의 기본 구성을 정의한다 

- [client-kakao](https://github.com/jhsong2580/subject/blob/main/client/client-kakao/README.md)
  - Kakao Rest API에 대한 기본 설정과 서비스 로직을 갖는다.
  - KakaoBlogRestService.class 를 통해 Kakao Rest API를 사용한다.

- [client-naver](https://github.com/jhsong2580/subject/blob/main/client/client-naver/README.md) 
    - Naver Rest API에 대한 기본 설정과 서비스 로직을 갖는다.
    - NaverBlogRestService.class 를 통해 Naver Rest API를 사용한다.

### 주의사항
- client-core와는 항상 의존관계를 맺어야 하며, 그 외 kakao, naver 모듈은 선택적으로 의존관계를 맺어도 상관 없다. 