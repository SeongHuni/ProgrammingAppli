package Programming4;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentList {
  private Students[] students = new Students[10];
  private int size = 0;

  public void add(Students student) {
    if (size == students.length) {
      resize();
    }
    insert(student);
  }

  private void insert(Students student) {
    int insertIndex = findInsertIndex(student);
    for (int i = size; i > insertIndex; i--) {
      students[i] = students[i - 1];
    }
    students[insertIndex] = student;
    size++;
  }

  public void remove(String studentid) {
    int removeIndex = find(studentid);
    if (removeIndex == -1) {
      throw new NoSuchElementException("해당 학생 정보 없음");
    }
    delete(removeIndex);
  }

  private void delete(int index) {//특정학번을 가진 학생을 제거
    for (int i = index, maxIndex = size - 1; i < maxIndex; i++) {
      students[i] = students[i + 1];
    }
    students[size - 1] = null;
    size--;
  }

  public int find(String studentId) {
    for (int i = 0; i < size; i++) {
      if (students[i].getStudentId().equals(studentId)) {
        return i;
      }
    }
    return -1;
  }

  private int findInsertIndex(Students student) {
    for (int i = 0; i < size; i++) {
      if (students[i].compareTo(student) > 0) {
        return i;
      }
    }
    return size;
  }

  private void resize() {
    Students[] newStudents = new Students[students.length * 2];
    System.arraycopy(students, 0, newStudents, 0, students.length);
    students = newStudents;
  }

  // 학생 목록을 파일에서 로드
  public void load(String file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] data = line.split(",");
        if (data.length == 3) { // 이름, 학번, 주소
          Students student = new Students(data[0].trim(), data[1].trim(), data[2].trim());
          this.add(student); //this.add를 사용하여 현재 클래스의 메소드 호출
        }
      }
      System.out.println("파일 업로드 완료.");
    } catch (IOException e) {
      System.out.println("오류 발생: " + e.getMessage());
    }
  }

  // 학생 목록을 파일에 저장
  public void save(String file) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      for (int i = 0; i < size; i++) {
        Students student = students[i];
        writer.write(student.getName() + "," + student.getStudentId() + "," + student.getAddress());
        writer.newLine();
      }
      System.out.println("파일 저장완료.");
    } catch (IOException e) {
      System.out.println("오류가 발생: " + e.getMessage());
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(" [ 학생 목록 ] \n");
    for (int i = 0; i < size; i++) {
      sb.append(students[i].toString());
      if (i != size - 1) {
        sb.append(", \n");
      }
    }
    return sb.toString();
  }

  public void retouch(Scanner sc, String studentId) {
    int retouch = find(studentId);
    if (retouch != -1) {
      System.out.print("수정할 주소를 입력해주세요: ");
      String newAddress = sc.nextLine();
      students[retouch].setAddress(newAddress);
      System.out.println("주소가 성공적으로 수정되었습니다.");
    } else {
      System.out.println("해당 학번이 없습니다.");
    }
  }
}