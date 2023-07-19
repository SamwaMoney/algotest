// 스페셜 코멘트 클래스. 시간표가 해당되는 스페셜 코멘트 중 하나를 뽑아 유형으로 선택하는 알고리즘 개발에 사용함. 따라서 ID, 내용, 우선순위 필드만 있음.
public class SpecialComment {
    /*
    필드
     */
    Long specialCommentId;  // ID (PK)
    String content;         // 내용
    Long priority;          // 우선순위 (숫자가 적을수록 우위이며, 가장 높은 것은 0. Special comment ID를 0부터 설정하셨길래 안 헷갈리게 우선순위도 0부터로 했습니다)

    /*
    생성자
     */
    public SpecialComment (Long id, String content, Long priority) {
        this.specialCommentId = id;
        this.content = content;
        this.priority = priority;
    }

}
