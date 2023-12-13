package app.model.organization;

import app.model.course.CourseManager;
import app.model.course.MyClass;
import app.model.read.ReadData;
import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

import java.util.Scanner;

public class ScheduleOrganization {
    public static void main(String[] args) {
        CourseManager courseManager = CourseManager.getInstance();
        ReadData readData = new ReadData();
        readData.readData();
        MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList = courseManager.getExerciseClassesList();
        MyArrayList<MyArrayList<MyClass>> allClassOfCourseList = enterData(courseList);

        // Sắp xếp danh sách học phần
        MyArrayList<String[][]> boardList = new MyArrayList<>();
        String[][] board = new String[5][10];

        organize(boardList, board, 0, 0, allClassOfCourseList);

        // In tkb
        printBoardList(boardList);
    }

    public static MyArrayList<MyArrayList<MyClass>> enterData(
            MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList) {
        // Nhập mã học phần muốn đăng kí vào MyArrayList<danh sách mã lớp>
        // MyArrayList này lưu trữ các classList của mỗi học phần
        MyArrayList<MyArrayList<MyClass>> allClassOfCourseList = new MyArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập số môn học muốn đăng kí: ");
        int n = input.nextInt();

        String[] courseIds = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("\nNhập mã học phần thứ " + (i + 1) + ": ");
            courseIds[i] = input.next();

            // Kiểm tra các học phần vừa nhập có trong danh sách mã học phần hay không
            // Nếu không có ---> báo lỗi và yêu cầu nhập lại
            // Nếu có --> thêm vào MyArrayList<danh sách mã lớp>
            int indexOfCourseIdsInCourseList = findIndexOfCourseIdsInCourseList(courseList, courseIds[i]);

            if (indexOfCourseIdsInCourseList != -1) {
                allClassOfCourseList.add(courseList.get(indexOfCourseIdsInCourseList).getValue());

                // In ra các lớp của môn học này
                for (int k = 0; k < courseList.get(indexOfCourseIdsInCourseList).getValue().size(); k++) {
                    System.out.println(courseList.get(indexOfCourseIdsInCourseList).getValue().get(k));
                }
            } else {
                System.out.print("Mã học phần thứ " + (i + 1) + " không tìm thấy. Vui lòng nhập lại: ");
                courseIds[i] = input.next();
                i--;
            }
        }

        return allClassOfCourseList;
    }

    public static int findIndexOfCourseIdsInCourseList(MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList,
            String courseIds) {
        for (int j = 0; j < courseList.size(); j++) {
            if (courseIds.equals(courseList.get(j).getKey())) {
                return j;
            }
        }

        return -1;
    }

    public static void organize(MyArrayList<String[][]> boardList, String[][] board, int indexOfCourse,
            int indexOfClass, MyArrayList<MyArrayList<MyClass>> allClassOfCourseList) {
        // Nếu đã duyệt hết các môn hoặc các lớp thì return
        if (indexOfCourse >= allClassOfCourseList.size()
                || indexOfClass >= allClassOfCourseList.get(indexOfCourse).size()) {
            return;
        }

        // Kiểm tra xem lớp myClass có thể đăng kí hay không(Bị trùng lịch ---> flase,
        // không bị trùng ---> true)
        MyClass myClass = allClassOfCourseList.get(indexOfCourse).get(indexOfClass);
        int dayOfWeek = Integer.valueOf(myClass.getClassTime().getDayOfWeek());
        int timeStart = Integer.valueOf(myClass.getClassTime().getTime().substring(0, 1));
        int timeEnd = Integer.valueOf(myClass.getClassTime().getTime().substring(2));
        int dayOfWeekTheory = Integer.valueOf(myClass.getTheoryTime().getDayOfWeek());
        int timeStartTheory = Integer.valueOf(myClass.getTheoryTime().getTime().substring(0, 1));
        int timeEndTheory = Integer.valueOf(myClass.getTheoryTime().getTime().substring(2));

        // Tạo cloneBoard để đăng kí các lớp tiếp theo trong học phần này
        String[][] cloneBoard = new String[5][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cloneBoard[i][j] = board[i][j];
            }
        }

        // Thêm myClass vào board nếu insertToBoard = true
        if (classCanBeInserted(myClass, board, dayOfWeek, timeStart, timeEnd,
                dayOfWeekTheory, timeStartTheory, timeEndTheory)) {
            for (int i = timeStart - 1; i < timeEnd; i++) {
                board[dayOfWeek - 2][i] = myClass.getClassId();
            }

            // Nếu myClass có lớp l thuyết ---> add lớp lý thuyết vào board
            if (myClass.getTheoryId() != null) {
                for (int i = timeStartTheory - 1; i < timeEndTheory; i++) {
                    board[dayOfWeekTheory - 2][i] = myClass.getTheoryId();
                }
            }

            // Nếu là môn cuối cùng thì thêm board vào boardList
            // Ngược lại thì duyệt môn tiếp theo
            if (indexOfCourse >= allClassOfCourseList.size() - 1) {
                boardList.add(board);
            } else {
                organize(boardList, board, indexOfCourse + 1, 0, allClassOfCourseList);
            }
        }

        // Duyệt lớp tiếp theo (Lúc này sử dụng cloneBoard thay cho board hiện tại)
        organize(boardList, cloneBoard, indexOfCourse, indexOfClass + 1, allClassOfCourseList);
    }

    public static boolean classCanBeInserted(MyClass myClass, String[][] board,
            int dayOfWeek, int timeStart, int timeEnd,
            int dayOfWeekTheory, int timeStartTheory, int timeEndTheory) {
        for (int i = timeStart - 1; i < timeEnd; i++) {
            // thứ trong tuần bắt đầu từ thứ 2 nhưng ở trong mảng 2 chiều thì bắt đầu từ 0
            // nên trừ đi 2
            if (board[dayOfWeek - 2][i] != null) {
                return false;
            }
        }

        if (myClass.getTheoryId() != null) {
            for (int i = timeStartTheory - 1; i < timeEndTheory; i++) {
                if (board[dayOfWeekTheory - 2][i] != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoardList(MyArrayList<String[][]> boardList) {
        for (int k = 0; k < boardList.size(); k++) {
            System.out.println("\n\n\n");
            System.out.printf("%-8s", "");
            for (int i = 0; i < 5; i++) {
                System.out.printf("%s%-15s", "|", "Thứ " + (i + 1));
            }
            System.out.println();
            for (int j = 0; j < boardList.get(k)[0].length; j++) {
                System.out.printf("%-8s", "Tiết" + (j + 1));
                for (int l = 0; l < boardList.get(k).length; l++) {
                    System.out.printf("%s%-15s", "|", boardList.get(k)[l][j]);
                }
                System.out.println();
            }
        }
    }
}