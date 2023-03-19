package subject.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import subject.blog.NaverSetting;
import subject.blog.dto.BlogListResponseDTO;
import subject.blog.dto.BlogRequestDTO;
import subject.blog.dto.BlogResponseDTO;
import subject.blog.enums.Sort;
import subject.blog.service.utils.MockServerSetting;

@SpringBootTest(classes = NaverSetting.class,
    properties = "spring.config.location=" +
        "classpath:/application-naver-test.yml"
)
class NaverBlogRestServiceTest {

    @Autowired
    private BlogRestService naverBlogRestService;

    private int port = 8081;
    private String URI = "/v1/search/blog.json";
    private MockServerSetting mockServer;

    @BeforeEach
    public void init() {

        mockServer = new MockServerSetting();
        mockServer.main(
            port, URI, HttpStatus.OK.value(),
            "{\n"
                + "    \"lastBuildDate\": \"Sun, 19 Mar 2023 14:44:36 +0900\",\n"
                + "    \"total\": 1108852,\n"
                + "    \"start\": 1,\n"
                + "    \"display\": 10,\n"
                + "    \"items\": [\n"
                + "        {\n"
                + "            \"title\": \"정선 파크로쉬 (숙암킹 <b>Review</b>)\",\n"
                + "            \"link\": \"https://blog.naver.com/gml7337/223013499416\",\n"
                + "            \"description\": \"Park Roche, Resorts &amp; Wellness 강원도 정선 좋은 기회로 최근에 다녀온 #파크로쉬 <b>Review</b>  강원도 정선군 북평면 중봉길 9-12  가격대는 조식 포함 30~40만원대 ✔️ 리뷰할 객실 Type은 숙암킹 (제일 기본 룸) 주로 서울... \",\n"
                + "            \"bloggername\": \"순간들\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/gml7337\",\n"
                + "            \"postdate\": \"20230212\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"[유틸] Start11 소개(Introduce),리뷰(<b>Review</b>),할인(Sale)... \",\n"
                + "            \"link\": \"https://202psj.tistory.com/2021\",\n"
                + "            \"description\": \"be/1Ef0oVnst5k 소개,리뷰,정보링크(Introduce, <b>Review</b>, Info Link): -https://namu.wiki/w/%EC%8A%A4%ED%83%80... be/hArF2VzLtU8 //영상<b>Review</b> - https://youtu.be/1Ef0oVnst5k //영상<b>Review</b> 할인정보관련[Platform Sale... \",\n"
                + "            \"bloggername\": \"알레폰드의 IT, 전자, 전기 이모저모\",\n"
                + "            \"bloggerlink\": \"https://202psj.tistory.com/\",\n"
                + "            \"postdate\": \"20230101\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"영국 러쉬 / 기념품 자석 / London <b>Review</b> Bookshop 에코백\",\n"
                + "            \"link\": \"https://blog.naver.com/6_bbang/222900061184\",\n"
                + "            \"description\": \"3 런던 1일차 코스 숙소→포토벨로마켓&amp;노팅힐북샵→DAUNT BOOKS→러쉬→London <b>Review</b> Bookshop→Flat Iron... London <b>Review</b> Bookshop 여기도 에코백 사러옴ㅋㅋㅋ 서점은 역시 그래24지 포인트 엄청 잘 쌓임 서양책들은... \",\n"
                + "            \"bloggername\": \"Bonhomie:친절한 마음씨\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/6_bbang\",\n"
                + "            \"postdate\": \"20221014\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"[현대이지웰]2022사업보고서.<b>review</b>.230316\",\n"
                + "            \"link\": \"https://blog.naver.com/kolosh/223046962853\",\n"
                + "            \"description\": \"재무제표 <b>review</b>.질문정리 &lt; 기타채권 &gt; - 전체 120억 가량증가 - 미수금 37% 증가 , 대손금 189%증가 =&gt; 미수금 대비 대손충당금 증가액이 너무 크다. 미수금 상태 체크 필요. &lt; 기타포괄-공정가치 평가금융자산... \",\n"
                + "            \"bloggername\": \"지오파\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/kolosh\",\n"
                + "            \"postdate\": \"20230317\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"상동 피부관리 끌랑 뷰티라운지 <b>review</b>\",\n"
                + "            \"link\": \"https://blog.naver.com/wjddk3136/223047766613\",\n"
                + "            \"description\": \"상동 피부관리 끌랑 뷰티라운지 <b>review</b> 최근에 방문한 상동 피부관리 끌랑 뷰티라운지. 오늘은 또 어떤 관리를 받을까~~ 친절하게 맞이해주신 쓰앵님. 앉아서 상담 시작! 제가 지금까지 다녀본 모든 샵 통틀어서... \",\n"
                + "            \"bloggername\": \"our record.\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/wjddk3136\",\n"
                + "            \"postdate\": \"20230317\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"◈ 씨에스윈드(112610) 4Q22 <b>Review</b> : 변하지 않는 큰 그림... \",\n"
                + "            \"link\": \"https://blog.naver.com/allsix6/223016136074\",\n"
                + "            \"description\": \"◈ 씨에스윈드(112610) 4Q22 <b>Review</b> : 변하지 않는 큰 그림 ▶️ 4분기는 일회성 비용으로 예상치... 1만원(유지)｜다올 그린인프라·2차전지 전혜영 ☎2184-2311] ★ 4Q22 <b>Review</b> : 지금은 성장통을 겪는... \",\n"
                + "            \"bloggername\": \"Day by Day\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/allsix6\",\n"
                + "            \"postdate\": \"20230215\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"[<b>Review</b>][디월트] DWHT65101 스크류드라이버 4 PCS 세트\",\n"
                + "            \"link\": \"https://blog.naver.com/osk0825/222951996283\",\n"
                + "            \"description\": \"그중 스크류드라이버 세트에 대한 간단 <b>Review</b> 다. DWHT65101 스크류드라이버 4 PCS 세트 가격은 10,480원에 구입했다. 구성품 스큐류드라이버 십자 2개, 스큐류드라이버 일자 2개 총 4개로 구성되어... \",\n"
                + "            \"bloggername\": \"Memories become Stories.\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/osk0825\",\n"
                + "            \"postdate\": \"20221210\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"M109A2 : Kinetic model, <b>Review</b>\",\n"
                + "            \"link\": \"https://blog.naver.com/sfpdswat/223019867955\",\n"
                + "            \"description\": \"model https://blog.naver.com/lpin/70141903541 ■ 리뷰 : Armorama https://archive.armorama.com/<b>review</b>/8305... com/sfpdswat/223018571679 M109A2 : Kinetic model, <b>Review</b> https://blog.naver.com/sfpdswat/223019867955 #프라모델... \",\n"
                + "            \"bloggername\": \"Bijouryu\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/sfpdswat\",\n"
                + "            \"postdate\": \"20230218\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"CJ 비비고 호족반 NY 양념갈비만두 내돈내산 <b>review</b>\",\n"
                + "            \"link\": \"https://blog.naver.com/frutta520/222987932083\",\n"
                + "            \"description\": \"CJ 비비고 호족반 NY 양념갈비만두 내돈내산 <b>review</b> 몇 주 전 부터 주니가 갈비만두 좀 사다줘~!를... 분들은 한 번 경험 해보시는 걸 추천 드려요 :) ※본 글은 직접 구매한 상품을 맛 보고 솔직하게 쓴 <b>review</b> 입니다!\",\n"
                + "            \"bloggername\": \"매일하는 노후준비\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/frutta520\",\n"
                + "            \"postdate\": \"20230118\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"title\": \"밴드씬의 아이콘 SURL(설) 전국투어 콘서트 ‘<b>review</b> of us’... \",\n"
                + "            \"link\": \"https://blog.naver.com/withinnews/222999409738\",\n"
                + "            \"description\": \"전국투어 ‘<b>review</b> of us’ 2달 간의 대장정 성황리에 종료...다음 행보는? K밴드의 아이콘 SURL(설)이... 설은 지난 28, 29일 서울 마포구 서강대학교 메리홀 대극장에서 개최한 ‘<b>review</b> of us’ 서울 공연을... \",\n"
                + "            \"bloggername\": \"위드인뉴스\",\n"
                + "            \"bloggerlink\": \"blog.naver.com/withinnews\",\n"
                + "            \"postdate\": \"20230130\"\n"
                + "        }\n"
                + "    ]\n"
                + "}"
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
        BlogListResponseDTO blogListResponseDTO = naverBlogRestService.getBlogs(blogRequestDTO);
        List<BlogResponseDTO> documents = blogListResponseDTO.getDocuments();

        //then
        assertThat(documents).extracting("title")
            .contains(
                "정선 파크로쉬 (숙암킹 <b>Review</b>)",
                "[유틸] Start11 소개(Introduce),리뷰(<b>Review</b>),할인(Sale)... ",
                "영국 러쉬 / 기념품 자석 / London <b>Review</b> Bookshop 에코백",
                "[현대이지웰]2022사업보고서.<b>review</b>.230316",
                "상동 피부관리 끌랑 뷰티라운지 <b>review</b>",
                "◈ 씨에스윈드(112610) 4Q22 <b>Review</b> : 변하지 않는 큰 그림... ",
                "[<b>Review</b>][디월트] DWHT65101 스크류드라이버 4 PCS 세트",
                "M109A2 : Kinetic model, <b>Review</b>",
                "CJ 비비고 호족반 NY 양념갈비만두 내돈내산 <b>review</b>",
                "밴드씬의 아이콘 SURL(설) 전국투어 콘서트 ‘<b>review</b> of us’... "
            );
    }
}