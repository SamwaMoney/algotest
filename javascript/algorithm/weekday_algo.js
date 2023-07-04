const weekday_algo = (table, moveDifficulty) => { // 인자로 시간표 저장
    let one_class = 0; // 강의 하나만 있는 요일 수 저장
    let score = {}; // 요일별 데이터 점수 계산에서 요인별 감/가점할 점수 저장 (uphill, difficulty)
    let no_lunch_cnt = 0; // 점심 시간 확보 못한 요일 수 저장 
    
    const day_algo = (day) => { // 인자로 table.monday 처럼 요일별 강의 리스트 가짐
        day.sort((a, b) => { // 요일마다 시작 시간/분 순으로 sort
            if(a.startH < b.startH || (a.startH == b.startH && a.startM < b.startM)) 
                return -1;
            else
                return 1;
        });
        console.log(day);

        if (day.length == 1){ // 강의 하나만 있는 요일인지 체크
            one_class++;
        }

        let diff = 0; // 인접한 강의 쌍의 시간 차이
        let in_a_row = false; // 연강 여부
        let row_cnt = 1; // 현재 연강 수
        let max_cnt = 1; // 최대 연강 수 
        let move; // 해당 연강 쌍의 Move 객체

        for (let i=0; i<(day.length-1); i++){ // 인접한 강의 쌍에 대해 연강 여부 확인
            // diff 계산
            if (day[i].endH == day[i+1].startH){
                diff = day[i+1].startM - day[i].endM;
            } else{
                diff = (day[i+1].startH - day[i].endH) * 60 + (day[i+1].startM - day[i].endM)
            }
            console.log(diff);

            // 연강일 경우에 대한 처리
            if(diff <= 15){
                // 연강 수 처리
                if(in_a_row){ // 이전 쌍이 연강이라면
                    row_cnt++;
                } else{ // 이전 쌍이 연강이 아니라면
                    row_cnt = 2;
                    in_a_row = true;
                }
                max_cnt = max(max_cnt, row_cnt);

                // 건물 이동 난이도 및 오르막길 유무 점수 계산
                move = moveDifficulty.get((day[i].location, day[i+1].location));
                if(move.uphill){ // 오르막길이 있으면
                    score['uphill'] -= 3;
                }
                switch(move.difficulty){
                    case 'LOW':
                        score['difficulty'] += 3;
                        break;
                    case 'MEDIUM': // 의미 없는 구문
                        score['difficulty'] += 0;
                        break;
                    case 'HIGH':
                        score['difficulty'] -= 3;
                        break;
                }
            } else {
                in_a_row = false;
                row_cnt = 1;
            }
        }

        let class_11 = false;
        let class_12 = false;
        // 점심 시간 확보 여부 처리
        for(const d of day){
            if (d.startH == 11){
                class_11 = true;
            } else if (d.startH == 12){
                class_12 = true;
            }
            if (class_11 && class_12){ // 3, 4교시 수업이 모두 있는 경우
                no_lunch_cnt++; // 점심 못 먹는 날
                break;
            }
        }
    }

    if (no_lunch_cnt >= 3){ // 점심 못먹는 날이 3일 이상이면
        score['no_lunch'] = -5;
    }

    day_algo(table.monday);
    day_algo(table.tuesday);
    day_algo(table.wednesday);
    day_algo(table.thursday);
    day_algo(table.friday);

    return [one_class, score, no_lunch_cnt];
}
export default weekday_algo;