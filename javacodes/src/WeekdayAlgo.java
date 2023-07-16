import java.util.*;

public class WeekdayAlgo {
    public static void main(String[] args) {
//
//
//        WeekdayAlgo algo = new WeekdayAlgo();
//        Object[] result = algo.weekdayAlgo(table, move);
//
//        int oneClass = (int) result[0];
//        Map<String, Integer> score = (Map<String, Integer>) result[1];
//        int noLunchCnt = (int) result[2];
//
//        // 결과 사용 (생략)
    }

    private int oneClass;
    private Map<String, Integer> score;
    private int noLunchCnt;

    public Object[] weekdayAlgo(Table table) {
        oneClass = 0;
        score = new HashMap<>();
        noLunchCnt = 0;

//        dayAlgo(table.monday);
//        dayAlgo(table.tuesday);
//        dayAlgo(table.wednesday);
//        dayAlgo(table.thursday);
//        dayAlgo(table.friday);

        return new Object[]{oneClass, score, noLunchCnt};
    }

    private void dayAlgo(List<Class> day, Move moveDifficulty) {
        day.sort((a, b) -> {
            if (a.startH < b.startH || (a.startH == b.startH && a.startM < b.startM))
                return -1;
            else
                return 1;
        });

        if (day.size() == 1) {
            oneClass++;
        }

        int diff;
        boolean inARow = false;
        int rowCnt = 1;
        int maxCnt = 1;
        Move move;

        for (int i = 0; i < day.size() - 1; i++) {
            Class current = day.get(i);
            Class next = day.get(i + 1);

            if (current.endH == next.startH) {
                diff = next.startM - current.endM;
            } else {
                diff = (next.startH - current.endH) * 60 + (next.startM - current.endM);
            }

            if (diff <= 15) {
                if (inARow) {
                    rowCnt++;
                } else {
                    rowCnt = 2;
                    inARow = true;
                }
                maxCnt = Math.max(maxCnt, rowCnt);

                List<String> locationPair = Arrays.asList(current.location, next.location);
                move = moveDifficulty;

                if (move.uphill) {
                    score.put("uphill", score.getOrDefault("uphill", 0) - 3);
                }

                switch (move.difficulty) {
                    case LOW:
                        score.put("difficulty", score.getOrDefault("difficulty", 0) + 3);
                        break;
                    case MEDIUM:
                        score.put("difficulty", score.getOrDefault("difficulty", 0));
                        break;
                    case HIGH:
                        score.put("difficulty", score.getOrDefault("difficulty", 0) - 3);
                        break;
                }

            } else {
                inARow = false;
                rowCnt = 1;
            }
        }

        boolean class11 = false;
        boolean class12 = false;

        for (Class c : day) {
            if (c.startH == 11) {
                class11 = true;
            } else if (c.startH == 12) {
                class12 = true;
            }
            if (class11 && class12) {
                noLunchCnt++;
                break;
            }
        }
    }
}
