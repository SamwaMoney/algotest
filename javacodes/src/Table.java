import java.util.ArrayList;

// 테스트용 시간표 데이터 클레스
class Table {
    // 필드
    Long id;                    // 고유 ID
    ArrayList<Class> classList; // 시간표에 속한 수업들의 리스트

    public Table(Long id) {
        this.id = id;
        this.classList = new ArrayList<>();
    }

}