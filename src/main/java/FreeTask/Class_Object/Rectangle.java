package FreeTask.Class_Object;

import java.util.Objects;

public class Rectangle {
    int width;
    int height;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return ((width * height) == (rectangle.height * rectangle.width));
    }

    @Override
    public int hashCode() {
        return Objects.hash(width * height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    static void main(String[] args) {
        Rectangle r1 = new Rectangle(10, 20);
        Rectangle r2 = new Rectangle(20, 10);
        System.out.println(r1.equals(r2));
    }
}
