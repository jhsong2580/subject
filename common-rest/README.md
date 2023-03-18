# Rest Client Module
- RestTemplate을 이용한 기능을 제공한다. 

### 사용방법
```
@Component
public YourClass {
    private RestClient restClient;
    
    public YourClass(RestClient restClient) {
        this.restClient = restClient; 
    }
    
    public void yourMethodWithoutGeneric() {
    
        Map<String, String> headers = new HashMap<>(); 
        headers.put(HttpHeaders.AUTHORIZATION, "Your Key");
        
        ResponseEntity<String> request = restClient
            .uri("http://dapi.kakao.com/v2/search/blog")
            .queryParam("query", "카카오")
            .uriBuild()
            .method(HttpMethod.GET)
            .headers(headers)
            .setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
            .build()
            .request();
    } 
    
    public void yourMethodWithGeneric() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.AUTHORIZATION, "KakaoAK 7e1c3d53c917a4450aeff99691f0690f");

        ResponseEntity<Object> request = restClient
            .uri("http://dapi.kakao.com/v2/search/blog")
            .queryParam("query", "카카오")
            .uriBuild()
            .method(HttpMethod.GET)
            .headers(headers)
            .setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
            .build()
            .request(Object.class);
    }
}
```