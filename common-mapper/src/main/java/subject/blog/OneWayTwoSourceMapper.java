package subject.blog;

public interface OneWayTwoSourceMapper<S1, S2, D> {

    D to(S1 s1, S2 s2);
}
