package subject.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import subject.blog.KakaoSetting;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.enums.Sort;
import subject.blog.service.utils.MockServerSetting;

@SpringBootTest(classes = KakaoSetting.class,
    properties = "spring.config.location=" +
        "classpath:/application-kakao-test.yml"
)
class KakaoBlogRestServiceTest {

    @Autowired
    private KakaoBlogRestService kakaoBlogRestService;
    private MockServerSetting mockServer;
    private int port = 8081;
    private String URI = "/v2/search/blog";

    @BeforeEach
    public void init() {

        mockServer = new MockServerSetting();
        mockServer.main(
            port, URI, HttpStatus.OK.value(),
            "{\"documents\":[{\"blogname\":\"내나이가 어때서\",\"contents\":\"\u200B 코로나 격리해제 3일 전 눈뜨자마자 피자가 먹고싶었다. \u200B \u200B 맛있는 피자.. 먹었으면 운동.. 배부르고 너무 심심해서 네이버 지식인에 ‘코로나 걸렸을 때 운동’ 검색해보고 피티선생님한테 운동할까요 물어봤다. 하지 말라고 하시길래 바로 했음 \u200B \u200B 서울에 있는 딸램의 응원 \u200B \u200B 결막염이 잠깐 있었는지 윙크하는 시루...\",\"datetime\":\"2022-06-06T18:00:00.000+09:00\",\"thumbnail\":\"https://search3.kakaocdn.net/argon/130x130_85_c/KeqA2DuVY9J\",\"title\":\"mytitle\",\"url\":\"https://blog.naver.com/jjtjdals/222761792052\"},{\"blogname\":\"모-코드\",\"contents\":\"DOCTYPE html\\u0026gt; \\u0026lt;html\\u0026gt; \\u0026lt;head\\u0026gt; \\u0026lt;meta charset=\\u0026#34;UTF-8\\u0026#34;\\u0026gt; \\u0026lt;title\\u0026gt;Insert title here\\u0026lt;/title\\u0026gt; \\u0026lt;/head\\u0026gt; \\u0026lt;body\\u0026gt; \\u0026lt;h1\\u0026gt;\\u003cb\\u003eë\\u003c/b\\u003e©\u0094\\u003cb\\u003eë\\u003c/b\\u003e\u0089´ ëª©\\u0026lt;/h1\\u0026gt; \\u0026lt;table\\u0026gt; \\u0026lt;tr\\u0026gt; \\u0026lt;td\\u0026gt;ë²\u0088\\u003cb\\u003eí\\u003c/b\\u003e\u0098¸\\u0026lt;/td\\u0026gt; \\u0026lt;td\\u0026gt;\\u003cb\\u003eì\\u003c/b\\u003e\u009D´\\u003cb\\u003eë\\u003c/b\\u003e¦\u0084 \\u0026lt;/td\\u0026gt; \\u0026lt;td\\u0026gt;ê°\u0080ê²©\\u0026lt;/td\\u0026gt; \\u0026lt;/tr\\u0026gt; \\u0026lt;tr\\u0026gt; \\u0026lt;td\\u0026gt;1\\u0026lt;/td\\u0026gt; \\u0026lt;td\\u0026gt;\\u003cb\\u003eì\\u003c/b\\u003e\u0095\u0084\\u003cb\\u003eë\\u003c/b\\u003e©\u0094\\u003cb\\u003eë\\u003c/b\\u003e¦¬ì¹´\\u003cb\\u003eë\\u003c/b\\u003e\u0085¸\\u0026lt;/td\\u0026gt; \\u0026lt;td\\u0026gt;5000\\u0026lt;/td\\u0026gt; \\u0026lt;/tr\\u0026gt; \\u0026lt;tr\\u0026gt; \\u0026lt;td\\u0026gt;2\\u0026lt;/td\\u0026gt; \\u0026lt;td\\u0026gt;ì¹´\\u003cb\\u003eí\\u003c/b\\u003e\u008E\u0098\\u003cb\\u003eë\\u003c/b\\u003e\u009D¼\\u003cb\\u003eë\\u003c/b\\u003e\u0096¼\\u0026lt;/td...\",\"datetime\":\"2023-02-14T19:44:22.000+09:00\",\"thumbnail\":\"https://search2.kakaocdn.net/argon/130x130_85_c/E88O7EUBv93\",\"title\":\"TIL : 50번째- 230214 [2-2-화]\",\"url\":\"http://mdlg.tistory.com/119\"},{\"blogname\":\"카브이\",\"contents\":\"\\u003cb\\u003eí\\u003c/b\\u003e�¬\\u003cb\\u003eí\\u003c/b\\u003e‹°\\u003cb\\u003eì\\u003c/b\\u003e§€ \\u003cb\\u003eì\\u003c/b\\u003e‹ \\u003cb\\u003eí\\u003c/b\\u003e˜• \\u003cb\\u003eí\\u003c/b\\u003eˆ¬\\u003cb\\u003eì\\u003c/b\\u003e‹¼ ë¹„êµ�ê°€ \\u003cb\\u003eë\\u003c/b\\u003e� ê¹Œìš”? [ëª¨ë¹„\\u003cb\\u003eí\\u003c/b\\u003e‹°] KIA The all-new Sportage HEV ê¸°\\u003cb\\u003eì\\u003c/b\\u003e•„\\u003cb\\u003eì\\u003c/b\\u003e—�\\u003cb\\u003eì\\u003c/b\\u003e„œ ìƒˆ\\u003cb\\u003eë\\u003c/b\\u003e¡\u00ADê²Œ \\u003cb\\u003eì\\u003c/b\\u003e¶œì‹œë�œ \\u003cb\\u003eì\\u003c/b\\u003e¤€\\u003cb\\u003eì\\u003c/b\\u003e¤‘\\u003cb\\u003eí\\u003c/b\\u003e˜• SUV ìŠ¤\\u003cb\\u003eí\\u003c/b\\u003e�¬\\u003cb\\u003eí\\u003c/b\\u003e‹°\\u003cb\\u003eì\\u003c/b\\u003e§€ \\u003cb\\u003eí\\u003c/b\\u003e•˜\\u003cb\\u003eì\\u003c/b\\u003e�´\\u003cb\\u003eë\\u003c/b\\u003e¸Œë¦¬\\u003cb\\u003eë\\u003c/b\\u003e“œ ëª¨\\u003cb\\u003eë\\u003c/b\\u003e�¸ê³¼ \\u003cb\\u003eì\\u003c/b\\u003e�˜ì¹´\\u003cb\\u003eì\\u003c/b\\u003e—�\\u003cb\\u003eì\\u003c/b\\u003e„œ ë¹Œë...\",\"datetime\":\"2022-12-12T13:31:00.000+09:00\",\"thumbnail\":\"\",\"title\":\"기아 스포티지와 현대 투싼 독일 자동차 전문지 하이브리드카 비교평가 1위, 2위 선정!\",\"url\":\"https://post.naver.com/viewer/postView.naver?memberNo=34624787\\u0026volumeNo=34938363\"},{\"blogname\":\"공고마스터의 추천채용공고\",\"contents\":\"근무지 :\u200B 인천광역시 부평구 송도시\u200B \u200B \u200B 접수기간 :\u200B 2023년 3월 9일(목) 24:00까지, 기간엄수!\u200B \u200B 접수방법 :\u200B 셀트리온 채용 홈페이지 온라인 입사지원 \\u003cb\\u003eì\\u003c/b\\u003e \u0080\\u003cb\\u003eí\\u003c/b\\u003e\u008A¸\\u003cb\\u003eë\\u003c/b\\u003e¦¬\\u003cb\\u003eì\\u003c/b\\u003e\u0098¨ Prev Next 채용공고 전체 ( 3 ) 공채 ( 0 ) 수시 ( 3 ) 지난채용 ( 9 ) 수시 ㈜셀트리온 – 연구개발부문 분야 신입/경력 수시채용 D-15 수시 ㈜...\",\"datetime\":\"2023-02-25T17:43:00.000+09:00\",\"thumbnail\":\"\",\"title\":\"[채용공고] 셀트리온 생산직 채용공고 + 자소서 항목 (대기업 생산직)\",\"url\":\"https://blog.naver.com/tnckd125/223027425509\"},{\"blogname\":\"The restricted area\",\"contents\":\"과부하될 수 있으므로 미리 입사 지원을 완료!! ※최종 제출 이후 수정 불가 -\\u0026gt; 신중하게 작성!! ●접수 방법 : 온라인 입사지원 ※E-mail 접수XXX \\u003cb\\u003eì\\u003c/b\\u003e \u0080\\u003cb\\u003eí\\u003c/b\\u003e\u008A¸\\u003cb\\u003eë\\u003c/b\\u003e¦¬\\u003cb\\u003eì\\u003c/b\\u003e\u0098¨ Prev Next 채용공고 전체 ( 3 ) 공채 ( 0 ) 수시 ( 3 ) 지난채용 ( 9 ) 수시 ㈜셀트리온 – 연구개발부문 분야 신입/경력 수시채용 D-15 수시 ㈜...\",\"datetime\":\"2023-02-25T21:38:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/H0YKMk93sxi\",\"title\":\"(대기업 생산직)셀트리온 생산직 채용 공고\",\"url\":\"https://blog.naver.com/boble97/223027598338\"},{\"blogname\":\"정겨운 재가방문요양센터\",\"contents\":\"의료급여 및 경감 2,190) * 기초생활수급자 본인부담금 면제 * 등급변경신청, 재신청: 전액본인부담 \u200B *치매진단서 첨부파일 ì¹_\\u003cb\\u003eë\\u003c/b\\u003e§¤\\u003cb\\u003eì\\u003c/b\\u003e§_\\u003cb\\u003eë\\u003c/b\\u003e_¨ \\u003cb\\u003eí\\u003c/b\\u003e__\\u003cb\\u003eì\\u003c/b\\u003e_¸\\u003cb\\u003eì\\u003c/b\\u003e_´ \\u003cb\\u003eí\\u003c/b\\u003e__\\u003cb\\u003eì\\u003c/b\\u003e__\\u003cb\\u003eí\\u003c/b\\u003e__ \\u003cb\\u003eì\\u003c/b\\u003e__\\u003cb\\u003eì\\u003c/b\\u003e__ \\u003cb\\u003eë\\u003c/b\\u003e__\\u003cb\\u003eí\\u003c/b\\u003e__ \\u003cb\\u003eì\\u003c/b\\u003e__ê²¬ (1)_1 .jpg 파일 다운로드 *의사소견서 첨부파일 \\u003cb\\u003eì\\u003c/b\\u003e__\\u003cb\\u003eì\\u003c/b\\u003e___1 .jpg 파일 다운로드 첨부파일 \\u003cb\\u003eì\\u003c/b\\u003e__\\u003cb\\u003eì\\u003c/b\\u003e___2 .jpg 파일...\",\"datetime\":\"2023-02-24T13:34:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/52dwdGL7Jov\",\"title\":\"[노인장기요양보험]의사소견서 서식 및 발급비용 변경 안내\",\"url\":\"https://blog.naver.com/jungcare21/223026275869\"},{\"blogname\":\"프리미엄독서실 ‘라움’ 목은점\",\"contents\":\"있으신 분들은, 아래 링크를 통해 홈페이지에 접속하신 후, 채용관련 유의사항 및 지원 방식 등 잘 확인하시고, 지원해 보시면 좋을 것 같아요 \uD83D\uDE4C\uD83D\uDE4C \u200B \\u003cb\\u003eì\\u003c/b\\u003e \u0080\\u003cb\\u003eí\\u003c/b\\u003e\u008A¸\\u003cb\\u003eë\\u003c/b\\u003e¦¬\\u003cb\\u003eì\\u003c/b\\u003e\u0098¨\\u003cb\\u003eì\\u003c/b\\u003e \u009C\\u003cb\\u003eì\\u003c/b\\u003e\u0095½ 채용사이트 채용공고 채용공고 ( 2 ) 신입 ( 2 ) 경력 ( 2 ) 인턴 ( 0 ) (주)셀트리온제약 신입/경력 수시채용 D-7 채용문의 [23.03.05] 대학...\",\"datetime\":\"2023-03-05T20:49:00.000+09:00\",\"thumbnail\":\"https://search4.kakaocdn.net/argon/130x130_85_c/IeR9f1VrNH7\",\"title\":\"[라움독서실] (주)셀트리온제약 신입 및 경력 수시채용 ( ~ 3/12)\",\"url\":\"https://blog.naver.com/kwondh88/223035535581\"},{\"blogname\":\"Return_Farming_함양\",\"contents\":\"\\u003cb\\u003eì\\u003c/b\\u003e¢… 2ê¸‰\\u003cb\\u003eì\\u003c/b\\u003e\u009D¸ \\u003cb\\u003eí\\u003c/b\\u003e\u0081°ê¸°ëŸ¬ê¸°, \\u003cb\\u003eì\\u003c/b\\u003e‡ ê¸°ëŸ¬ê¸°, ìž¬\\u003cb\\u003eë\\u003c/b\\u003e‘\u0090\\u003cb\\u003eë\\u003c/b\\u003e£¨ë¯¸ê°€ ìž¥\\u003cb\\u003eí\\u003c/b\\u003e•\u00ADìŠµì§€\\u003cb\\u003eë\\u003c/b\\u003e¥¼ \\u003cb\\u003eì\\u003c/b\\u003e°¾ëŠ”\\u003cb\\u003eë\\u003c/b\\u003e‹¤. \\u003cb\\u003eì\\u003c/b\\u003e\u009D´\\u003cb\\u003eë\\u003c/b\\u003e“¤\\u003cb\\u003eì\\u003c/b\\u003e\u009D„ \\u003cb\\u003eí\\u003c/b\\u003e\u008F¬\\u003cb\\u003eí\\u003c/b\\u003e•¨\\u003cb\\u003eí\\u003c/b\\u003e•´ \\u003cb\\u003eë\\u003c/b\\u003e§\u0090\\u003cb\\u003eë\\u003c/b\\u003e˜¥ê°€\\u003cb\\u003eë\\u003c/b\\u003e¦¬\\u003cb\\u003eì\\u003c/b\\u003e™€ ë¹„\\u003cb\\u003eë\\u003c/b\\u003e‘˜ê¸°\\u003cb\\u003eì\\u003c/b\\u003e¡°\\u003cb\\u003eë\\u003c/b\\u003e¡±\\u003cb\\u003eì\\u003c/b\\u003e\u009D´, \\u003cb\\u003eë\\u003c/b\\u003e¶‰\\u003cb\\u003eì\\u003c/b\\u003e\u009D€\\u003cb\\u003eë\\u003c/b\\u003e°œë§\u0090\\u003cb\\u003eë\\u003c/b\\u003e˜¥ê²Œ \\u003cb\\u003eë\\u003c/b\\u003e“± 20\\u003cb\\u003eì\\u003c/b\\u003e—¬\\u003cb\\u003eì\\u003c/b\\u003e¢…\\u003cb\\u003eì\\u003c/b\\u003e\u009D˜ \\u003cb\\u003eë\\u003c/b\\u003e©¸\\u003cb\\u003eì\\u003c/b\\u003e¢…ìœ„ê¸° \\u003cb\\u003eì\\u003c/b\\u003e•¼...\",\"datetime\":\"2011-11-14T20:53:00.000+09:00\",\"thumbnail\":\"https://search1.kakaocdn.net/argon/130x130_85_c/G28jWavaMC\",\"title\":\"mytitle1\",\"url\":\"https://blog.naver.com/haks991/130123844057\"},{\"blogname\":\"슬로우블루 아카이브\",\"contents\":\"1.3|{\\u0026#34;originWidth\\u0026#34;:658,\\u0026#34;originHeight\\u0026#34;:1005,\\u0026#34;style\\u0026#34;:\\u0026#34;alignCenter\\u0026#34;,\\u0026#34;filename\\u0026#34;:\\u0026#34;edited_[ë³\u0084\\u003cb\\u003eì\\u003c/b\\u003e§\u0080 \\u003cb\\u003eì\\u003c/b\\u003e \u009C83\\u003cb\\u003eí\\u003c/b\\u003e\u0098¸\\u003cb\\u003eì\\u003c/b\\u003e\u009D\u00982\\u003cb\\u003eì\\u003c/b\\u003e\u0084\u009C\\u003cb\\u003eì\\u003c/b\\u003e\u008B\u009D] \\u003cb\\u003eì\\u003c/b\\u003e£¼\\u003cb\\u003eí\\u003c/b\\u003e\u0083\u009D\\u003cb\\u003eì\\u003c/b\\u003e\u009E\u0084\\u003cb\\u003eë\\u003c/b\\u003e\u008C\u0080\\u003cb\\u003eì\\u003c/b\\u003e\u0082¬\\u003cb\\u003eì\\u003c/b\\u003e\u0097\u0085\\u003cb\\u003eì\\u003c/b\\u003e\u009E\u0090\\u003cb\\u003eì\\u003c/b\\u003e\u009D\u0098 ê±°\\u003cb\\u003eì\\u003c/b\\u003e£¼\\u003cb\\u003eì\\u003c/b\\u003e£¼\\u003cb\\u003eí\\u003c/b\\u003e\u0083\u009D 1\\u003cb\\u003eì\\u003c/b\\u003e\u0084¸\\u003cb\\u003eë\\u003c/b\\u003e\u008C\u0080 1\\u003cb\\u003eì\\u003c/b\\u003e£¼\\u003cb\\u003eí\\u003c/b\\u003e\u0083\u009D \\u003cb\\u003eí\\u003c/b\\u003e\u008A¹ë¡\u0080\\u003cb\\u003eì\\u003c/b\\u003e \u0081\\u003cb\\u003eì\\u003c/b\\u003e\u009A©\\u003cb\\u003eì\\u003c/b\\u003e\u008B ê³ \\u003cb\\u003eì\\u003c/b\\u003e\u0084\u009C(\\u003cb\\u003eì\\u003c/b\\u003e\u0086\u008C\\u003cb\\u003eë\\u003c/b\\u003e\u0093\u009D\\u003cb\\u003eì\\u003c/b\\u003e\u0084¸ë²\u0095 \\u003cb\\u003eì\\u003c/b\\u003e\u008B\u009C\\u003cb\\u003eí\\u003c/b\\u003e\u0096\u0089ê·\u009Cì¹\u0099).gif\\u0026#34;}_##] 소득세법 시행령 제155조 ⑳ 제167조의3제1항제2호에 따른 주택{같은 호...\",\"datetime\":\"2022-12-11T16:21:00.000+09:00\",\"thumbnail\":\"\",\"title\":\"양도소득세 일시적 1세대 2주택 비과세 증빙서류\",\"url\":\"http://eulbwols.tistory.com/160\"},{\"blogname\":\"공고마스터의 추천채용공고\",\"contents\":\"청주시/진천시\u200B\u200B \u200B \u200B 접수기간 :\u200B\u200B 2022년 11월 20일(목) 24:00까지, 기간엄수!\u200B\u200B \u200B 접수방법 :\u200B\u200B 셀트리온제약 채용 홈페이지 온라인 입사지원서 작성 \\u003cb\\u003eì\\u003c/b\\u003e \u0080\\u003cb\\u003eí\\u003c/b\\u003e\u008A¸\\u003cb\\u003eë\\u003c/b\\u003e¦¬\\u003cb\\u003eì\\u003c/b\\u003e\u0098¨\\u003cb\\u003eì\\u003c/b\\u003e \u009C\\u003cb\\u003eì\\u003c/b\\u003e\u0095½ 채용사이트 채용공고 채용공고 ( 2 ) 신입 ( 2 ) 경력 ( 2 ) 인턴 ( 0 ) (주)셀트리온제약 신입/경력 수시채용 D-13 채용문의 [22.11.07] 공통자격요건...\",\"datetime\":\"2022-11-07T17:31:00.000+09:00\",\"thumbnail\":\"\",\"title\":\"[채용공고] 셀트리온제약 생산직 채용공고 + 자소서 항목 (대기업 생산직)\",\"url\":\"https://blog.naver.com/tnckd125/222922499734\"}],\"meta\":{\"is_end\":false,\"pageable_count\":799,\"total_count\":4439}}"
        );
    }

    @AfterEach
    public void destroy() {
        mockServer.stopMockServer();
    }

    @Test
    public void getBlogListTest() {
        //given (Mock서버이기 때문에 query, page, sort, size 등의 정보는 무시된다)
        BlogRequestDTO blogRequestDTO = BlogRequestDTO.builder()
            .query("query")
            .page(1)
            .sort(Sort.ACCURACY)
            .size(10)
            .build();

        //when
        BlogListResponseDTO blogListResponseDTO = kakaoBlogRestService.getBlogs(blogRequestDTO);
        List<BlogResponseDTO> documents = blogListResponseDTO.getDocuments();

        //then
        assertThat(documents).extracting("title")
            .contains(
                "mytitle",
                "TIL : 50번째- 230214 [2-2-화]",
                "기아 스포티지와 현대 투싼 독일 자동차 전문지 하이브리드카 비교평가 1위, 2위 선정!",
                "[채용공고] 셀트리온 생산직 채용공고 + 자소서 항목 (대기업 생산직)",
                "(대기업 생산직)셀트리온 생산직 채용 공고",
                "[노인장기요양보험]의사소견서 서식 및 발급비용 변경 안내",
                "[라움독서실] (주)셀트리온제약 신입 및 경력 수시채용 ( ~ 3/12)",
                "mytitle1",
                "양도소득세 일시적 1세대 2주택 비과세 증빙서류",
                "[채용공고] 셀트리온제약 생산직 채용공고 + 자소서 항목 (대기업 생산직)"
            );
    }
}