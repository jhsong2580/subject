package subject.blog.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class UrlEncoderTest {

    @ParameterizedTest
    @CsvSource(
        value = {
            "테스트뱅트,%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%B1%85%ED%8A%B8",
            "쿼리1,%EC%BF%BC%EB%A6%AC1",
            "테@스#트$쿼!리%,%ED%85%8C%40%EC%8A%A4%23%ED%8A%B8%24%EC%BF%BC%21%EB%A6%AC%25",
            "t!es@tQu2Ry,t%21es%40tQu2Ry",
            "t est 쿼 리 @,t+est+%EC%BF%BC+%EB%A6%AC+%40"
        },
        delimiter = ','
    )
    public void normalUtf8EncodeTest(String source, String expect) {
        //when
        String encodedResult = UrlEncoder.encode(source, "UTF-8");

        //then
        assertThat(encodedResult).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(
        value = {
            "%ED%85%8C%EC%8A%A4%ED%8A%B8%EB%B1%85%ED%8A%B8,테스트뱅트",
            "%EC%BF%BC%EB%A6%AC1,쿼리1",
            "%ED%85%8C%40%EC%8A%A4%23%ED%8A%B8%24%EC%BF%BC%21%EB%A6%AC%25,테@스#트$쿼!리%",
            "t%21es%40tQu2Ry,t!es@tQu2Ry",
            "t+est+%EC%BF%BC+%EB%A6%AC+%40,t est 쿼 리 @"
        },
        delimiter = ','
        , nullValues = "null"
    )
    public void normalUtf8DecodeTest(String source, String expect) {
        //when
        String decodedResult = UrlEncoder.decode(source, "UTF-8");

        //then
        assertThat(decodedResult).isEqualTo(expect);
    }
}