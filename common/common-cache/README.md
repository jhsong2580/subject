# In-Memory-Cache Module

### 사용한 외부 라이브러리
- [com.github.ben-manes.caffeine:caffeine:3.0.0](https://github.com/jhsong2580/subject/blob/main/common/common-cache/src/main/java/subject/blog/CacheSetting.java)
  - In-Memory-Cache를 사용하기 위한 라이브러리 

### 사용 방법
- 이 모듈에서 만드는 Cache 는 "blogs"와 "hotKeys" 이다.
  - blogs 
    - 용도 : Kakao / Naver Blog API 조회 결과를 Cache에 담는다.  
    - maximum cache size : 300
    - TTL : 10Sec
  - hotKeys
    - 용도 : Top 10 Query를 Cache에 담아 부하가 높은 쿼리가 발생되는것을 감소시킨다.
    - maximum cache size : 1
    - TTL : 5Sec
- 사용방법
  - @Cacheable(cacheNames = "{blogs / hotKeys}", key = "#{키이름}", unless = "#result == null", cacheManager = "cacheManager")
    - key : @Cacheable이 붙은 함수의 인자값으로 받아온 객체중, key로 설정할 값을 path로 정해주면 된다. 
    - cacheNames : 설정할 cache를 넣어준다. 

# Lock
- ConcurrentHashMap을 통한 Lock 설정
- @GetLock Annotation을 사용한 AOP를 통해 Lock 획득/반환을 한다. 
- 사용방법
  - @GetLock(key = "#blogRequestEvent.blogRequestDTO.query", waitTime = 5)
    - key : 함수의 인자값으로 받아온 객체중, key로 설정할 값을 path로 정해주면 된다. [이 클래스가](https://github.com/jhsong2580/subject/blob/main/common/common-cache/src/main/java/subject/blog/utils/CustomSpringELParser.java) 실제 값으로 변환시켜준다
    - waitTime : 설정한 숫자로 Lock을 획득할 때 까지 대기한다. (default TimeUnit : Seconds)
    - timeUnit : waitTime에 설정한 숫자로, 어떠한 TimeUnit으로 대기할껀지 설정한다 (default : Seconds)
