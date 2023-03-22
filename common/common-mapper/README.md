# Mapstruct Mapper Module 

## Ground Rule 
- DTO는 Setter를 제거하자 
- Mapstruct의 Mapper는 Builder를 통해 Destination 객체에 값 설정이 가능하니, @AllArgConstructor & @Builder를 설정하자 

### OneWayMapper 
- Src -> Dst 방향의 Mapper

### OneWayTwoSourceMapper
- 2개의 Src를 통해 Dst 객체를 만드는 Mapper