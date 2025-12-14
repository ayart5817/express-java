package FreeTask.Class_Object;

import java.util.Objects;

public class Point2D {
    int x;
    int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object p) {
        if (this == p) return true;
        if (p == null || p.getClass() != getClass()) return false;
        Point2D that = (Point2D) p;
        return this.x == that.x && this.y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    // Тест
    public static void main(String[] args) {
        Point2D a = new Point2D(1, 2);
        Point2D b = new Point2D(1, 2);
        Point2D c = new Point2D(3, 4);

        System.out.println(a.equals(b)); // true
        System.out.println(a.equals(c)); // false
        System.out.println(a.hashCode() == b.hashCode()); // true
    }
}
