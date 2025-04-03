package Modified3;

public class KStudents implements Comparable<KStudents> {
  private String name;
  private String studentId;
  private String address;

  public KStudents(String name, String studentId, String address) {
    this.name = name;
    this.studentId = studentId;
    this.address = address;
  }

  public String getStudentId() {
    return studentId; // 학번 반환
  }

  @Override
  public int compareTo(KStudents o) {
    int compare = studentId.compareTo(o.studentId);
    if (compare == 0) {
      compare = name.compareTo(o.name );
      if (compare == 0) {
        return address.compareTo(o.address);
      }
    }
    return compare;
  }

  @Override
  public String toString() {
    return "Student{'학생 이름:'" + name + "', 학번='" + studentId + "', 주소='" + address + "'}" ;
  }


}