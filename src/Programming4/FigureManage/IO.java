package FigureManage;

import java.io.*;
import java.util.Scanner;

public class IO {
    private static final String MENU_STRING = """
            ====================================================
            1).  도형 추가
            2).  도형 삭제
            3).  도형 출력
            4).  종료
            ====================================================
            숫자를 입력해 주십시오.: """;
    private final Scanner scanner = new Scanner(System.in);

    public void load(List<Shape> list, String file) {
        if (!new File(file).exists()) {
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Shape shape = (Shape) ois.readObject();
                list.add(shape);
            }
        } catch (EOFException ignored) {
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

    public Shape getShapeInfo(String id) {
        System.out.print("X좌표를 입력해주세요: ");
        int x = scanner.nextInt();
        System.out.print("Y좌표를 입력해주세요: ");
        int y = scanner.nextInt();
        System.out.print("반지름을 입력해주세요: ");
        int r = scanner.nextInt();
        scanner.nextLine();
        return new Shape(id, x, y, r);
    }

    public String getShapeId() {
        System.out.print("ID를 입력해주세요: ");
        return scanner.nextLine();
    }

    public void display(List<Shape> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getDistance() > list.get(j).getDistance()) {
                    list.swap(i, j);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    public void save(List<Shape> list, String file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < list.size(); i++) {
                oos.writeObject(list.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}