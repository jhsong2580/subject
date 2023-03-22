# Naver Api Module

- service.NaverBlogRestService.getBlogs : Naver Blog API를 사용한다
- dto.* : Naver Blog API와 시스템 간 경계에서 사용되는 DTO이다.
- mapper.* : Naver Blog API와의 경계에서 사용되는 DTO를 내부 시스템 DTO로 변환해주는 Mapper이다.
- NaverSetting : client-Naver 모듈의 @ComponentScan과 NaverConfig를 Loading해주는 역할을 갖는다.

### 사용 방법
- 외부에선 NaverBlogRestService만 사용한다.
- Profile에 "naver"를 추가하여 Application을 구동시킨다.

### 사용한 외부 라이브러리
1. Mapstruct의 Mapper를 사용하기위해 적용되었다.
```
    implementation "org.mapstruct:mapstruct:1.4.2.Final"
    annotationProcessor "org.mapstruct:mapstruct-processor:1.4.2.Final"
```

2. Naver Rest API Mock 서버를 통해 테스트하기위해 적용되었다. (관련코드 : test/java/subject/blog/service/utils/MockServerSetting.java)
```
    testImplementation 'org.mock-server:mockserver-netty:5.12.0'
```

