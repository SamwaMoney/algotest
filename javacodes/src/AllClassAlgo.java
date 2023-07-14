import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllClassAlgo {
    public static void main(String[] args) {

    }

    // 전체 과목 개수 count
    public static int AllClassesAlgo(Table t) {
        Set<String> allClasses = new HashSet<>(); // 모든 강의 리스트

        int allCount = 0; // 모든 강의의 수 저장

        for (Class c : t.classList) {
            allClasses.add(c.className);
        }

        allCount = allClasses.size();

        System.out.println(allCount);

        return allCount;
    }

    // 공강 개수 count
    public static int DayoffAlgo(Table t) {

        int dayOffs = 0; // 공강 개수 저장

        // mon
        if (t.monday.isEmpty()) {
            dayOffs++;
        }

        // tue
        if (t.tuesday.isEmpty()) {
            dayOffs++;
        }

        // wed
        if (t.wednesday.isEmpty()) {
            dayOffs++;
        }

        // thu
        if (t.thursday.isEmpty()) {
            dayOffs++;
        }

        // fri
        if (t.friday.isEmpty()) {
            dayOffs++;
        }

        System.out.println(dayOffs);

        return dayOffs;
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
    public static int FirstClassAlgo(Table t) {
        int firstClass = 0; // 1교시 수업 개수 저장

        for (Class c : t.classList) {
            if (c.startH == 8) {
                firstClass++;
            }
        }

        System.out.println(firstClass);

        return firstClass;
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
