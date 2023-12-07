package app.model.organization;

import app.model.course.CourseManager;
import app.model.course.MyClass;
import app.model.read.ReadData;
import resource.arraylist.MyArrayList;
import resource.node.CourseNode;

import java.sql.SQLOutput;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ScheduleOrganization {
    public static void main(String[] args) {
        CourseManager courseManager = CourseManager.getInstance();
        ReadData readData = new ReadData();
        readData.readData();
        MyArrayList<CourseNode<String, MyArrayList<MyClass>>> courseList = courseManager.getCourseList();

        //Nhập mã học phần muốn đăng kí vào MyArrayList<danh sách mã lớp>
        //MyArrayList này lưu trữ các classList của mỗi học phần
        MyArrayList<MyArrayList<MyClass>> allClassOfCourseList = new MyArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("Nhập số môn học muốn đăng kí: ");
        int n = input.nextInt();

        String[] courseIds = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập mã học phần thứ " + (i + 1) + ": ");
            courseIds[i] = input.next();
        }

        for (int i = 0; i < n; i++) {
            //Kiểm tra các học phần vừa nhập có trong danh sách mã học phần hay không
            //Nếu không có ---> báo lỗi
            //Nếu có --> thêm vào MyArrayList<danh sách mã lớp>
            boolean found = false;
            for (int j = 0; j < courseList.size(); j++) {
                if (courseIds[i].equals(courseList.get(j).getKey())) {
                    found = true;
                    allClassOfCourseList.add(courseList.get(j).getValue());
                    break;
                }
            }

            if (found == false) {
                System.out.println("Mã học phần thứ " + (i + 1) + " không tìm thấy. Vui lòng nhập lại: ");
                courseIds[i] = input.next();
                i--;
            }
        }


        //Sắp xếp danh sách học phần
        MyArrayList<String[][]> boardList = new MyArrayList<>();
        for (int i = 0; i < allClassOfCourseList.get(0).size(); i++) {
            String[][] board = new String[5][10];

            organize(boardList, board, 0, i, allClassOfCourseList);
        }

        //In tkb
        for (int k = 0; k < boardList.size(); k++) {
            System.out.println("\n\n\n");
            System.out.printf("%-8s", "");
            for (int i = 0; i < 5; i++) {
                System.out.printf("%s%-15s", "|", "Thứ " + (i + 2));
            }
            System.out.println();
            for (int j = 0; j < boardList.get(k)[0].length; j++) {
                System.out.printf("%-8s", "Tiết" + (j + 2));
                for (int l = 0; l < boardList.get(k).length; l++) {
                    System.out.printf("%s%-15s", "|", boardList.get(k)[l][j]);
                }
                System.out.println();
            }
        }
    }

    public static void organize(MyArrayList<String[][]> boardList, String[][] board, int indexOfCourse, int indexOfClass, MyArrayList<MyArrayList<MyClass>> allClassOfCourseList) {
        //Nếu đã duyệt hết các môn thì return
        if (indexOfCourse >= allClassOfCourseList.size()) {
            return;
        }

        //Kiểm tra xem lớp myClass có thể đăng kí hay không(Bị trùng lịch ---> flase, không bị trùng ---> true)
        boolean insertToBoard = true;
        MyClass myClass = allClassOfCourseList.get(indexOfCourse).get(indexOfClass);
        int dayOfWeek = Integer.valueOf(myClass.getTime().getDayOfWeek());
        int timeStart = Integer.valueOf(myClass.getTime().getTime().substring(0, 1));
        int timeEnd = Integer.valueOf(myClass.getTime().getTime().substring(2));
        for (int i = timeStart - 1; i < timeEnd; i++) {
            if (board[dayOfWeek - 2][i] != null) {
                insertToBoard = false;
            }
        }

        //Tạo cloneBoard để đăng kí các lớp tiếp theo trong học phần này
        String[][] cloneBoard = new String[5][10];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                cloneBoard[i][j] = board[i][j];
            }
        }
        //Thêm myClass vào board nếu insertToBoard = true
        if (insertToBoard) {
            for (int i = timeStart - 1; i < timeEnd; i++) {
                board[dayOfWeek - 2][i] = myClass.getClassId();
            }

            //Nếu chưa phải môn cuối cùng thì duyệt môn tiếp theo
            //Ngược lại thì thêm board vào boardList và return
            if (indexOfCourse < allClassOfCourseList.size() - 1) {
                organize(boardList, board, indexOfCourse + 1, 0, allClassOfCourseList);
            } else {
                boardList.add(board);
            }
        }

        //Nếu chưa phải lớp cuối cùng thì duyệt lớp tiếp theo (Lúc này sử dụng cloneBoard thay cho board hiện tại)
        if (indexOfClass < allClassOfCourseList.get(indexOfCourse).size() - 1 && indexOfCourse != 0) {
            organize(boardList, cloneBoard, indexOfCourse, indexOfClass + 1, allClassOfCourseList);
        }
    }
}