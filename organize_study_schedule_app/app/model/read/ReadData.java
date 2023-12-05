package app.model.read;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import app.model.course.Course;
import app.model.course.CourseManager;
import app.model.course.MyClass;
import app.model.course.Time;
import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

public class ReadData {

    private CourseManager courseManager = CourseManager.getInstance();

    public void readData() {

        BufferedReader dataReader = null;
        String prevCourseId = null;
        MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList = courseManager.getCourseList();
        try {
            String line;
            dataReader = new BufferedReader(new FileReader("TKB-gui-SV.csv", StandardCharsets.UTF_8));
            // Read file line by line?
            while ((line = dataReader.readLine()) != null) {
                List<String> dataList = parseDataLineToList(line);

                // course infor
                String courseId = dataList.get(0).trim();
                String courseName = dataList.get(1).trim();
                String creditNumber = dataList.get(2).trim();

                Course course = new Course(courseId, courseName, creditNumber);

                // time infor
                String dayOfWeek = dataList.get(4).trim();
                String time = dataList.get(5).trim();
                String room = dataList.get(6).trim();

                Time classTime = new Time(dayOfWeek, time, room);

                // class infor
                String classId = dataList.get(3).trim();
                MyClass myClass = new MyClass(classId, course, classTime);

                String currentCourseId = dataList.get(0);

                if (!currentCourseId.equals(prevCourseId)) {
                    CourseNode<String, MyArrayList<MyClass>> courseNode = new CourseNode<>(courseId,
                            new MyArrayList<MyClass>());

                    courseList.add(courseNode);
                    courseList.get(courseList.size() - 1).getValue().add(myClass);

                    prevCourseId = currentCourseId;
                } else {
                    courseList.get(courseList.size() - 1).getValue().add(myClass);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataReader != null)
                    dataReader.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }

    }

    public List<String> parseDataLineToList(String dataLine) {
        List<String> result = new ArrayList<>();
        if (dataLine != null) {
            String[] splitData = dataLine.split(",");
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }

    public String[] parseDataLineToArray(String dataLine) {
        if (dataLine == null) {
            return null;
        }

        return dataLine.split("\\,");
    }

}
