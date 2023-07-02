function Class (classId, className, location, weekday, startH, startM, endH, endM) {
    this.id = classId;
    this.className = className;
    if(arguments.length > 2){ // 대면 강의의 경우
        this.location = location;
        this.weekday = weekday;
        this.startH = startH;
        this.startM = startM;
        this.endH = endH;
        this.endM = endM;
    } else { // 비대면 강의의 경우
        this.location = "원격/비대면";
        this.weekday = "원격/비대면";
        this.startH = -1;
        this.startM = -1;
        this.endH = -1;
        this.endM = -1;
    }
}
export default Class;