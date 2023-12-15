package app.model.course;

import resource.arraylist.MyArrayList;
import resource.queue.ArrayQueue;

import java.util.LinkedList;
import java.util.Queue;

public class MyClass {
    private String classId;
    private String theoryId;
    Course course;
    Time classTime;
    ArrayQueue<Time> theoryTime;

    public MyClass() {
    }

    public MyClass(String classId, String theoryId, Course course, Time classTime, ArrayQueue<Time> theoryTime) {
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

    public ArrayQueue<Time> getTheoryTime() {
        return theoryTime;
    }

    public void setTheoryTime(ArrayQueue<Time> theoryTime) {
        this.theoryTime = theoryTime;
    }

    public String theoryTimeToString(ArrayQueue theoryTime) {
        Queue<Time> theoryTimeQueue = new LinkedList<>();
        String theoryTimeString = "";
        while (!theoryTime.isEmpty()) {
            theoryTimeString += theoryTime.dequeue();
        }
        return theoryTimeString;

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
