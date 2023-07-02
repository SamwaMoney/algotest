import Class from './class.js';
import Move from './move.js';
import Table from './table.js';
import Difficulty from './difficulty.js';

// 이동 난이도 DB 생성
let fromHak = new Map(); // 학관에서 출발하는 이동 난이도를 저장하는 해시맵 선언
let fromEcc = new Map(); // ECC에서 출발하는 이동 난이도를 저장하는 해시맵 선언
let fromEng = new Map(); // 공대에서 출발하는 이동 난이도를 저장하는 해시맵 선언

fromHak.set("학관", new Move(false, Difficulty.LOW)); // 학관에서 학관으로의 이동 난이도 저장
fromHak.set("ECC", new Move(false, Difficulty.MEDIUM)); // 학관에서 ECC로의 이동 난이도 저장
fromHak.set("공대", new Move(true, Difficulty.HIGH)); // 학관에서 공대로의 이동 난이도 저장
fromEcc.set("학관", new Move(true, Difficulty.HIGH)); // ECC에서 학관으로의 이동 난이도 저장
fromEng.set("학관", new Move(false, Difficulty.HIGH)); // 공대에서 학관으로의 이동 난이도 저장

// 수업 데이터 생성 함수
const Hak = () => { // 학관 과목
    let result = [];
    result.push(new Class(++classId, "학관과목1", "학관", "월", 11, 0, 12, 15));
    result.push(new Class(++classId, "학관과목1", "학관", "수", 9, 30, 10, 45));
    result.push(new Class(++classId, "학관과목2", "학관", "월", 9, 30, 10, 45));
    result.push(new Class(++classId, "학관과목2", "학관", "수", 11, 0, 12, 15));
    result.push(new Class(++classId, "학관과목3", "학관", "월", 14, 0, 15, 15));
    result.push(new Class(++classId, "학관과목3", "학관", "수", 15, 30, 16, 45));
    result.push(new Class(++classId, "학관과목4", "학관", "월", 15, 30, 16, 45));
    result.push(new Class(++classId, "학관과목4", "학관", "수", 14, 0, 15, 15));
    result.push(new Class(++classId, "학관과목5", "학관", "화", 11, 0, 12, 15))
    result.push(new Class(++classId, "학관과목5", "학관", "목", 12, 30, 13, 45));
    result.push(new Class(++classId, "학관과목6", "학관", "화", 12, 30, 13, 45))
    result.push(new Class(++classId, "학관과목6", "학관", "목", 14, 0, 15, 15));
    result.push(new Class(++classId, "학관과목7", "학관", "월", 8, 0, 9, 15))
    result.push(new Class(++classId, "학관과목7", "학관", "수", 9, 30, 10, 45));
    result.push(new Class(++classId, "학관과목8", "학관", "월", 9, 30, 10, 45))
    result.push(new Class(++classId, "학관과목8", "학관", "수", 8, 0, 9, 15));
    result.push(new Class(++classId, "학관과목9", "학관", "화", 8, 0, 9, 15))
    result.push(new Class(++classId, "학관과목9", "학관", "목", 9, 30, 10, 45));
    result.push(new Class(++classId, "학관과목10", "학관", "화", 9, 30, 10, 45))
    result.push(new Class(++classId, "학관과목10", "학관", "목", 8, 0, 9, 15));
    result.push(new Class(++classId, "학관과목11", "학관", "월", 11, 0, 12, 15))
    result.push(new Class(++classId, "학관과목11", "학관", "수", 14, 0, 15, 15));
    result.push(new Class(++classId, "학관과목12", "학관", "월", 11, 0, 12, 15))
    result.push(new Class(++classId, "학관과목12", "학관", "수", 11, 0, 12, 15));
    result.push(new Class(++classId, "학관과목13", "학관", "화", 14, 0, 15, 15))
    result.push(new Class(++classId, "학관과목13", "학관", "수", 15, 30, 16, 45));
    result.push(new Class(++classId, "학관과목14", "학관", "화", 14, 0, 15, 15))
    result.push(new Class(++classId, "학관과목14", "학관", "목", 15, 30, 16, 45));
    result.push(new Class(++classId, "학관과목15", "학관", "화", 11, 0, 13, 45));
    result.push(new Class(++classId, "학관과목16", "학관", "목",  12, 30, 15, 15));
    result.push(new Class(++classId, "학관과목17", "학관", "금",  11, 0, 13, 45));
    return result;
} 

const Ecc = () => { // Ecc 과목
    let result = [];

    // 학관과목1 의 위치만 ECC로 바꾼 것
    result.push(new Class(++classId, "ECC과목1", "ECC", "월", 11, 0, 12, 15));
    result.push(new Class(++classId, "ECC과목1", "ECC", "수", 9, 30, 10, 45));

    // 학관과목3 의 위치만 ECC로 바꾼 것
    result.push(new Class(++classId, "ECC과목2", "ECC", "월", 14, 0, 15, 15));
    result.push(new Class(++classId, "ECC과목2", "ECC", "수", 15, 30, 16, 45));

    // 학관과목5 의 위치만 ECC로 바꾼 것
    result.push(new Class(++classId, "ECC과목3", "ECC", "화", 11, 0, 12, 15));
    result.push(new Class(++classId, "ECC과목3", "ECC", "목", 12, 30, 13, 45));
    return result;
}

const Eng = () => { // 공대 과목
    let result = [];
    // 학관과목1 의 위치만 공대로 바꾼 것
    result.push(new Class(++classId, "공대과목1", "공대", "월", 11, 0, 12, 15));
    result.push(new Class(++classId, "공대과목1", "공대", "수", 9, 30, 10, 45));

    // 학관과목3 의 위치만 공대로 바꾼 것
    result.push(new Class(++classId, "공대과목2", "공대", "월", 14, 0, 15, 15));
    result.push(new Class(++classId, "공대과목2", "공대", "수", 15, 30, 16, 45));

    // 학관과목5 의 위치만 공대로 바꾼 것
    result.push(new Class(++classId, "공대과목3", "공대", "화", 11, 0, 12, 15));
    result.push(new Class(++classId, "공대과목3", "공대", "목", 12, 30, 13, 45));    
    return result;
}

const Km = () => { // k-mooc 과목
    let result = [];
    result.push(new Class(++classId, "케이묵1"));
    result.push(new Class(++classId, "케이묵2"));
    result.push(new Class(++classId, "케이묵3"));
    return result;
}

// 수업 데이터 생성
let classId = 0;
const hak = Hak(); // 학관 과목 16개 생성
const ecc = Ecc(); // 학관과의 이동난이도가 갈 때 '중', 올 때 '상'인 ECC 과목 3개 생성
const eng = Eng(); // 학관과의 이동난이도가 왕복 모두 '상'인 공대 과목 3개 생성
const km = Km(); // K-MOOC 과목 3개 생성

// 시간표 데이터 생성 함수

// 수업을 시간표의 요일별 리스트에 집어 넣는 함수
const addClass = (t, list) => { // 과목 데이터, 요일 리스트 
    for (const c of list){
        switch (c.weekday){
            case "월":
                t.monday.push(c);
                break;
            case "화":
                t.tuesday.push(c);
                break;
            case "수":
                t.wednesday.push(c);
                break;
            case "목":
                t.thursday.push(c);
                break;
            case "금":
                t.friday.push(c);
                break;
        }
    }
    return t;
}

// 강의 개수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
const dataGen1 = () => {
    let result = [];
    let temp;

    // 강의 1개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp = addClass(temp, temp.classList);
    
    result.push(temp);

    // 강의 2개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 강의 3개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 강의 4개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 데이터리스트 리턴
    return result;
}

// 공강 일수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
const dataGen2 = () => {
    let result = [];
    let temp;

    // 공강 0일짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[30]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 공강 1일짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 공강 2일짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[28]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 공강 3일짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 공강 4일짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[28]);

    temp = addClass(temp, temp.classList);

    result.push(temp);


    // 데이터리스트 리턴
    return result;
}

// 1교시 수업 개수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
const dataGen3 = () => {
    let result = [];
    let temp;
    // 1교시 수업 0개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 1교시 수업 1개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 1교시 수업 2개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 1교시 수업 3개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[16]);
    temp.classList.push(hak[17]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 1교시 수업 4개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[18]);
    temp.classList.push(hak[19]);

    temp.classList.push(hak[16]);
    temp.classList.push(hak[17]);

    temp = addClass(temp, temp.classList);

    result.push(temp);


    // 데이터리스트 리턴
    return result;
}

// 3교시 이상 연강 개수에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
const dataGen4 = () => {
    let result = [];
    let temp;
    // 3교시 이상 연강 수업 0개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 3교시 이상 연강 수업 1개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[20]);
    temp.classList.push(hak[21]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 3교시 이상 연강 수업 2개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[22]);
    temp.classList.push(hak[23]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 3교시 이상 연강 수업 3개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[24]);
    temp.classList.push(hak[25]);

    temp.classList.push(hak[22]);
    temp.classList.push(hak[23]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 3교시 이상 연강 수업 4개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[26]);
    temp.classList.push(hak[27]);

    temp.classList.push(hak[22]);
    temp.classList.push(hak[23]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 3교시 이상 연강 수업 4개짜리 시간표 (3시간짜리 강의 2개를 포함)
    temp = new Table(++tableId);

    temp.classList.push(hak[12]);
    temp.classList.push(hak[13]);

    temp.classList.push(hak[14]);
    temp.classList.push(hak[15]);

    temp.classList.push(hak[26]);
    temp.classList.push(hak[27]);

    temp.classList.push(hak[22]);
    temp.classList.push(hak[23]);

    temp.classList.push(hak[28]);

    temp.classList.push(hak[29]);

    temp = addClass(temp, temp.classList);

    result.push(temp);


    // 데이터리스트 리턴
    return result;
}

// 이동난이도 차이에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
const dataGen5 = () => {
    // 이 함수 내의 모든 시간표는 엑셀 파일의 "기본 시간표 형태"를 따름
    // "기본 시간표 형태"에서, 1번, 3번, 5번 강의의 강의위치만(학관/ECC/공대) 바꾸어 데이터를 생성하였음
    // 즉, 모든 시간표에서 직전직후 수업간 이동은 총 6번
    
    let result = [];
    let temp;

    // 이동난이도 하 6회
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 하 4회, 중 1회, 상 1회
    temp = new Table(++tableId);

    temp.classList.push(ecc[0]);
    temp.classList.push(ecc[1]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 하 4회, 상 2회
    temp = new Table(++tableId);

    temp.classList.push(eng[0]);
    temp.classList.push(eng[1]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 하 2회, 중 1회, 상 3회
    temp = new Table(++tableId);

    temp.classList.push(ecc[0]);
    temp.classList.push(ecc[1]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(eng[2]);
    temp.classList.push(eng[3]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 하 2회, 중 2회, 상 2회
    temp = new Table(++tableId);

    temp.classList.push(ecc[0]);
    temp.classList.push(ecc[1]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(ecc[2]);
    temp.classList.push(ecc[3]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);
    // 학관 <-> 학관 (하 2회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 중 3회, 상 3회
    temp = new Table(++tableId);

    temp.classList.push(ecc[0]);
    temp.classList.push(ecc[1]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(ecc[2]);
    temp.classList.push(ecc[3]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(eng[4]);
    temp.classList.push(eng[5]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 중 2회, 상 4회
    temp = new Table(++tableId);

    temp.classList.push(eng[0]);
    temp.classList.push(eng[1]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(ecc[2]);
    temp.classList.push(ecc[3]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(ecc[4]);
    temp.classList.push(ecc[5]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 중 1회, 상 5회
    temp = new Table(++tableId);

    temp.classList.push(eng[0]);
    temp.classList.push(eng[1]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(eng[2]);
    temp.classList.push(eng[3]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(ecc[4]);
    temp.classList.push(ecc[5]);
    // ECC <-> 학관 (중 1회, 상 1회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // 이동난이도 상 6회
    temp = new Table(++tableId);

    temp.classList.push(eng[0]);
    temp.classList.push(eng[1]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(eng[2]);
    temp.classList.push(eng[3]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(eng[4]);
    temp.classList.push(eng[5]);
    // 공대 <-> 학관 (상 2회)
    temp.classList.push(hak[10]);
    temp.classList.push(hak[10]);

    temp = addClass(temp, temp.classList);

    result.push(temp);


    // 데이터리스트 리턴
    return result;
}

// K-MOOC 개수 차이에 따른 점수 변동 확인을 위한 데이터리스트를 생성하는 함수
const dataGen6 = () => {
    let result = [];
    let temp;
    // K-MOOC 수업 0개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(hak[0]);
    temp.classList.push(hak[1]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // K-MOOC 수업 1개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(km[0]);

    temp.classList.push(hak[2]);
    temp.classList.push(hak[3]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // K-MOOC 수업 2개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(km[0]);

    temp.classList.push(km[1]);

    temp.classList.push(hak[4]);
    temp.classList.push(hak[5]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);

    // K-MOOC 수업 3개짜리 시간표
    temp = new Table(++tableId);

    temp.classList.push(km[0]);

    temp.classList.push(km[1]);

    temp.classList.push(km[2]);

    temp.classList.push(hak[6]);
    temp.classList.push(hak[7]);

    temp.classList.push(hak[8]);
    temp.classList.push(hak[9]);

    temp.classList.push(hak[10]);
    temp.classList.push(hak[11]);

    temp = addClass(temp, temp.classList);

    result.push(temp);


    // 데이터리스트 리턴
    return result;
}

// 시간표 데이터 생성
let tableId = 0;
const data1 = dataGen1(); // 1. 강의 개수에 따른 점수 변동 확인을 위한 데이터리스트
const data2 = dataGen2(); // 2. 공강 일수에 따른 점수 변동 확인을 위한 데이터리스트
const data3 = dataGen3(); // 3. 1교시 수업 개수에 따른 점수 변동 확인을 위한 데이터리스트
const data4 = dataGen4(); // 4. 3교시 이상 연강 개수에 따른 점수 변동 확인을 위한 데이터리스트
const data5 = dataGen5(); // 5. 이동난이도 및 오르막길 차이에 따른 점수 변동 확인을 위한 데이터리스트
const data6 = dataGen6(); // 6. K-MOOC 개수 차이에 따른 점수 변동 확인을 위한 데이터리스트

const printData = () => {
    console.log(hak);
    console.log(ecc);
    console.log(eng);
    console.log(km);
}


const printTable = () => {
    console.log(data1);
    console.log(data2);
    console.log(data3);
    console.log(data4);
    console.log(data5);
    console.log(data6);
    //console.log(data1[0].classList);
}

printData();
printTable();