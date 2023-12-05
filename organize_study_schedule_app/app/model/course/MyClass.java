package app.model.course;

public class MyClass {
    private String classId;
    Course course;
    Time time;

    public MyClass() {
    }

    public MyClass(String classId, Course course, Time time) {
        this.classId = classId;
        this.course = course;
        this.time = time;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Time getTime() {
        return this.time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public MyClass classId(String classId) {
        setClassId(classId);
        return this;
    }

    public MyClass course(Course course) {
        setCourse(course);
        return this;
    }

    public MyClass time(Time time) {
        setTime(time);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " classId='" + getClassId() + "'" +
                ", course='" + getCourse() + "'" +
                ", time='" + getTime() + "'" +
                "}";
    }

}
