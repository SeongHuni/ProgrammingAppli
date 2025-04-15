package FigureManage;

public class Main {
  private static final IO IO = new IO();
  private static final String file = "figureManage.txt";

  public static void main(String[] args) {
    List<Shape> shapes =new List<>();

    boolean isRuning = true;

    while (isRuning) {
      Choice choice = IO.printMenu();
      switch (choice) {
        case INSERT -> insert(shapes);
        case DELETE -> delete(shapes);
        case DISPLAY -> IO.diplay(shapes);
        case EXIT -> isRuning = false;

      }

    }

    IO.save(shapes, file);
  }

  private static void insert(List<Shape> shapes){
    Shape shape = IO.getShapeInfo();
    if (find(shapes, shape.getId())!= null) {
      System.out.println("중복된 도형이 존재합니다.");
      return;
    }
    shapes.add(shape);

  }

  private static void delete(List<Shape> shapes){
    String infoShape = IO.getShape();
    Shape shape = findShape(shapes, infoShape);
    if (find(shapes, shape.getId()) != null) {
      System.out.println("존재하지 않는 도형입니다.");
      return;
    }
    shapes.remove(shape);

  }

  private static Shape find(List<Shape> shapes, String id){
    for (int i = 0; i < shapes.size(); i++){
      Shape shape = shapes.get(i);
      if (shape.getId().equals(id)){
        return shape;
      }
    }
    return null;
  }

}
