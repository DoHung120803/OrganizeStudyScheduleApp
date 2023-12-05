package app.model.course;

import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

public class CourseManager {

    private static CourseManager courseManager;

    private MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList;

    private CourseManager() {
        courseList = new MyArrayList<>();
    }

    public MyArrayList<CourseNode<String, MyArrayList<MyClass>>> getCourseList() {
        return courseList;
    }

    public static CourseManager getInstance() {
        if (courseManager == null) {
            synchronized (CourseManager.class) {
                if (courseManager == null) {
                    courseManager = new CourseManager();
                }
            }
        }
        return courseManager;
    }
}
