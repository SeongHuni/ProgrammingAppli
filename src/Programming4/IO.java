package FigureManage;

import java.io.*;
import java.util.Scanner;

public class IO {
  private static final String MENU_STRING = """
            ====================================================
            1). 학생 추가
            2). 학생 삭제
            3). 학생 검색
            4). 학생 출력
            5). 종료
            ====================================================""";

  private final Scanner scanner = new Scanner(System.in);

  public void load(List<Shape> list, String file) {
    try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
      while (true) {
        Shape shape = (Shape) objectInputStream.readObject();
        list.add(shape);
      }
    } catch (EOFException _) {

    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public Choice printMenu() {
    System.out.println(MENU_STRING);
    int input = scanner.nextInt();
    scanner.nextLine();
    return Choice.values()[input - 1];
  }

  public Choice menu() {
    System.out.println(MENU_STRING);
    int input = scanner.nextInt();
    scanner.nextLine();
    return Choice.values()[input - 1];
  }

  public

  public Shape getShapeInfo() {
    System.out.print("X좌표를 입력해주세요: ");
    int x = scanner.nextInt();
    System.out.print("Y좌표를 입력해주세요: ");
    int y = scanner.nextInt();
    System.out.println("반지름을 입력해주세요: ");
    int r = scanner.nextInt();
    return new Shape(x, y, r);
  }

  public String getShapeId(){
    System.out.println("Id를 입력해주세요: ");
    return scanner.nextLine();
  }

  public void display(List<Shape> list){

    for(int i = 0; i < list.size(); i++){
      Shape shape = list.get(i);
      System.out.println(shape.toString());
    }
  }

  public void save(List<Shape> list, String file) {
    try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
      for (int i = 0; i < list.size(); i++) {
        Shape shape = list.get(i);
        objectOutputStream.writeObject(shape);
      }
      objectOutputStream.flush();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
