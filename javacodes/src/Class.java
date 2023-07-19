// 수업 클래스
class Class {
    // 필드
    Long id;            // 고유 ID
    String className;   // 과목명
    String location;    // 장소
    String weekday;     // 요일
    int startH;         // 시작 시
    int startM;         // 시작 분
    int endH;           // 끝 시
    int endM;           // 끝 분

    // 생성자 (오프라인 강의 & 시간이 정해진 원격/비대면 강의)
    public Class (Long classId, String className, String location, String weekday, int startH, int startM, int endH, int endM){
        this.id = classId;
        this.className = className;
        this.location = location;
        this.weekday = weekday;
        this.startH = startH;
        this.startM = startM;
        this.endH = endH;
        this.endM = endM;
    }

    // 생성자 (시간이 정해지지 않은 원격/비대면 강의)
    public Class (Long classId, String className){
        this.id = classId;
        this.className = className;
        this.location = "원격/비대면";
        this.weekday = "원격/비대면";
        this.startH = -1;
        this.startM = -1;
        this.endH = -1;
        this.endM = -1;
    }

}
