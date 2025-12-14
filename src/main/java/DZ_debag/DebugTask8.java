package DZ_debag;

public class DebugTask8 {
    public static void main(String[] args) {
        double a = 0.1 * 3;
        double b = 0.3;
        double EPS = 1e-9;
        if (equals(a,b,EPS)) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }
    }

    private static boolean equals(double a, double b, double eps) {
        return Math.abs(a-b) <= eps;
    }
}
