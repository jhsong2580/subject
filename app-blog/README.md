# app-blog 모듈
- 블로그 검색 서비스의 실행 단위이다.

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