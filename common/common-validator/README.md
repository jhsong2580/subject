# Validator Module
- 외부로부터 값을 받을때, 값 검증을 도와주는 기능을 제공한다. 

### 사용방법
```
    // sort라는 filed에 들어갈수 있는 값은 Sort Enum의 값을 equalsIgnoreCase(sort) 검증을 한다. 
    // 그렇지 않다면 bindingResult에 FieldError로 나타난다.
    @EnumCheck(message = "Field sort must be either ACCURACY or RECENCY value", enumClass = Sort.class)
    private String sort;
```