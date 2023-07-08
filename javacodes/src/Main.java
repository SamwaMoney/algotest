import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
*   데이터 생성을 수행하는 메인 코드
 */
public class Main {

    // 수업 ID 지정을 위한 변수
    static Long classId;

    // 학관에서 하는 강의들을 담을 리스트
    static ArrayList<Class> hak;
    // ECC에서 하는 강의들을 담을 리스트
    static ArrayList<Class> ecc;
    // 공대에서 하는 강의들을 담을 리스트
    static ArrayList<Class> eng;
    // K-MOOC 강의들을 담을 리스트
    static ArrayList<Class> km;


    // 시간표 ID 지정을 위한 변수
    static Long tableId;

    /*
    메인 함수
     */
    public static void main(String[] args) {

        /*
        이동 난이도 DB 생성
         */
        // 본 코드에서 생성되는 테스트데이터를 채점하기 위해 필요한 조합만을 저장하였습니다
        Map<String, Move> fromHak = new HashMap<>();    // 학관에서 출발하는 이동 난이도를 저장하는 해시맵 선언
        Map<String, Move> fromEcc = new HashMap<>();    // ECC에서 출발하는 이동 난이도를 저장하는 해시맵 선언
        Map<String, Move> fromEng = new HashMap<>();    // 공대에서 출발하는 이동 난이도를 저장하는 해시맵 선언

        // 학관에서 학관으로의 이동 난이도 저장
        fromHak.put("학관", new Move(false, Difficulty.LOW));
        // 학관에서 ECC로의 이동 난이도 저장
        fromHak.put("ECC", new Move(false, Difficulty.MEDIUM));
        // 학관에서 공대로의 이동 난이도 저장
        fromHak.put("공대", new Move(true, Difficulty.HIGH));

        // ECC에서 학관으로의 이동 난이도 저장
        fromEcc.put("학관", new Move(true, Difficulty.HIGH));

        // 공대에서 학관으로의 이동 난이도 저장
        fromEng.put("학관", new Move(false, Difficulty.HIGH));


        /*
        수업 데이터 생성
         */

        // 수업 ID 값 0으로 초기화
        classId = 0L;

        // 학관 기준으로 했을 때 다른 건물들과의 이동난이도 및 오르막길 여부 분포가 적절해서, 학관을 중심으로 데이터를 생성했습니다.
        // 원래 공대 중심으로 하려고 했는데... 공대는 모든 건물과의 이동난이도가 '상'이라서 대신 학관으로 선택했습니다ㅎㅎ...

        // 학관 과목 16개 생성
        hak = hak();

        // 학관과의 이동난이도가 갈 때 '중', 올 때 '상'인 ECC 과목 3개 생성
        ecc = ecc();

        // 학관과의 이동난이도가 왕복 모두 '상'인 공대 과목 3개 생성
        eng = eng();

        // K-MOOC 과목 3개 생성
        km = km();


        /*
        시간표 데이터 생성
         */

        // 시간표 ID 값 0으로 초기화
        tableId = 0L;

        // 1. 강의 개수에 따른 점수 변동 확인을 위한 데이터리스트
        ArrayList<Table> data1 = dataGen1();

        // 2. 공강 일수에 따른 점수 변동 확인을 위한 데이터리스트
        ArrayList<Table> data2 = dataGen2();

        // 3. 1교시 수업 개수에 따른 점수 변동 확인을 위한 데이터리스트
        ArrayList<Table> data3 = dataGen3();

        // 4. 3교시 이상 연강 개수에 따른 점수 변동 확인을 위한 데이터리스트
        ArrayList<Table> data4 = dataGen4();

        // 5. 이동난이도 및 오르막길 차이에 따른 점수 변동 확인을 위한 데이터리스트
        ArrayList<Table> data5 = dataGen5();

        // 6. K-MOOC 개수 차이에 따른 점수 변동 확인을 위한 데이터리스트
        ArrayList<Table> data6 = dataGen6();


        /*
        아래는 데이터가 잘 생성되었는지 테스트하는 프린트 코드
         */

//        printClasses(hak);  // 학관 수업들
//        printClasses(ecc);  // ECC 수업들
//        printClasses(eng);  // 공대 수업들
//        printClasses(km); // kmooc 수업들
//
//        printTables(data1);
//        printTables(data2);
//        printTables(data3);
//        printTables(data4);
//        printTables(data5);
//        printTables(data6);

        System.out.println("========== algorithm test ==========");

        // 공강 개수 count
        System.out.println("공강 개수 count");
        for(Table t : data3) AllClassAlgo.DayoffAlgo(t);

        // 채플 개수 count
        System.out.println("채플 개수 count");
        for(Table t : data2) AllClassAlgo.ChapelAlgo(t);

        // 원격/온라인 개수 count
        System.out.println("원격/온라인 개수 count");
        for(Table t : data6) AllClassAlgo.OnlineAlgo(t);

        // 모든 수업 개수 count
        System.out.println("모든 수업 개수 count");
        for(Table t : data1) AllClassAlgo.AllClassesAlgo(t);

        // 1교시 수업 개수 count
        System.out.println("1교시 수업 개수 count");
        for(Table t : data3) AllClassAlgo.FirstClassAlgo(t);

        // 오전 수업 개수 count
        System.out.println("오전 수업 개수 count");
        for(Table t : data3) AllClassAlgo.MorningClassAlgo(t);

        // 모든 장소 같은지 판별
        System.out.println("모든 장소 같은지 판별");
        for(Table t : data2) AllClassAlgo.sameLocationAlgo(t);

        System.out.println("모든 장소 다른지 판별");
        // 모든 장소 다른지 판별
        for(Table t : data2) AllClassAlgo.differentLocationAlgo(t);

        System.out.println("모두 오전 수업인지 판별");
        for(Table t : data3) AllClassAlgo.allMorningAlgo(t);

        System.out.println("모두 오후 수업인지 판별");
        for(Table t : data2) AllClassAlgo.allAfternoonAlgo(t);

    }

    /*
    이 아래는 수업 데이터 생성 함수
     */

    // 학관 과목 데이터를 생성하는 함수
    public static ArrayList<Class> hak () {
        // 학관 과목 17개 (일반 과목 14개 & 3시간 연강 과목 3개) (수업 31개)
        ArrayList<Class> result = new ArrayList<>();

        classId++;
        result.add(new Class(classId, "학관과목1", "학관", "월", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "학관과목1", "학관", "수", 9, 30, 10, 45));

        classId++;
        result.add(new Class(classId, "학관과목2", "학관", "월", 9, 30, 10, 45));
        classId++;
        result.add(new Class(classId, "학관과목2", "학관", "수", 11, 0, 12, 15));

        classId++;
        result.add(new Class(classId, "학관과목3", "학관", "월", 14, 0, 15, 15));
        classId++;
        result.add(new Class(classId, "학관과목3", "학관", "수", 15, 30, 16, 45));

        classId++;
        result.add(new Class(classId, "학관과목4", "학관", "월", 15, 30, 16, 45));
        classId++;
        result.add(new Class(classId, "학관과목4", "학관", "수", 14, 0, 15, 15));

        classId++;
        result.add(new Class(classId, "학관과목5", "학관", "화", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "학관과목5", "학관", "목", 12, 30, 13, 45));

        classId++;
        result.add(new Class(classId, "학관과목6", "학관", "화", 12, 30, 13, 45));
        classId++;
        result.add(new Class(classId, "학관과목6", "학관", "목", 14, 0, 15, 15));

        classId++;
        result.add(new Class(classId, "학관과목7", "학관", "월", 8, 0, 9, 15));
        classId++;
        result.add(new Class(classId, "학관과목7", "학관", "수", 9, 30, 10, 45));

        classId++;
        result.add(new Class(classId, "학관과목8", "학관", "월", 9, 30, 10, 45));
        classId++;
        result.add(new Class(classId, "학관과목8", "학관", "수", 8, 0, 9, 15));

        classId++;
        result.add(new Class(classId, "학관과목9", "학관", "화", 8, 0, 9, 15));
        classId++;
        result.add(new Class(classId, "학관과목9", "학관", "목", 9, 30, 10, 45));

        classId++;
        result.add(new Class(classId, "학관과목10", "학관", "화", 9, 30, 10, 45));
        classId++;
        result.add(new Class(classId, "학관과목10", "학관", "목", 8, 0, 9, 15));

        classId++;
        result.add(new Class(classId, "학관과목11", "학관", "월", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "학관과목11", "학관", "수", 14, 0, 15, 15));

        classId++;
        result.add(new Class(classId, "학관과목12", "학관", "월", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "학관과목12", "학관", "수", 11, 0, 12, 15));

        classId++;
        result.add(new Class(classId, "학관과목13", "학관", "화", 14, 0, 15, 15));
        classId++;
        result.add(new Class(classId, "학관과목13", "학관", "수", 15, 30, 16, 45));

        classId++;
        result.add(new Class(classId, "학관과목14", "학관", "화", 14, 0, 15, 15));
        classId++;
        result.add(new Class(classId, "학관과목14", "학관", "목", 15, 30, 16, 45));

        classId++;
        result.add(new Class(classId, "학관과목15", "학관", "화", 11, 0, 13, 45));

        classId++;
        result.add(new Class(classId, "학관과목16", "학관", "목",  12, 30, 15, 15));

        classId++;
        result.add(new Class(classId, "학관과목17", "학관", "금",  11, 0, 13, 45));

        return result;

    }

    // ecc 과목 3개를 생성하는 함수
    public static ArrayList<Class> ecc () {
        // 학관과의 이동난이도가 갈 때 '중', 올 때 '상'인 ECC 과목 3개 (수업 6개)
        ArrayList<Class> result = new ArrayList<>();

        // 학관과목1 의 위치만 ECC로 바꾼 것
        classId++;
        result.add(new Class(classId, "ECC과목1", "ECC", "월", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "ECC과목1", "ECC", "수", 9, 30, 10, 45));

        // 학관과목3 의 위치만 ECC로 바꾼 것
        classId++;
        result.add(new Class(classId, "ECC과목2", "ECC", "월", 14, 0, 15, 15));
        classId++;
        result.add(new Class(classId, "ECC과목2", "ECC", "수", 15, 30, 16, 45));

        // 학관과목5 의 위치만 ECC로 바꾼 것
        classId++;
        result.add(new Class(classId, "ECC과목3", "ECC", "화", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "ECC과목3", "ECC", "목", 12, 30, 13, 45));

        return result;
    }

    // 공대 과목 3개를 생성하는 함수
    public static ArrayList<Class> eng() {
        // 학관과의 이동난이도가 왕복 모두 '상'인 공대 과목 3개 (수업 6개)
        ArrayList<Class> result = new ArrayList<>();

        // 학관과목1 의 위치만 공대로 바꾼 것
        classId++;
        result.add(new Class(classId, "공대과목1", "공대", "월", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "공대과목1", "공대", "수", 9, 30, 10, 45));

        // 학관과목3 의 위치만 공대로 바꾼 것
        classId++;
        result.add(new Class(classId, "공대과목2", "공대", "월", 14, 0, 15, 15));
        classId++;
        result.add(new Class(classId, "공대과목2", "공대", "수", 15, 30, 16, 45));

        // 학관과목5 의 위치만 공대로 바꾼 것
        classId++;
        result.add(new Class(classId, "공대과목3", "공대", "화", 11, 0, 12, 15));
        classId++;
        result.add(new Class(classId, "공대과목3", "공대", "목", 12, 30, 13, 45));


        return result;
    }

    // K-MOOC 과목 3개를 생성하는 함수
    public static ArrayList<Class> km() {
        // K-MOOC 수업 3개
        ArrayList<Class> result = new ArrayList<>();

        classId++;
        result.add(new Class(classId, "케이묵1"));
        classId++;
        result.add(new Class(classId, "케이묵2"));
        classId++;
        result.add(new Class(classId, "케이묵3"));

        return result;

    }

    /*
    이 아래는 시간표 데이터 생성 함수
     */

    // 수업을 시간표의 요일별 리스트에 집어넣는 함수
    public static Table addClass (Table t, ArrayList<Class> list) {

        for(Class c : list) {
            switch (c.weekday) {
                case "월":
                    t.monday.add(c);
                    break;
                case "화":
                    t.tuesday.add(c);
                    break;
                case "수":
                    t.wednesday.add(c);
                    break;
                case "목":
                    t.thursday.add(c);
                    break;
                case "금":
                    t.friday.add(c);
                    break;
            }
        }

        return t;

    }

    // 강의 개수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen1 () {
        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // 강의 1개짜리 시간표
        tableId++;
        temp = new Table(tableId);
        
        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 강의 2개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 강의 3개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 강의 4개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 데이터리스트 리턴
        return result;
    }

    // 공강 일수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen2 () {
        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // 공강 0일짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(30));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 공강 1일짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 공강 2일짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(28));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 공강 3일짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 공강 4일짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(28));

        temp = addClass(temp, temp.classList);

        result.add(temp);


        // 데이터리스트 리턴
        return result;
    }

    // 1교시 수업 개수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen3 () {
        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // 1교시 수업 0개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 1교시 수업 1개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 1교시 수업 2개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 1교시 수업 3개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(16));
        temp.classList.add(hak.get(17));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 1교시 수업 4개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(18));
        temp.classList.add(hak.get(19));

        temp.classList.add(hak.get(16));
        temp.classList.add(hak.get(17));

        temp = addClass(temp, temp.classList);

        result.add(temp);


        // 데이터리스트 리턴
        return result;
    }

    // 3교시 이상 연강 개수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen4 () {
        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // 3교시 이상 연강 수업 0개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 3교시 이상 연강 수업 1개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(20));
        temp.classList.add(hak.get(21));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 3교시 이상 연강 수업 2개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(22));
        temp.classList.add(hak.get(23));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 3교시 이상 연강 수업 3개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(24));
        temp.classList.add(hak.get(25));

        temp.classList.add(hak.get(22));
        temp.classList.add(hak.get(23));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 3교시 이상 연강 수업 4개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(26));
        temp.classList.add(hak.get(27));

        temp.classList.add(hak.get(22));
        temp.classList.add(hak.get(23));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 3교시 이상 연강 수업 4개짜리 시간표 (3시간짜리 강의 2개를 포함)
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(12));
        temp.classList.add(hak.get(13));

        temp.classList.add(hak.get(14));
        temp.classList.add(hak.get(15));

        temp.classList.add(hak.get(26));
        temp.classList.add(hak.get(27));

        temp.classList.add(hak.get(22));
        temp.classList.add(hak.get(23));

        temp.classList.add(hak.get(28));

        temp.classList.add(hak.get(29));

        temp = addClass(temp, temp.classList);

        result.add(temp);


        // 데이터리스트 리턴
        return result;

    }

    // 이동난이도 차이에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen5 () {
        // 이 함수 내의 모든 시간표는 엑셀 파일의 "기본 시간표 형태"를 따름
        // "기본 시간표 형태"에서, 1번, 3번, 5번 강의의 강의위치만(학관/ECC/공대) 바꾸어 데이터를 생성하였음
        // 즉, 모든 시간표에서 직전직후 수업간 이동은 총 6번

        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // 이동난이도 하 6회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 하 4회, 중 1회, 상 1회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(ecc.get(0));
        temp.classList.add(ecc.get(1));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 하 4회, 상 2회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(eng.get(0));
        temp.classList.add(eng.get(1));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 하 2회, 중 1회, 상 3회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(ecc.get(0));
        temp.classList.add(ecc.get(1));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(eng.get(2));
        temp.classList.add(eng.get(3));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 하 2회, 중 2회, 상 2회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(ecc.get(0));
        temp.classList.add(ecc.get(1));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(ecc.get(2));
        temp.classList.add(ecc.get(3));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));
        // 학관 <-> 학관 (하 2회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 중 3회, 상 3회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(ecc.get(0));
        temp.classList.add(ecc.get(1));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(ecc.get(2));
        temp.classList.add(ecc.get(3));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(eng.get(4));
        temp.classList.add(eng.get(5));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 중 2회, 상 4회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(eng.get(0));
        temp.classList.add(eng.get(1));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(ecc.get(2));
        temp.classList.add(ecc.get(3));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(ecc.get(4));
        temp.classList.add(ecc.get(5));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 중 1회, 상 5회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(eng.get(0));
        temp.classList.add(eng.get(1));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(eng.get(2));
        temp.classList.add(eng.get(3));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(ecc.get(4));
        temp.classList.add(ecc.get(5));
        // ECC <-> 학관 (중 1회, 상 1회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 이동난이도 상 6회
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(eng.get(0));
        temp.classList.add(eng.get(1));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(eng.get(2));
        temp.classList.add(eng.get(3));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(eng.get(4));
        temp.classList.add(eng.get(5));
        // 공대 <-> 학관 (상 2회)
        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);


        // 데이터리스트 리턴
        return result;

    }

    // K-MOOC 개수 차이에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen6 () {
        // 본 함수에서 K-MOOC 0개짜리 시간표는 엑셀 파일의 "기본 시간표 형태"와 동일하며, 모든 수업은 학관에서 합니다
        // K-MOOC 개수가 1 증가할 때마다, 학관에서 하던 수업 중 하나가 삭제됩니다

        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // K-MOOC 수업 0개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(hak.get(0));
        temp.classList.add(hak.get(1));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // K-MOOC 수업 1개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(km.get(0));

        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // K-MOOC 수업 2개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(km.get(0));

        temp.classList.add(km.get(1));

        temp.classList.add(hak.get(4));
        temp.classList.add(hak.get(5));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // K-MOOC 수업 3개짜리 시간표
        tableId++;
        temp = new Table(tableId);

        temp.classList.add(km.get(0));

        temp.classList.add(km.get(1));

        temp.classList.add(km.get(2));

        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(hak.get(8));
        temp.classList.add(hak.get(9));

        temp.classList.add(hak.get(10));
        temp.classList.add(hak.get(11));

        temp = addClass(temp, temp.classList);

        result.add(temp);


        // 데이터리스트 리턴
        return result;
    }

    /*
    이 아래는 테스트용 함수
     */

    // 수업 데이터 생성이 잘 되었는지 테스트하는 함수
    public static void printClasses (ArrayList<Class> list) {
        // 주어진 수업 리스트에 대하여
        for(Class c : list) {
            // 수업 ID 출력
            System.out.println("Class ID: " + c.id);
            // 수업 정보 출력
            System.out.println("과목명: " + c.className);
            System.out.println("장소: " + c.location);
            System.out.println("요일: " + c.weekday);
            System.out.println("시작: " + c.startH + ":" + c.startM);
            System.out.println("끝: " + c.endH + ":" + c.endM);
            System.out.println();
        }
    }

    // 시간표 데이터 생성이 잘 되었는지 테스트하는 함수
    public static void printTables (ArrayList<Table> list) {
        // 주어진 시간표 리스트에 대하여
        for(Table s : list) {
            // 시간표 ID 출력
            System.out.println("Table ID: " + s.id);
            // 시간표에 포함된 수업들의 ID 출력
            System.out.print("Class IDs: ");
            for(Class c : s.classList) System.out.print(c.id + " ");
            System.out.println();
            // 요일별로 수업들의 ID 출력
            System.out.print("월요일: ");
            for(Class c : s.monday) System.out.print(c.id + " ");
            System.out.println();
            System.out.print("화요일: ");
            for(Class c : s.tuesday) System.out.print(c.id + " ");
            System.out.println();
            System.out.print("수요일: ");
            for(Class c : s.wednesday) System.out.print(c.id + " ");
            System.out.println();
            System.out.print("목요일: ");
            for(Class c : s.thursday) System.out.print(c.id + " ");
            System.out.println();
            System.out.print("금요일: ");
            for(Class c : s.friday) System.out.print(c.id + " ");
            System.out.println();
            System.out.println();

        }
    }


}

