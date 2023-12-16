//package app.model.organization;
//import app.model.course.CourseManager;
//import app.model.course.MyClass;
//import app.model.read.ReadData;
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//import resource.arraylist.MyArrayList;
//import resource.node.CourseNode;
//
//public class TimetableGUI extends Application {
//    private MyArrayList<String[][]> boardList;
//
//    public TimetableGUI(MyArrayList<String[][]> boardList) {
//        this.boardList = boardList;
//    }
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Thời Khóa Biểu");
//
//        GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setHgap(5);
//        gridPane.setVgap(5);
//
//        // Tạo giao diện thời khóa biểu cho từng board
//        for (int k = 0; k < boardList.size(); k++) {
//            String[][] timetableData = boardList.get(k);
//
//            // Tạo khung biểu diễn thứ
//            String[] daysOfWeek = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6"};
//            for (int i = 0; i < daysOfWeek.length; i++) {
//                Label dayLabel = createLabel(daysOfWeek[i], "day-label");
//                gridPane.add(dayLabel, i * (timetableData[0].length + 1), 0);
//            }
//
//            // Tạo khung biểu diễn tiết
//            String[] periodsOfDay = {"Tiết 1", "Tiết 2", "Tiết 3", "Tiết 4", "Tiết 5", "Tiết 6", "Tiết 7", "Tiết 8", "Tiết 9", "Tiết 10"};
//            for (int i = 0; i < periodsOfDay.length; i++) {
//                Label periodLabel = createLabel(periodsOfDay[i], "period-label");
//                gridPane.add(periodLabel, 0, i + 1);
//            }
//
//            // Tạo nội dung thời khóa biểu
//            for (int i = 0; i < timetableData.length; i++) {
//                for (int j = 0; j < timetableData[i].length; j++) {
//                    String cellData = timetableData[i][j];
//                    Label cellLabel = createLabel(cellData != null ? cellData : "", "cell-label");
//                    gridPane.add(cellLabel, i * (timetableData[i].length + 1) + j + 1, i + 1);
//                }
//            }
//        }
//
//        Scene scene = new Scene(gridPane, 600, 400);
//        scene.getStylesheets().add("styles.css"); // Load file CSS
//        primaryStage.setScene(scene);
//
//        primaryStage.show();
//    }
//
//    private Label createLabel(String text, String styleClass) {
//        Label label = new Label(text);
//        label.getStyleClass().add(styleClass);
//        return label;
//    }
//
//    public static void main(String[] args) {
//        ScheduleOrganization scheduleOrganization = new ScheduleOrganization();
//        CourseManager courseManager = CourseManager.getInstance();
//        ReadData readData = new ReadData();
//        readData.readData();
//        MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList = courseManager.getExerciseClassesList();
//        MyArrayList<MyArrayList<MyClass>> allClassOfCourseList = scheduleOrganization.enterData(courseList);
//
//        // Sắp xếp danh sách học phần
//        MyArrayList<String[][]> boardList = new MyArrayList<>();
//        String[][] board = new String[5][10];
//
//        scheduleOrganization.organize(boardList, board, 0, 0, allClassOfCourseList);
//        // Thêm các board vào boardList
//
//        TimetableGUI timetableGUI = new TimetableGUI(boardList);
//        timetableGUI.launch(args);
//    }
//}
//
//
