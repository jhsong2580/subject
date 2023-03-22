# 모듈들이 공통으로 사용하는 DTO, Enum, EventDTO
- BlogRequestDTO : Blog 조회에 대한 공통 DTO
- BlogListResponseDTO, BlogResponseDTO : Blog 조회에 대한 공통 응답 DTO 
- QueryResponseDTO : Top 10 검색어 조회에 대한 공통 응답 DTO
- Sort : Naver / Kakao에서 사용하는 Sort 기준 
- BlogRequestEvent : Blog 조회 Event이며, Listener는 검색어 저장을 한다. 
