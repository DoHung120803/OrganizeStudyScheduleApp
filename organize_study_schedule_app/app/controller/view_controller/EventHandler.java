package app.controller.view_controller;

import javax.swing.*;

import app.model.course.CourseManager;
import app.model.course.MyClass;
import app.model.read.ReadData;
import app.view.main.AppUI;
import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private static CourseManager courseManager = CourseManager.getInstance();

    private MyArrayList<CourseNode<String, MyArrayList<MyClass>>> exerciseClassesList = courseManager.getExerciseClassesList();
    private MyArrayList<CourseNode<String, MyArrayList<MyClass>>> theoryClassesList = courseManager.getTheoryClassesList();
    AppUI appUI;
    public EventHandler(AppUI appUI) {
         this.appUI = appUI;
    }

    public void suggestionSearchCourseId() {

    }
    public void dropDownList() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        List<String> suggestions = new ArrayList<>();
        for (int i = 0; i < exerciseClassesList.size(); i++) {
            suggestions.add(exerciseClassesList.get(i).getKey() + " - " +
                    exerciseClassesList.get(i).getValue().get(0).getCourse().getCourseName());
        }
        for (String suggestion : suggestions) {
            model.addElement(suggestion);
        }
        JComboBox<String> comboBox = appUI.getjComboBoxCourses();
        JTextField inputCourseJFT = appUI.getInputCourseJFT();
        comboBox.setModel(model);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                inputCourseJFT.setText(selectedOption);
            }
        });
    }
    public void chooseCourseId() {
        JButton chooseCourseIdBtn = appUI.getChooseCourseIdBtn();
        JTextArea jTextArea = appUI.getjTextArea();
        JTextField inputCourseJFT = appUI.getInputCourseJFT();

        chooseCourseIdBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                String text = inputCourseJFT.getText();
                jTextArea.append(text + "\n");
                inputCourseJFT.setText("");
            }
        });
    }
}
