package app.model.course;

public class MyClass {
    private String classId;
    private String theoryId;
    Course course;
    Time classTime;
    Time theoryTime;

    public MyClass() {
    }

    public MyClass(String classId, String theoryId, Course course, Time classTime, Time theoryTime) {
        this.classId = classId;
        this.theoryId = theoryId;
        this.course = course;
        this.classTime = classTime;
        this.theoryTime = theoryTime;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getTheoryId() {
        return theoryId;
    }

    public void setTheoryId(String theoryId) {
        this.theoryId = theoryId;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Time getClassTime() {
        return classTime;
    }

    public void setClassTime(Time classTime) {
        this.classTime = classTime;
    }

    public Time getTheoryTime() {
        return theoryTime;
    }

    public void setTheoryTime(Time theoryTime) {
        this.theoryTime = theoryTime;
    }

    @Override
    public String toString() {
        return "[" +
                "classId = " + classId +
                ", theoryId = " + theoryId +
                ", course = " + course +
                ", classTime = " + classTime +
                ", theoryTime = " + theoryTime +
                "]";
    }
}
