package subject.blog;

public interface OneWayMapper <S, D>{
    D to(S s);
}
