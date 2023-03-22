# 외부 API 모듈

- client-core: API의 기본 구성을 정의한다 

- [client-kakao](https://github.com/jhsong2580/subject/blob/main/client/client-kakao/README.md) : Mapstruct의 Mapper를 사용하기 위한 기본 interface 설정

- [client-naver](https://github.com/jhsong2580/subject/blob/main/client/client-naver/README.md) : Cache를 사용하기 위한 기본 interface 설정

### 주의사항
- client-core와는 항상 의존관계를 맺어야 하며, 그 외 kakao, naver 모듈은 선택적으로 의존관계를 맺어도 상관 없다. 