package Modified3;

import java.util.NoSuchElementException;

public class KStudentList {
  private KStudents[] students = new KStudents[10];
  private int size = 0;

  public void add(KStudents student) {
    if (size == students.length) {
      resize();
    }
    insert(student);
  }

  private void insert(KStudents student) {
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

  private int findInsertIndex(KStudents student) {
    for (int i = 0; i < size; i++) {
      if (students[i].compareTo(student) > 0) {
        return i;
      }
    }
    return size;
  }

  private void resize() {
    KStudents[] newStudents = new KStudents[students.length * 2];
    System.arraycopy(students, 0, newStudents, 0, students.length);
    students = newStudents;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("StudentList{");
    for (int i = 0; i < size; i++) {
      sb.append(students[i].toString());
      if (i != size - 1) {
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }
}