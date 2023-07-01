import java.util.ArrayList;

/*
*   데이터 생성을 수행하는 메인 코드
 */
public class Main {

    // 수업 ID 지정을 위한 변수
    static Long classId;

    // 메인 함수
    public static void main(String[] args) {

        // 수업 ID 값 0으로 초기화
        classId = 0L;

        // 학관 기준으로 했을 때 다른 건물들과의 이동난이도 및 오르막길 여부 분포가 적절해서, 학관을 중심으로 데이터를 생성했습니다.
        // 원래 공대 중심으로 하려고 했는데... 공대는 모든 건물과의 이동난이도가 '상'이라서 대신 학관으로 선택했습니다ㅎㅎ...

        // 학관 과목 16개 생성
        ArrayList<Class> hak = hak();

        // 학관과의 이동난이도가 '중'인 ECC 과목 3개 생성
        ArrayList<Class> ecc = ecc();

        // 학관과의 이동난이도가 '상'인 공대 과목 3개 생성
        ArrayList<Class> eng = eng();

        // K-MOOC 과목 3개 생성
        ArrayList<Class> km = km();


    }

    // 학관 과목 데이터를 생성하는 함수
    public static ArrayList<Class> hak () {
        // 학관 과목 16개 (일반 과목 14개 & 3시간 연강 과목 2개) (수업 30개)
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

        return result;

    }

    // ecc 과목 3개를 생성하는 함수
    public static ArrayList<Class> ecc () {
        // 학관과의 이동난이도가 '중'인 ECC 과목 3개 (수업 6개)
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
        // 학관과의 이동난이도가 '상'인 공대 과목 3개 (수업 6개)
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



}

