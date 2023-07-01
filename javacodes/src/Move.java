// 오르막길 여부와 이동난이도 상, 중, 하를 포괄하는 이동 객체
public class Move {
    // 필드
    boolean uphill;         // 오르막길 여부
    Difficulty difficulty;  // 난이도 상, 중, 하

    // 생성자
    public Move (boolean uphill, Difficulty difficulty) {
        this.uphill = uphill;
        this.difficulty = difficulty;
    }
}
