import java.util.*;

public class AllClassAlgo {
    public static void main(String[] args) {

    }

    // 전체 과목 개수 count
    // 수업 7개 미만: 수업 하나당 +1, 원격 비대면일 경우 +7
    public static int AllClassesAlgo(Table t) {
        Set<String> allClasses = new HashSet<>(); // 모든 강의 리스트

        int allCount = 0; // 모든 강의의 수 저장
        int online = 0; // 원격/온라인 강의의 수 저장
        int score = 0; // 점수 저장
        int allBlockCount = t.classList.size(); // 전체 수업 단위 수 저장
        int chapelCount = ChapelAlgo(t); // 전체 채플 수

        ArrayList<Integer> good = new ArrayList<>();
        ArrayList<Integer> bad = new ArrayList<>();
        ArrayList<Integer> special = new ArrayList<>();

        for (Class c : t.classList) {
            if (c.location.equals("원격/비대면")) {
                score += 3;
                online++;
            } else {
                score++;
            }
            allClasses.add(c.className);
        }

        allCount = allClasses.size();

        if(allBlockCount <= 6)
            special.add(0); // special comment id 0 취미는 학교 다니기
        else if (allBlockCount >= 14)
            special.add(1); // special comment id 1 등록금 뿌린 대로 거두자

        if (online >= 3)
            special.add(2); // special comment id 2 이화 사이버 대학교
        if (online >= 2)
            good.add(10); // good comment id 10 비대면 강의 있음

        if (chapelCount >= 2)
            special.add(3); // special comment id 3 하나님의 축복이 끝이 없네

        DayoffAlgo(t, good, bad); // 공강 관련 가감점 및 코멘트 처리 함수 
        
        System.out.println("과목의 개수가 " + allCount + "개이고, 원격/온라인 강의의 개수는 " + online +"개 이므로, 점수는 " + score);

        return score;
    }

    // 공강 개수 count
    // 요일 공강 하나당 +10
    public static void DayoffAlgo(Table t, ArrayList<Integer> good, ArrayList<Integer> bad) {

        boolean[] dayOffs = new boolean[5]; // 공강 여부 저장하는 배열
        int score = 0; // 점수 저장
        int dayOffCnt = 0;

        // mon
        if (t.monday.isEmpty()) {
            dayOffs[0] = true;
        }

        // tue
        if (t.tuesday.isEmpty()) {
            dayOffs[1] = true;
        }

        // wed
        if (t.wednesday.isEmpty()) {
            dayOffs[2] = true;
        }

        // thu
        if (t.thursday.isEmpty()) {
            dayOffs[3] = true;
        }

        // fri
        if (t.friday.isEmpty()) {
            dayOffs[4] = true;
            good.add(7); // good comment id 7 최고 인기 금공강!
        }

        for (int i=0; i<5; i++){
            if(dayOffs[i] == true)
                dayOffCnt++;
        }

        score += dayOffCnt * 10;

        if (dayOffCnt == 1){
            for (int i=0; i<5; i++){
                if(dayOffs[i] == true){
                    good.add(i); // good comment id i 기분 좋은 _요일
                    break;
                }
            }
        } else if (dayOffCnt == 2){
            good.add(5); // good comment id 5 행복한 2공강
        } else if (dayOffCnt == 3){
            good.add(6); // good comment id 6 엄청난 3공강 
        } else if (dayOffCnt == 0){
            bad.add(4); // bad comment id 4 공강 1도 없어
        }

        System.out.println("요일 공강의 개수가 " + dayOffCnt + "개 이므로, 점수는 " + score);

        return;
    }

    // 채플 개수 count
    public static int ChapelAlgo(Table t) {
        int chapel = 0; // 채플 개수 저장

        for (Class c : t.classList) {
            if (c.className.equals("채플")) {
                chapel++;
            }
        }

        System.out.println(chapel);

        return chapel;
    }

    // 원격/비대면 강의 개수 count
    public static int OnlineAlgo(Table t) {
        int online = 0; // 원격/비대면 강의 개수 저장

        for (Class c : t.classList) {
            if (c.location.equals("원격/비대면")) {
                online++;
            }
        }

        System.out.println(online);

        return online;
    }

    // 오전(1, 2교시) 수업 개수 count
    public static int MorningClassAlgo(Table t) {
        int morningClass = 0; // 오전 수업 개수 저장

        for (Class c : t.classList) {
            if (c.startH == 8 || c.startH == 9) {
                morningClass++;
            }
        }

        System.out.println(morningClass);

        return morningClass;
    }


    // 1교시 수업 개수 count
    // 1교시 수업 유무: 1교시 수업 하나당 -3
    public static int FirstClassAlgo(Table t) {
        int firstClass = 0; // 1교시 수업 개수 저장
        int score = 0; // 점수 저장

        for (Class c : t.classList) {
            if (c.startH == 8) {
                firstClass++;
                score -= 3 ;
            }
        }

        System.out.println("1교시의 개수가 " + firstClass + "개 이므로, 점수는 " + score);

        return score;
    }

    // 강의 장소 모두 같은지 판별
    public static boolean sameLocationAlgo(Table t) {

        Set<String> locations = new HashSet<>(); // 강의 장소 저장
        int locationNum = 0; // 강의 장소의 수 저장

        for (Class c : t.classList) {
            locations.add(c.location);
        }

        locationNum = locations.size();

        if (locationNum == 1) {
            System.out.println(true);
            return true;
        } else {
            System.out.println(false);
            return false;
        }

    }

    // 강의 장소 모두 다른지 판별
    public static boolean differentLocationAlgo(Table t) {
        Set<String> locations = new HashSet<>(); // 모든 강의 장소 저장
        Set<String> allClasses = new HashSet<>(); // 모든 강의명 저장

        boolean isDifferent;

        for (Class c : t.classList) {
            locations.add(c.location);
            allClasses.add(c.className);
        }

        if (allClasses.size() == 1) {
            isDifferent = false; // 과목 하나만 있을 때는 수업 장소가 같은 것으로 가정
        }
        else if (locations.size() == allClasses.size()) {
            isDifferent = true;
        } else {
            isDifferent = false;
        }

        System.out.println(isDifferent);

        return isDifferent;
    }

    // 강의가 모두 오전인지 판별
    public static boolean allMorningAlgo(Table t) {
        Set<String> morningClass = new HashSet<>(); // 오전 강의 저장

        boolean isAllMorning;

        for (Class c : t.classList) {

            if (c.startH <= 9) {
                morningClass.add("morning");
            } else {
                morningClass.add("afternoon");
            }
        }

        if (morningClass.size() == 1) {
            isAllMorning = true;
        } else {
            isAllMorning = false;
        }

        System.out.println(isAllMorning);

        return isAllMorning;
    }

    // 강의가 모두 오후인지 판별
    public static boolean allAfternoonAlgo(Table t) {
        Set<String> afternoonClass = new HashSet<>(); // 오후 강의 저장

        boolean isAllAfternoon;

        for (Class c : t.classList) {

            if (c.startH > 9) {
                afternoonClass.add("afternoon");
            } else {
                afternoonClass.add("morning");
            }
        }

        if (afternoonClass.size() == 1) {
            isAllAfternoon = true;
        } else {
            isAllAfternoon = false;
        }

        System.out.println(isAllAfternoon);

        return isAllAfternoon;
    }
}
