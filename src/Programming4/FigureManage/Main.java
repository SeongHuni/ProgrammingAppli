package FigureManage;

public class Main {
    private static final IO io = new IO();
    private static final String FILE = "Shape.dat";
    private static int idCounter = 1;

    public static void main(String[] args) {
        List<Shape> shapes = new List<>();
        io.load(shapes, FILE);
        updateIdCounter(shapes);

        boolean running = true;
        while (running) {
            Choice choice = io.printMenu(); // 메뉴
            switch (choice) {
                case INSERT -> insert(shapes); // 추가
                case DELETE -> delete(shapes); // 삭제
                case DISPLAY -> io.display(shapes); // 출력
                case EXIT -> running = false; // 종료
            }
        }
        io.save(shapes, FILE); // 종료 시 정보 저장
    }

    private static void updateIdCounter(List<Shape> shapes) {
        int max = 0;
        for (int i = 0; i < shapes.size(); i++) {
            String id = shapes.get(i).getId();
            if (id.length() == 3 && id.charAt(0) == 'C') {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > max) {
                        max = num;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        idCounter = max + 1;
    }

    //도형 삽입
    private static void insert(List<Shape> shapes) {
        String id = generateId(shapes);
        Shape shape = io.getShapeInfo(id);
        shapes.add(shape);
    }

    //Id 생성
    private static String generateId(List<Shape> shapes) {
        for (int i = 1; i <= 99; i++) {
            String id;
            if (i < 10) {
                id = "C0" + i; // 1~9는 앞에 0을 붙임
            } else {
                id = "C" + i;
            }

            if (find(shapes, id) == null) {
                return id;
            }
        }
        throw new IllegalStateException("더 이상 생성할 수 있는 ID가 없습니다."); //99이상 생성 방지
    }

    // 도형 삭제
    private static void delete(List<Shape> shapes) {
        String id = io.getShapeId(); // 삭제할 ID 입력
        Shape found = find(shapes, id); // 도형 찾기
        if (found == null) {
            System.out.println("존재하지 않는 도형입니다.");
        } else {
            shapes.remove(found); // 도형 삭제
            System.out.println("도형이 삭제되었습니다.");
        }
    }

    // ID로 도형 찾기
    private static Shape find(List<Shape> shapes, String id) {
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            if (shape.getId().equals(id)) {
                return shape;
            }
        }
        return null;
    }

}
