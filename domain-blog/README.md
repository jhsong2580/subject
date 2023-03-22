# domain 모듈 
- db에 대한 직접적인 접근을 맡는다. 
- 사용방법
  - profile에 "blog-domain"을 사용한다. 
  - 탑10 keyword를 가져오는 로직은 QueryDomainService를 직접 접근하면 된다. 
  - keyword저장 로직은 "BlogRequestEvent"를 통해 경유하여 접근한다. 
    - 직접 접근시 동시성 제어 로직이 누락된다. 