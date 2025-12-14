package FreeTask.static_final;

public class StaticFinal {

    public static void main(String[] arg) {

        System.out.println(HelpMathConstants.E);
        System.out.println(HelpMathConstants.PI);
    }
}
class HelpMathConstants {
    public double getE() {
        return E;
    }

    public double getPI() {
        return PI;
    }

    final static public double PI = 3.14159;
    final static public double E = 2.71828;

}

