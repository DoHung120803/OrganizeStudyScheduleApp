package app.controller.view_controller;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import app.model.course.CourseManager;
import app.model.course.MyClass;
import app.view.main.AppUI;
import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

public class EventHandler {
    private static CourseManager courseManager = CourseManager.getInstance();

    private MyArrayList<CourseNode<String, MyArrayList<MyClass>>> exerciseClassesList = courseManager
            .getExerciseClassesList();

    AppUI appUI;

    public EventHandler(AppUI appUI) {
        this.appUI = appUI;
        suggestionInputCourseIdHandler();
        fillSelectCourseSuggestion();
        chooseCourseIdBtnHandler();
        jTableCoursesListReadData();
    }

    public void suggestionInputCourseIdHandler() {
        List<String> courseIdList = new ArrayList<String>();
        JTextField inputCourseJFT = appUI.getInputCourseJFT();
        JList<String> jListSuggestion = appUI.getjListSuggestion();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        jListSuggestion.setModel(listModel);
        jListSuggestion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        readCourseId(courseIdList);

        inputCourseJFT.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSuggestion(inputCourseJFT, courseIdList, jListSuggestion, listModel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSuggestion(inputCourseJFT, courseIdList, jListSuggestion, listModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSuggestion(inputCourseJFT, courseIdList, jListSuggestion, listModel);
            }

        });
    }

    private void readCourseId(List<String> courseIdList) {
        for (int i = 0; i < exerciseClassesList.size(); i++) {
            String courseId = exerciseClassesList.get(i).getKey() + " - " +
                    exerciseClassesList.get(i).getValue().get(0).getCourse().getCourseName();

            courseIdList.add(courseId);
        }
    }

    private void updateSuggestion(JTextField inputCourseJFT, List<String> courseList, JList jListSuggestion,
            DefaultListModel listModel) {

        if (inputCourseJFT.getText().equals("")) {
            listModel.clear();
            jListSuggestion.setBorder(null);
            return;
        }

        jListSuggestion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        String courseId = inputCourseJFT.getText().toLowerCase();
        List<String> matchedCourses = new ArrayList<>();

        for (String course : courseList) {
            int index = course.trim().indexOf(" ");
            String courseIdStr = course.substring(0, index);
            if (courseIdStr.toLowerCase().contains(courseId)) {
                matchedCourses.add(course);
            }
        }

        // Hiển thị kết quả tìm kiếm trong JList
        listModel.clear();
        for (String matchedCourse : matchedCourses) {
            listModel.addElement(matchedCourse);
        }
    }

    public void fillSelectCourseSuggestion() {
        JList jListSuggestion = appUI.getjListSuggestion();
        JTextField inputCourseJFT = appUI.getInputCourseJFT();
        jListSuggestion.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    // Lấy giá trị được chọn và đưa vào JTextField
                    String selectedValue = (String) jListSuggestion.getSelectedValue();
                    inputCourseJFT.setText(selectedValue);
                    ((DefaultListModel) jListSuggestion.getModel()).clear();
                    jListSuggestion.setBorder(null);

                }
            }
        });
    }

    public void chooseCourseIdBtnHandler() {
        JButton chooseCourseIdBtn = appUI.getChooseCourseIdBtn();

        chooseCourseIdBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTextField inputCourseJFT = appUI.getInputCourseJFT();
                JPanel previewRegistedCourseIdPn = appUI.getjPanel2();
                GroupLayout previewRegistedCourseIdPnLayout = new GroupLayout(previewRegistedCourseIdPn);
                previewRegistedCourseIdPn.setLayout(previewRegistedCourseIdPnLayout);
                String courseId = inputCourseJFT.getText();
                JTextField registedCourseIdJTF = new JTextField();
                registedCourseIdJTF.setText(courseId);

                // Chỉnh sửa GroupLayout
                previewRegistedCourseIdPnLayout.setHorizontalGroup(
                        previewRegistedCourseIdPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(registedCourseIdJTF, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                previewRegistedCourseIdPnLayout.setVerticalGroup(
                        previewRegistedCourseIdPnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(registedCourseIdJTF, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                // Cố định kích thước của JPanel
                Dimension preferredSize = new Dimension(300, 200); // Thay đổi kích thước tùy ý
                previewRegistedCourseIdPn.setPreferredSize(preferredSize);

            }
        });
    }

    public void jTableCoursesListReadData() {
        JTable jTableCoursesList = appUI.getjTableCoursesList();
        DefaultTableModel tableModel = (DefaultTableModel) jTableCoursesList.getModel();

        tableModel.setRowCount(0);

        for (int i = 0; i < exerciseClassesList.size(); i++) {
            String courseId = exerciseClassesList.get(i).getKey();
            String courseName = exerciseClassesList.get(i).getValue().get(0).getCourse().getCourseName();
            String creditNumber = exerciseClassesList.get(i).getValue().get(0).getCourse().getCreditNumber();
            String numberOfClass = exerciseClassesList.get(i).getValue().size() + "";

            tableModel.addRow(new Object[] { courseId, courseName, creditNumber, numberOfClass });

        }
    }

}
