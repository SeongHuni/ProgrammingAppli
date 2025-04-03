package Programming4;

public class Students implements Comparable<Students> {
  private String name;
  private String studentId;
  private String address;

  public Students(String name, String studentId, String address) {
    this.name = name;
    this.studentId = studentId;
    this.address = address;
  }

  public String getName() {
    return name;
  }
  public String getStudentId() {
    return studentId; // 학번 반환
  }
  public String getAddress() {
    return address;
  }

  @Override
  public int compareTo(Students o) {
    int compare = name.compareTo(o.name );
    if (compare == 0) {
      compare = studentId.compareTo(o.studentId);
      if (compare == 0) {
        return address.compareTo(o.address);
      }
    }
    return compare;
  }

  @Override
  public String toString() {
    return "['학생 이름:'" + name + "', 학번:'" + studentId + "', 주소:'" + address + "']" ;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}