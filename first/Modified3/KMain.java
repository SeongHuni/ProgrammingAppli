package Modified3;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class KMain {
  private static final String MENU_STR = """
            ====================================================
            1). 학생 추가
            2). 학생 삭제
            3). 학생 출력
            4). 종료
            ====================================================
            """;
  private static final Scanner sc = new Scanner(System.in);
  private static final KStudentList studentList = new KStudentList();

  public static void main(String[] args) {
    boolean isRunning = true;

    while (isRunning) {
      int choice = printMenu();

      switch (choice) {
        case 1 -> addStudent();
        case 2 -> deleteStudent();
        case 3 -> System.out.println(studentList);
        case 4 -> isRunning = false;
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
    KStudents student = inputStudent();
    if (studentList.find(student.getStudentId()) != -1) {
      System.out.println("학번 중복");
      return;
    }
    studentList.add(student);
    System.out.println("학생이 추가되었습니다.");
  }

  private static KStudents inputStudent() {
    System.out.print("이름: ");
    String name = sc.nextLine();
    System.out.print("학번: ");
    String studentId= sc.nextLine();
    System.out.print("주소: ");
    String address = sc.nextLine();
    return new KStudents(name, studentId, address);
  }

  private static void deleteStudent() {
    System.out.print("삭제할 학생 학번 입력: ");
    String name = sc.nextLine();
    try {
      studentList.remove(name);
      System.out.println("해당 학생 정보 삭제됨.");
    } catch (NoSuchElementException e) {
      System.out.println("해당 학생 정보 없음.");
    }
  }
}