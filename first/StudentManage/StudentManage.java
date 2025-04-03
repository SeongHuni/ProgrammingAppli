package Programming2;

import java.util.Scanner;
import java.util.Arrays;

public class StudentManage {
  private static final int INSERT = 1;  // 학생 추가
  private static final int DELETE = 2; // 학생 삭제
  private static final int DISPLAY = 3; // 학생 출력
  private static final int EXIT = 4;   // 프로그램 종료

  private static final int maxStudent = 100; // 최대 학생 수
  private static Student[] students = new Student[maxStudent]; // 학생 정보를 저장하는 배열
  private static int currentStudent = 0; // 현재 등록된 학생 수
  private static int choice;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean exit = true;

    while (exit) {
      choice = printInitialMenu(sc); // 메뉴 출력 및 선택

      switch (choice) {
        case INSERT:
          addStudent(sc);// 학생 명단 추가
          break;
        case DELETE:
          deleteStudent(sc);// 학생 명단 삭제
          break;
        case DISPLAY:
          outputStudent();// 학생 명단 출력
          break;
        case EXIT:
          exit = false; // 종료 - while문 빠져나감
          break;
      }
    }
    sc.close(); // Scanner 종료
  }

  private static int printInitialMenu(Scanner sc) {
    System.out.println("======================================");
    System.out.println(INSERT + "). 학생 추가");
    System.out.println(DELETE + "). 학생 삭제");
    System.out.println(DISPLAY + "). 학생 출력");
    System.out.println(EXIT + "). 종료");
    System.out.println("======================================");
    System.out.print("선택하세요: ");
    while (!sc.hasNextInt()) {
      System.out.println("숫자를 입력해주세요.");
      sc.next(); // 유효하지 않은 입력 제거
    }
    return sc.nextInt();
  }

  private static void addStudent(Scanner sc) {
    sc.nextLine(); // 버퍼 비우기
    if (currentStudent >= maxStudent) {
      System.out.println("학생 수가 최대입니다.");
      return;
    }
    System.out.print("학생 이름: ");
    String name = sc.nextLine();
    if (duplicate(name)) {
      System.out.println("Error: 동일한 이름의 학생이 이미 존재합니다.");
      return;
    }
    System.out.print("주소: ");
    String address = sc.nextLine();
    System.out.print("핸드폰 번호(형식: 010-XXXX-XXXX): ");
    String phone;
    while (true) {
      phone = sc.nextLine();
      if (isValidPhoneNumber(phone)) {
        break;
      } else {
        System.out.println("Error: 전화번호 형식이 아닙니다. 다시 입력해주세요.");
      }
    }

    students[currentStudent++] = new Student(name, address, phone); // 배열에 학생 추가
    System.out.println("해당 학생이 추가되었습니다.");
  }

  private static boolean duplicate(String name) {
    for (int i = 0; i < currentStudent; i++) {
      if (students[i].getName().equals(name)) {
        return true;
      }
    }
    return false;
  }

  private static void deleteStudent(Scanner sc) {
    sc.nextLine(); // 버퍼 비우기
    System.out.print("삭제할 학생의 이름을 입력해주세요: ");
    String name = sc.nextLine();

    boolean found = false; // 삭제 여부를 추적
    for (int i = 0; i < currentStudent; i++) {
      if (students[i].getName().equals(name)) {
        // 배열 요소 삭제 (뒤의 요소들을 앞으로 이동)
        for (int j = i; j < currentStudent - 1; j++) {
          students[j] = students[j + 1];
        }
        students[--currentStudent] = null; // 마지막 요소 제거
        System.out.println("학생이 삭제되었습니다.");
        found = true;
        break;
      }
    }

    if (!found) {
      System.out.println("해당 학생 정보 없음");
    }
  }

  private static void outputStudent() {
    if (currentStudent == 0) {
      System.out.println("등록된 학생 정보 없음.");
      return;
    }

    Student[] sortedStudents = Arrays.copyOf(students, currentStudent);

    // 이름 기준 내림차순 정렬
    Arrays.sort(sortedStudents, (s1, s2) -> s2.getName().compareTo(s1.getName()));

    // 정렬된 학생 정보 출력
    System.out.println("모든 학생 목록:");
    for (Student s : sortedStudents) {
      System.out.println("이름: " + s.getName() + ", 주소: " + s.getAddress() + ", 핸드폰 번호: " + s.getPhone());
    }
  }

  private static boolean isValidPhoneNumber(String phone) {
    return phone.matches("^010-\\d{4}-\\d{4}$");
  }
}

class Student {
  private final String name; // 학생 이름
  private final String address; // 학생 주소
  private final String phone; // 학생 핸드폰 번호

  public Student(String name, String address, String phone) {
    this.name = name;
    this.address = address;
    this.phone = phone;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }
}