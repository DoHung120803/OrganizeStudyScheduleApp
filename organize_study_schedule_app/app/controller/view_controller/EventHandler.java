package app.controller.view_controller;

import javax.swing.JTextField;

import app.model.course.CourseManager;
import app.model.course.MyClass;
import app.view.main.AppUI;
import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

public class EventHandler {
    private static CourseManager courseManager;

    private MyArrayList<CourseNode<String, MyArrayList<MyClass>>> exerciseClassesList;
    private MyArrayList<CourseNode<String, MyArrayList<MyClass>>> theoryClassesList;
    AppUI appUI = new AppUI();

    public void suggestionSearchCourseId() {

    }

}
