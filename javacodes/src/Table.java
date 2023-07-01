import java.util.ArrayList;

// 테스트용 시간표 데이터 클레스
class Table {
    // 필드
    Long id;                    // 고유 ID
    ArrayList<Class> classList; // 모든 수업의 리스트
    ArrayList<Class> monday;    // 월요일 수업 리스트
    ArrayList<Class> tuesday;   // 화요일 수업 리스트
    ArrayList<Class> wednesday;    // 수요일 수업 리스트
    ArrayList<Class> thursday;    // 목요일 수업 리스트
    ArrayList<Class> friday;    // 금요일 수업 리스트

    public Table(Long id) {
        this.id = id;
        this.classList = new ArrayList<>();
        this.monday = new ArrayList<>();
        this.tuesday = new ArrayList<>();
        this.wednesday = new ArrayList<>();
        this.thursday = new ArrayList<>();
        this.friday = new ArrayList<>();
    }

}