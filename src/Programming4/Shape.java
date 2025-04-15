package FigureManage;

import java.io.Serializable;

public class Shape implements Serializable{
  private String id;
  private int x;
  private int y;
  private int radius;

  public Shape(String id, int x, int y, int radius) {
    this.id = id;
    this.x = x;
    this.y = y;
    this.radius = radius;
  }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public int getX() { return x; }
  public void setX(int x) { this.x = x; }
  public int getY() { return y; }
  public void setY(int y) { this.y = y; }
  public int getRadius() { return radius; }
  public void setRadius(int radius) { this.radius = radius; }

}
