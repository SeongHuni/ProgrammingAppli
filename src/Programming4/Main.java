package Programming4;

import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
  private static final String MENU_STR = """
            ====================================================
            1). 학생 추가
            2). 학생 삭제
            3). 학생 출력
            4). 학생 정보 수정
            5). 종료
            ====================================================
            """;
  private static final Scanner sc = new Scanner(System.in);
  private static final StudentList studentList = new StudentList();
  private static final String file = "students.txt";

  public static void main(String[] args) {
    studentList.load(file); //파일에서 정보들을 읽어 Student 객체 생성 후 StudentList에 추가.
    boolean isRunning = true;

    while (isRunning) {
      int choice = printMenu();

      switch (choice) {
        case 1 -> addStudent();
        case 2 -> deleteStudent();
        case 3 -> System.out.println(studentList);
        case 4 -> retouch();
        case 5 ->{
          studentList.save(file);
          isRunning = false;
        }
        default -> System.out.println("잘못된 입력입니다.");
      }
    }
  }

  private static int printMenu() {
    System.out.println(MENU_STR);
    System.out.print("선택: ");
    while (!sc.hasNextInt()) {
      System.out.println("숫자를 입력해주세요.");
      sc.next();
    }
    int input = sc.nextInt();
    sc.nextLine(); // 버퍼 비우기
    return input;
  }

  private static void addStudent() {
    while (true){
      Students student = inputStudent();

      if (studentList.find(student.getStudentId()) != -1) {
        System.out.println("중복된 학번입니다. 처음부터 다시 입력해주세요.");
      }
      else {
        studentList.add(student);
        System.out.println("학생이 추가되었습니다.");
        break;
      }
    }
  }

  private static Students inputStudent() {
    System.out.print("이름: ");
    String name = sc.nextLine();
    System.out.print("학번: ");
    String studentId = sc.nextLine();
    System.out.print("주소: ");
    String address = sc.nextLine();
    return new Students(name, studentId, address);
  }

  private static void deleteStudent() {
    System.out.print("삭제할 학생 학번 입력: ");
    String studentId = sc.nextLine();
    try {
      studentList.remove(studentId);
      System.out.println("해당 학생 정보 삭제됨.");
    } catch (NoSuchElementException e) {
      System.out.println("해당 학생 정보 없음.");
    }
  }

  private static void retouch() {
    System.out.print("수정할 학생 학번 입력: ");
    String studentId = sc.nextLine();
    studentList.retouch(sc, studentId); // Corrected call
  }

}