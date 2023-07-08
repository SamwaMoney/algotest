import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AllClassAlgo {
    public static void main(String[] args) {

    }

    // 전체 과목 개수 count
    public static int AllClassesAlgo(ArrayList<Class> list) {
        Set<String> allClasses = new HashSet<>();
        int allCount = 0;

        for (Class c : list) {
            allClasses.add(c.className);
        }

        allCount = allClasses.size();

        return allCount;
    }

    // 공강 개수 count
    public static int DayoffAlgo(ArrayList<Table> timetable) {
        int dayOffs = 0; // 공강 개수 저장

        for (Table s : timetable) {
            // mon
            if (s.monday.isEmpty()) {
                dayOffs++;
            }

            // tue
            if (s.tuesday.isEmpty()) {
                dayOffs++;
            }

            // wed
            if (s.wednesday.isEmpty()) {
                dayOffs++;
            }

            // thu
            if (s.thursday.isEmpty()) {
                dayOffs++;
            }

            // fri
            if (s.friday.isEmpty()) {
                dayOffs++;
            }
        }

        return dayOffs;
    }

    // 채플 개수 count
    public static int ChapelAlgo(ArrayList<Class> list) {
        int chapel = 0; // 채플 개수 저장

        for (Class c : list) {
            if (c.className == "채플") {
                chapel++;
            }
        }

        return chapel;
    }

    // 원격/비대면 강의 개수 count
    public static int OnlineAlgo(ArrayList<Class> list) {
        int online = 0; // 원격/비대면 강의 개수 저장

        for (Class c : list) {
            if (c.location == "원격/비대면") {
                online++;
            }
        }

        return online;
    }

    // 오전(1, 2교시) 수업 개수 count
    public static int MorningClassAlgo(ArrayList<Class> list) {
        int morningClass = 0; // 오전 수업 개수 저장

        for (Class c : list) {
            if (c.startH == 8 || c.startH == 9) {
                morningClass++;
            }
        }

        return morningClass;
    }


    // 1교시 수업 개수 count
    public static int FirstClassAlgo(ArrayList<Class> list) {
        int firstClass = 0; // 1교시 수업 개수 저장

        for (Class c : list) {
            if (c.startH == 8) {
                firstClass++;
            }
        }

        return firstClass;
    }

    // 강의 장소 모두 같은지 판별
    public static int sameLocationAlgo(ArrayList<Class> list) {

        Set<String> locations = new HashSet<>(); // 강의 장소 저장
        int locationNum = 0; // 강의 장소의 수 저장

        for (Class c : list) {
            locations.add(c.location);
        }

        locationNum = locations.size();

        return locationNum;
    }

    // 강의 장소 모두 다른지 판별
    public static boolean differentLocationAlgo(ArrayList<Class> list) {
        Set<String> locations = new HashSet<>(); // 모든 강의 장소 저장
        Set<String> allClasses = new HashSet<>(); // 모든 강의명 저장
        boolean isDifferent;

        for (Class c : list) {
            locations.add(c.location);
            allClasses.add(c.className);
        }

        if (locations.size() == allClasses.size()) {
            isDifferent = true;
        } else {
            isDifferent = false;
        }

        return isDifferent;
    }

    // 강의가 모두 오전인지 판별
    public static boolean allMorningAlgo(ArrayList<Class> list) {
        Set<String> allClass = new HashSet<>(); // 모든 강의 저장
        Set<String> morningClass = new HashSet<>(); // 오전 강의 저장
        boolean isAllMorning;

        for (Class c : list) {
            allClass.add(c.className);
            if (c.startH == 8 || c.startH == 9) {
                morningClass.add(c.className);
            }
        }

        if (allClass.size() == morningClass.size()) {
            isAllMorning = true;
        } else {
            isAllMorning = false;
        }

        return isAllMorning;
    }

    // 강의가 모두 오후인지 판별
    public static boolean allAfternoonAlgo(ArrayList<Class> list) {
        Set<String> allClass = new HashSet<>(); // 모든 강의 저장
        Set<String> afternoonClass = new HashSet<>(); // 오전 강의 저장
        boolean isAllAfternoon;

        for (Class c : list) {
            allClass.add(c.className);
            if (c.startH == 8 || c.startH == 9) {
                afternoonClass.add(c.className);
            }
        }

        if (allClass.size() == afternoonClass.size()) {
            isAllAfternoon = true;
        } else {
            isAllAfternoon = false;
        }

        return isAllAfternoon;
    }
}
