import java.util.*;

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
    // 공학관에서 하는 강의들을 담을 리스트
    static ArrayList<Class> eng;
    // K-MOOC 강의들을 담을 리스트
    static ArrayList<Class> km;


    // 시간표 ID 지정을 위한 변수
    static Long tableId;

    /*
    메인 함수
     */
    public static void main(String[] args) {
        // 수업 ID 값 0으로 초기화
        classId = 0L;

        // 학관 기준으로 했을 때 다른 건물들과의 이동난이도 및 오르막길 여부 분포가 적절해서, 학관을 중심으로 데이터를 생성했습니다.
        // 원래 공학관 중심으로 하려고 했는데... 공학관은 모든 건물과의 이동난이도가 '상'이라서 대신 학관으로 선택했습니다ㅎㅎ...

        // 학관 과목 16개 생성
        hak = hak();

        // 학관과의 이동난이도가 갈 때 '중', 올 때 '상'인 ECC 과목 3개 생성
        ecc = ecc();

        // 학관과의 이동난이도가 왕복 모두 '상'인 공학관 과목 3개 생성
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

        // 7. 삼와머니 팀원들이 구글 스프레드시트에 모은 실제 시간표들의 데이터리스트
        ArrayList<Table> data7 = dataGen7();


        /*
        아래는 데이터가 잘 생성되었는지 테스트하는 프린트 코드
         */
//
//        printTables(data1);
//        printTables(data2);
//        printTables(data3);
//        printTables(data4);
//        printTables(data5);
//        printTables(data6);
        printTables(data7);

//        System.out.println("========== algorithm test - 전체 ==========");
//
//        // 공강 개수 count
//        System.out.println("공강 개수 count");
//        for(Table t : data3) AllClassAlgo.DayoffAlgo(t);
//
//        // 채플 개수 count
//        System.out.println("채플 개수 count");
//        for(Table t : data2) AllClassAlgo.ChapelAlgo(t);
//
//        // 원격/온라인 개수 count
//        System.out.println("원격/온라인 개수 count");
//        for(Table t : data6) AllClassAlgo.OnlineAlgo(t);
//
//        // 모든 수업 개수 count
//        System.out.println("모든 수업 개수 count");
//        for(Table t : data1) AllClassAlgo.AllClassesAlgo(t);
//
//        // 1교시 수업 개수 count
//        System.out.println("1교시 수업 개수 count");
//        for(Table t : data3) AllClassAlgo.FirstClassAlgo(t);
//
//        // 오전 수업 개수 count
//        System.out.println("오전 수업 개수 count");
//        for(Table t : data3) AllClassAlgo.MorningClassAlgo(t);
//
//        // 모든 장소 같은지 판별
//        System.out.println("모든 장소 같은지 판별");
//        for(Table t : data2) AllClassAlgo.sameLocationAlgo(t);
//
//        System.out.println("모든 장소 다른지 판별");
//        // 모든 장소 다른지 판별
//        for(Table t : data2) AllClassAlgo.differentLocationAlgo(t);
//
//        System.out.println("모두 오전 수업인지 판별");
//        for(Table t : data3) AllClassAlgo.allMorningAlgo(t);
//
//        System.out.println("모두 오후 수업인지 판별");
//        for(Table t : data2) AllClassAlgo.allAfternoonAlgo(t);

        // 이동난이도 해시맵 생성
        Map<List<String>, Move> moveDifficulty = makeMoveDifficulties();
        // 스페셜 코멘트 해시맵 생성
        Map<Long, SpecialComment> specialComments = makeSpecialComments();

        // 채점 수행
        testData(data7, moveDifficulty, specialComments);
    }

    /*
    최종적으로 채점 작업을 수행하는 함수
     */
    public static void testData(ArrayList<Table> data, Map<List<String>, Move> moveDifficulty, Map<Long, SpecialComment> specialComments){
        Long index = 1L;

        for(Table t : data){
            System.out.println("================= " + index++ + "번 시간표 채점 시작 =================");

            ArrayList<Integer> good = new ArrayList<>();
            ArrayList<Integer> bad = new ArrayList<>();
            ArrayList<Integer> special = new ArrayList<>();

            int score = 60; // 기본 점수 60점 

            System.out.println();
            System.out.println("--------------- algorithm test - 전체 ---------------");
            score += AllClassAlgo.allClassAlgo(t, good, bad, special);

            System.out.println();
            System.out.println("--------------- algorithm test - 요일별 ---------------");
            score += WeekdayAlgo.weekdayAlgo(t, moveDifficulty, good, bad, special);

            if (score < 0)
                score = 0;
            else if (score > 100)
                score = 100;

            Collections.sort(good);
            Collections.sort(bad);
            Collections.sort(special);

            // 각 코멘트가 3개 초과일 경우 랜덤으로 3가지를 고르기
            if(good.size()>3) good = pickThree(good);
            if(bad.size()>3) bad = pickThree(bad);
            if(special.size()>3) special = pickThree(special);

            System.out.println("good: " + good);
            System.out.println("bad: " + bad);
            System.out.println("special: " + special);
            System.out.println("총점: " + score);

            System.out.println();
            System.out.println("--------------- algorithm test - 유형 선정 ---------------");
            // 시간표 유형 판별
            System.out.println("이 시간표의 유형: " + TableTypeAlgo.tableTypeAlgo(special, specialComments));

            System.out.println();
            System.out.println();


        }
    }

    /*
    리스트에서 랜덤으로 3개를 고르는 함수
     */
    public static ArrayList<Integer> pickThree(ArrayList<Integer> list) {
        // 선택된 3개 코멘트의 ID를 담을 리스트
        ArrayList<Integer> result = new ArrayList<>();

        Random random = new Random();
        for(int i=0; i<3; i++) {
            // 0 이상, 현재 리스트의 크기 미만 중 하나의 수를 랜덤으로 선택
            int picked = random.nextInt(list.size());
            // 선택된 수를 인덱스로 사용하여, list에서 수 하나를 꺼내 result로 옮김
            result.add(list.get(picked));
            list.remove(picked); // 선택된 수가 list에서 remove되었으므로, 이미 선택된 요소가 중복선택될 우려가 없음
        }

        // 결과 리스트 리턴
        return result;
    }

    /*
    이 아래는 수업 데이터 생성 함수
     */

    // 학관 과목 데이터를 생성하는 함수
    public static ArrayList<Class> hak () {
        // 학관 과목 17개 (일반 과목 14개 & 3시간 연강 과목 3개) (수업 31개)
        ArrayList<Class> result = new ArrayList<>();

        classId++;
        result.add(new Class(classId, "학관과목1", "학관", "월", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "학관과목1", "학관", "수", 9, 30, 11, 0));

        classId++;
        result.add(new Class(classId, "학관과목2", "학관", "월", 9, 30, 11, 0));
        classId++;
        result.add(new Class(classId, "학관과목2", "학관", "수", 11, 0, 12, 30));

        classId++;
        result.add(new Class(classId, "학관과목3", "학관", "월", 14, 0, 15, 30));
        classId++;
        result.add(new Class(classId, "학관과목3", "학관", "수", 15, 30, 17, 0));

        classId++;
        result.add(new Class(classId, "학관과목4", "학관", "월", 15, 30, 17, 0));
        classId++;
        result.add(new Class(classId, "학관과목4", "학관", "수", 14, 0, 15, 30));

        classId++;
        result.add(new Class(classId, "학관과목5", "학관", "화", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "학관과목5", "학관", "목", 12, 30, 14, 0));

        classId++;
        result.add(new Class(classId, "학관과목6", "학관", "화", 12, 30, 14, 0));
        classId++;
        result.add(new Class(classId, "학관과목6", "학관", "목", 14, 0, 15, 30));

        classId++;
        result.add(new Class(classId, "학관과목7", "학관", "월", 8, 0, 9, 30));
        classId++;
        result.add(new Class(classId, "학관과목7", "학관", "수", 9, 30, 11, 0));

        classId++;
        result.add(new Class(classId, "학관과목8", "학관", "월", 9, 30, 11, 0));
        classId++;
        result.add(new Class(classId, "학관과목8", "학관", "수", 8, 0, 9, 30));

        classId++;
        result.add(new Class(classId, "학관과목9", "학관", "화", 8, 0, 9, 30));
        classId++;
        result.add(new Class(classId, "학관과목9", "학관", "목", 9, 30, 11, 0));

        classId++;
        result.add(new Class(classId, "학관과목10", "학관", "화", 9, 30, 11, 0));
        classId++;
        result.add(new Class(classId, "학관과목10", "학관", "목", 8, 0, 9, 30));

        classId++;
        result.add(new Class(classId, "학관과목11", "학관", "월", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "학관과목11", "학관", "수", 14, 0, 15, 30));

        classId++;
        result.add(new Class(classId, "학관과목12", "학관", "월", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "학관과목12", "학관", "수", 11, 0, 12, 30));

        classId++;
        result.add(new Class(classId, "학관과목13", "학관", "화", 14, 0, 15, 30));
        classId++;
        result.add(new Class(classId, "학관과목13", "학관", "수", 15, 30, 17, 0));

        classId++;
        result.add(new Class(classId, "학관과목14", "학관", "화", 14, 0, 15, 30));
        classId++;
        result.add(new Class(classId, "학관과목14", "학관", "목", 15, 30, 17, 0));

        classId++;
        result.add(new Class(classId, "학관과목15", "학관", "화", 11, 0, 14, 0));

        classId++;
        result.add(new Class(classId, "학관과목16", "학관", "목",  12, 30, 15, 30));

        classId++;
        result.add(new Class(classId, "학관과목17", "학관", "금",  11, 0, 14, 0));

        return result;

    }

    // ecc 과목 3개를 생성하는 함수
    public static ArrayList<Class> ecc () {
        // 학관과의 이동난이도가 갈 때 '중', 올 때 '상'인 ECC 과목 3개 (수업 6개)
        ArrayList<Class> result = new ArrayList<>();

        // 학관과목1 의 위치만 ECC로 바꾼 것
        classId++;
        result.add(new Class(classId, "ECC과목1", "ECC", "월", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "ECC과목1", "ECC", "수", 9, 30, 11, 0));

        // 학관과목3 의 위치만 ECC로 바꾼 것
        classId++;
        result.add(new Class(classId, "ECC과목2", "ECC", "월", 14, 0, 15, 30));
        classId++;
        result.add(new Class(classId, "ECC과목2", "ECC", "수", 15, 30, 17, 0));

        // 학관과목5 의 위치만 ECC로 바꾼 것
        classId++;
        result.add(new Class(classId, "ECC과목3", "ECC", "화", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "ECC과목3", "ECC", "목", 12, 30, 14, 0));

        return result;
    }

    // 공학관 과목 3개를 생성하는 함수
    public static ArrayList<Class> eng() {
        // 학관과의 이동난이도가 왕복 모두 '상'인 공학관 과목 3개 (수업 6개)
        ArrayList<Class> result = new ArrayList<>();

        // 학관과목1 의 위치만 공학관로 바꾼 것
        classId++;
        result.add(new Class(classId, "공학관과목1", "공학관", "월", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "공학관과목1", "공학관", "수", 9, 30, 11, 0));

        // 학관과목3 의 위치만 공학관로 바꾼 것
        classId++;
        result.add(new Class(classId, "공학관과목2", "공학관", "월", 14, 0, 15, 30));
        classId++;
        result.add(new Class(classId, "공학관과목2", "공학관", "수", 15, 30, 17, 0));

        // 학관과목5 의 위치만 공학관로 바꾼 것
        classId++;
        result.add(new Class(classId, "공학관과목3", "공학관", "화", 11, 0, 12, 30));
        classId++;
        result.add(new Class(classId, "공학관과목3", "공학관", "목", 12, 30, 14, 0));


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

        // 4연강이 있는 시간표
        tableId++;
        temp = new Table(tableId);
        temp.classList.add(hak.get(28));
        
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

    // 이동난이도 차이에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen5 () {
        // 이 함수 내의 모든 시간표는 엑셀 파일의 "기본 시간표 형태"를 따름
        // "기본 시간표 형태"에서, 1번, 3번, 5번 강의의 강의위치만(학관/ECC/공학관) 바꾸어 데이터를 생성하였음
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
        // 공학관 <-> 학관 (상 2회)
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
        // 공학관 <-> 학관 (상 2회)
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
        // 공학관 <-> 학관 (상 2회)
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
        // 공학관 <-> 학관 (상 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(eng.get(2));
        temp.classList.add(eng.get(3));
        // 공학관 <-> 학관 (상 2회)
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
        // 공학관 <-> 학관 (상 2회)
        temp.classList.add(hak.get(2));
        temp.classList.add(hak.get(3));

        temp.classList.add(eng.get(2));
        temp.classList.add(eng.get(3));
        // 공학관 <-> 학관 (상 2회)
        temp.classList.add(hak.get(6));
        temp.classList.add(hak.get(7));

        temp.classList.add(eng.get(4));
        temp.classList.add(eng.get(5));
        // 공학관 <-> 학관 (상 2회)
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

    // 삼와머니 팀원들이 구글 스프레드시트에 모은 실제 시간표들의 데이터리스트를 생성하는 함수
    public static ArrayList<Table> dataGen7 () {
        ArrayList<Table> result = new ArrayList<>();

        Table temp;

        // 스프레드시트의 시트 순서대로 추가하였습니다

        // 시트 1 노하은
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "인공지능", "공학관", "월", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "인공지능", "공학관", "수", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "운영체제", "공학관", "월", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "운영체제", "공학관", "수", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "화", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "금", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "캡스톤디자인과창업프로젝트B", "공학관", "화", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "캡스톤디자인과창업프로젝트B", "공학관", "금", 14, 0, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "디지털논리설계", "공학관", "화", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "디지털논리설계", "공학관", "목", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "SW리더십과기업가정신세미나III", "공학관", "화", 17, 0, 18, 30));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "목", 11, 30, 12, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 2 이소정
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "한국현대시와 삶읽기", "포스코관", "월", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "한국현대시와 삶읽기", "포스코관", "목", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "자료구조", "공학관", "월", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "자료구조", "공학관", "목", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "선형대수학1", "포스코관", "월", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "선형대수학1", "포스코관", "수", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "일반생물학", "종합과학관", "화", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "일반생물학", "종합과학관", "목", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "시스템sw및 실습", "공학관", "월", 17, 0, 18, 30));
        classId++;
        temp.classList.add(new Class(classId, "시스템sw및 실습", "공학관", "목", 17, 0, 18, 30));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "수", 11, 0, 12, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 3 최한비
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "일반생물학", "포스코관", "월", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "일반생물학", "포스코관", "목", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "기독교와세계", "학관", "월", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "기독교와세계", "학관", "수", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "일반물리학I", "종합과학관", "월", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "일반물리학I", "종합과학관", "수", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "일반물리학실험I", "종합과학관", "화", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "미분적분학", "포스코관", "화", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "미분적분학", "포스코관", "목", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "CollegeEnglish", "ECC", "화", 8, 0, 9, 30));
        classId++;
        temp.classList.add(new Class(classId, "CollegeEnglish", "ECC", "목", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "호크마세미나", "원격/비대면", "화", 17, 0, 18, 30));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "화", 9, 30, 10 ,0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 4 김민정
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "월", 10, 0, 10, 30));
        classId++;
        temp.classList.add(new Class(classId, "UI디자인", "sk텔레콤관(정보관)", "월", 12, 30, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "컴퓨터알고리즘", "공학관", "월", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "컴퓨터알고리즘", "공학관", "수", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "캡스톤디자인과창업프로젝트B", "공학관", "화", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "캡스톤디자인과창업프로젝트B", "공학관", "금", 14, 0, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "수", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "금", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "오토마타및형식언어", "공학관", "수", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "오토마타및형식언어", "공학관", "금", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "K-MOOC:동물의행동"));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 5 오혜린
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "마케팅 조사", "신세계관", "월", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "마케팅 조사", "신세계관", "수", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "인적자원관리", "신세계관", "월", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "인적자원관리", "신세계관", "수", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "미시경제이론", "포스코관", "화", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "미시경제이론", "포스코관", "금", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "개인금융", "신세계관", "화", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "개인금융", "신세계관", "목", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "경영학원론", "신세계관", "수", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "경영학원론", "신세계관", "금", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "시장경제세미나", "포스코관", "수", 15, 30, 18, 30));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "목", 11, 30, 12, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 6 권태영
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "월", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "목", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "운영체제", "공학관", "월", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "운영체제", "공학관", "수", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "데이터베이스", "공학관", "화", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "데이터베이스", "공학관", "목", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "컴퓨터알고리즘", "공학관", "화", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "컴퓨터알고리즘", "공학관", "목", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "SW리더십과기업가정신세미나", "공학관", "화", 17, 0, 18, 30));
        classId++;
        temp.classList.add(new Class(classId, "인물로본중국의역사", "포스코관", "수", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "인물로본중국의역사", "포스코관", "금", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "목", 11, 30, 12, 0));
        classId++;
        temp.classList.add(new Class(classId, "시장경제세미나", "포스코관", "목", 15, 30, 18, 30));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 7 차소연
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "화", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "정보통신공학", "공학관", "금", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "인공지능", "공학관", "월", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "인공지능", "공학관", "수", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "컴퓨터알고리즘", "공학관", "화", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "컴퓨터알고리즘", "공학관", "목", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "데이터베이스", "공학관", "화", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "데이터베이스", "공학관", "목", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "프로그래밍을위한컴퓨팅적사고"));
        classId++;
        temp.classList.add(new Class(classId, "K-MOOC:동물의행동"));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "월", 10, 0, 10, 30));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 8 김혜빈
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "가상현실과멀티미디어", "신세계관", "월", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "가상현실과멀티미디어", "신세계관", "수", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "자료구조와알고리즘개론", "원격/비대면", "월", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "자료구조와알고리즘개론", "원격/비대면", "목", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "인공지능과딥러닝개론", "원격/비대면", "화", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "인공지능과딥러닝개론", "원격/비대면", "목", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "정보기술과미래사회"));
        classId++;
        temp.classList.add(new Class(classId, "정보기술과미래사회"));
        classId++;
        temp.classList.add(new Class(classId, "K-MOOC:아동의신비한언어습득력-이중언어아동"));
        classId++;
        temp.classList.add(new Class(classId, "K-MOOC:아동의신비한언어습득력-이중언어아동"));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "목", 11, 30, 12, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 9 조민서
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "캡스톤디자인과창업프로젝트B", "공학관", "화", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "캡스톤디자인과창업프로젝트B", "공학관", "금", 14, 0, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "운영체제", "공학관", "월", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "운영체제", "공학관", "수", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "미시경제이론", "포스코관", "수", 15, 30, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "미시경제이론", "포스코관", "금", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "거시경제이론"));
        classId++;
        temp.classList.add(new Class(classId, "금융경제학", "포스코관", "월", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "금융경제학", "포스코관", "목", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "인류문명과환경과학"));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "수", 11, 30, 12, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 10 장예원
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "모바일인터랙션디자인(캡스톤디자인)", "조형예술관", "월", 9, 30, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "현대영국소설", "신세계관", "화", 9, 30, 11, 0));
        classId++;
        temp.classList.add(new Class(classId, "현대영국소설", "신세계관", "금", 11, 0, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "현대시와사회", "ECC", "화", 12, 30, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "현대시와사회", "ECC", "금", 14, 0, 15, 30));
        classId++;
        temp.classList.add(new Class(classId, "형태와공간I", "조형예술관", "수", 9, 30, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "브랜드아이덴티티디자인", "조형예술관", "목", 9, 30, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "엔터테인먼트그래픽스", "조형예술관", "목", 17, 0, 20, 0));
        classId++;
        temp.classList.add(new Class(classId, "채플", "대강당", "금", 9, 30, 11, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);

        // 시트 11 허지원
        tableId++;
        temp = new Table(tableId);

        classId++;
        temp.classList.add(new Class(classId, "정보디자인", "조형예술관", "월", 11, 0, 14, 0));
        classId++;
        temp.classList.add(new Class(classId, "타이포그래피1", "조형예술관", "화", 9, 30, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "북아트", "조형예술관", "화", 14, 0, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "한국회화스튜디오3", "조형예술관", "수", 14, 0, 17, 0));
        classId++;
        temp.classList.add(new Class(classId, "그래픽디자인", "조형예술관", "목", 9, 30, 12, 30));
        classId++;
        temp.classList.add(new Class(classId, "현대수묵1", "조형예술관", "목", 14, 0, 17, 0));

        temp = addClass(temp, temp.classList);

        result.add(temp);


        // 데이터리스트 리턴
        return result;
    }

    /*
    이 아래는 이동난이도 데이터를 생성하여 Map에 넣는 함수
     */
    public static Map<List<String>, Move> makeMoveDifficulties () {
        Map<List<String>, Move> moveDifficulty = new HashMap<>();

        //  - data1~data6을 채점하기 위한 이동난이도 쌍
        moveDifficulty.put(Arrays.asList("학관", "학관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("학관", "ECC"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("학관", "공학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("ECC", "학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("공학관", "학관"), new Move(false, Difficulty.HIGH));
        //  - data7(삼와머니 팀원들이 모은 시간표)을 채점하기 위한 이동난이도 쌍
        moveDifficulty.put(Arrays.asList("ECC", "ECC"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("공학관", "공학관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("신세계관", "신세계관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("조형예술관", "조형예술관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("종합과학관", "종합과학관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("포스코관", "포스코관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("ECC", "대강당"), new Move(true, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("ECC", "신세계관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("ECC", "조형예술관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("ECC", "종합과학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("ECC", "포스코관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("ECC", "학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("sk텔레콤관(정보관)", "공학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("sk텔레콤관(정보관)", "대강당"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("공학관", "sk텔레콤관(정보관)"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("공학관", "대강당"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("공학관", "종합과학관"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("공학관", "포스코관"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("대강당", "sk텔레콤관(정보관)"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("대강당", "ECC"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("대강당", "공학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("대강당", "신세계관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("대강당", "조형예술관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("대강당", "종합과학관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("대강당", "포스코관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("대강당", "학관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("신세계관", "ECC"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("신세계관", "대강당"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("신세계관", "포스코관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("신세계관", "조형예술관"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("조형예술관", "ECC"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("조형예술관", "대강당"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("조형예술관", "신세계관"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("종합과학관", "ECC"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("종합과학관", "공학관"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("종합과학관", "대강당"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("종합과학관", "포스코관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("종합과학관", "학관"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("포스코관", "ECC"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("포스코관", "공학관"), new Move(true, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("포스코관", "대강당"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("포스코관", "신세계관"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("포스코관", "종합과학관"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("포스코관", "학관"), new Move(false, Difficulty.HIGH));
        moveDifficulty.put(Arrays.asList("학관", "ECC"), new Move(false, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("학관", "대강당"), new Move(false, Difficulty.LOW));
        moveDifficulty.put(Arrays.asList("학관", "종합과학관"), new Move(true, Difficulty.MEDIUM));
        moveDifficulty.put(Arrays.asList("학관", "포스코관"), new Move(true, Difficulty.LOW));

        // 완성된 이동난이도 Map을 리턴
        return moveDifficulty;

    }

    /*
    이 아래는 스페셜 코멘트 데이터를 생성하여 Map에 넣는 함수
     */
    public static Map<Long, SpecialComment> makeSpecialComments () {
        Map<Long, SpecialComment> specialComments = new HashMap<>();

        // 한비 님이 부여하신 ID대로 코멘트 내용 채우기
        String[] contents = new String[14];
        contents[0] = "취미는 학교 다니기";
        contents[1] = "등록금 뿌린 대로 거두자";
        contents[2] = "이화 사이버 대학교";
        contents[3] = "하나님의 축복이 끝이 없네";
        contents[4] = "상여자 특) 점심 먹고 등교함";
        contents[5] = "(충격) 6시 넘어서 수업 듣는 사람 (진짜 계심)";
        contents[6] = "이대 지박령을 뵙습니다";
        contents[7] = "퐁당퐁당 수업을 나누자";
        contents[8] = "동에 번쩍 서에 번쩍";
        contents[9] = "쉬는 시간에 치타가 되는 사람";
        contents[10] = "이화사랑산악회";
        contents[11] = "점심은 포기 못해";
        contents[12] = "밥은 먹고 다니냐?";
        contents[13] = "한 건물 지박령";

        // 14개의 스페셜 코멘트를 Map에 모두 추가
        for(Long i=0L; i<14; i++)
            specialComments.put(i, new SpecialComment(i, contents[i.intValue()], i));
        // 우선순위는 일단 ID와 동일하게 설정하였음

        // 완성된 스페셜 코멘트 Map을 리턴
        return specialComments;
    }

    /*
    이 아래는 테스트용 함수
     */

    // 수업 데이터 생성이 잘 되었는지 프린트해보는 함수
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

    // 시간표 데이터 생성이 잘 되었는지 프린트해보는 함수
    public static void printTables (ArrayList<Table> list) {
        // 주어진 시간표 리스트에 대하여
        for(Table s : list) {
            // 시간표 ID 출력
            System.out.println("Table ID: " + s.id);
            // 시간표에 포함된 수업들의 ID 출력
            System.out.print("모든 수업의 ID: ");
            for(Class c : s.classList) System.out.print(c.id + " ");
            System.out.println();
            System.out.println();
            // 요일별로 수업들의 정보 출력
            System.out.println("월요일: ");
            for(Class c : s.monday) System.out.println(c.id + " " + c.className + ", " + c.location + ", " + c.weekday + ", " + c.startH + ":" + c.startM + " ~ " + c.endH + ":" + c.endM);
            System.out.println();
            System.out.println("화요일: ");
            for(Class c : s.tuesday) System.out.println(c.id + " " + c.className + ", " + c.location + ", " + c.weekday + ", " + c.startH + ":" + c.startM + " ~ " + c.endH + ":" + c.endM);
            System.out.println();
            System.out.println("수요일: ");
            for(Class c : s.wednesday) System.out.println(c.id + " " + c.className + ", " + c.location + ", " + c.weekday + ", " + c.startH + ":" + c.startM + " ~ " + c.endH + ":" + c.endM);
            System.out.println();
            System.out.println("목요일: ");
            for(Class c : s.thursday) System.out.println(c.id + " " + c.className + ", " + c.location + ", " + c.weekday + ", " + c.startH + ":" + c.startM + " ~ " + c.endH + ":" + c.endM);
            System.out.println();
            System.out.println("금요일: ");
            for(Class c : s.friday) System.out.println(c.id + " " + c.className + ", " + c.location + ", " + c.weekday + ", " + c.startH + ":" + c.startM + " ~ " + c.endH + ":" + c.endM);
            System.out.println();
            System.out.println();

        }
    }


}

