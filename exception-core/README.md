# Exception 처리 모듈 

- IllegalArgumentException이 발생하면 ControllerAdvice에서 400 BAD REQUEST로 전환한다. 
- RuntimeException이 발생하면 ControllerAdvice에서 500 INTERNAL SERVER ERROR로 전환한다. 