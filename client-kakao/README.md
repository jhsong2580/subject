# Kakao Api Module

- service.KakaoBlogRestService.getBlogs : Kakao Blog API를 사용한다 
- dto.* : Kakao Blog API와 시스템 간 경계에서 사용되는 DTO이다. 
- mapper.* : Kakao Blog API와의 경계에서 사용되는 DTO를 내부 시스템 DTO로 변환해주는 Mapper이다.
- KakaoSetting : client-kakao 모듈의 @ComponentScan과 KakaoConfig를 Loading해주는 역할을 갖는다.

# 사용된 외부 라이브러리
1. Mapstruct의 Mapper를 사용하기위해 적용되었다.
```
    implementation "org.mapstruct:mapstruct:1.4.2.Final"
    annotationProcessor "org.mapstruct:mapstruct-processor:1.4.2.Final"
```

2. Kakao Rest API Mock 서버를 통해 테스트하기위해 적용되었다. (관련코드 : test/java/subject/blog/service/utils/MockServerSetting.java)
```
    testImplementation 'org.mock-server:mockserver-netty:5.12.0'
```
