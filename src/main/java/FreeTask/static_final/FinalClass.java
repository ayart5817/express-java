package FreeTask.static_final;

public final class FinalClass {
    String name;
    static int a =5;
    static int b =2;

    private FinalClass() {
        throw new UnsupportedOperationException("Нельзя выполнить, клас и конструктор приватны");
    }

    static void printFinalClassSum() {
        System.out.println("Сумма чисел а и b = " + (a+b));
    }

    static void printFinalClassMultiplication() {
        System.out.println("Сумма чисел а и b = " + a*b);
    }

    static void printFinalClassDivision() {
        System.out.println("Сумма чисел а и b = " + a/b);
    }
    static void printFinalClassSubtraction() {
        System.out.println("Сумма чисел а и b = " + (a-b));
    }
}
