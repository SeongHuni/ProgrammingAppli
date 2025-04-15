package FigureManage;

import java.io.Serializable;

public class Shape implements Serializable {
    private String id;
    private int x, y, radius;

    public Shape(String id, int x, int y, int radius) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public String getId() { return id; }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    public double getDistance() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return String.format("ID : %s / 좌표 : (%d, %d)  반지름 : %d / 면적 : %.2f / 둘레 : %.2f / 거리 : %.2f",
                id, x, y, radius, getArea(), getPerimeter(), getDistance());
    }
}
