package MOC;

public class Increment {
    static int staticCount = 0;


    public Increment() {
        staticCount++;

    }

    static void main() {
Increment increment1 = new Increment();

        System.out.println(staticCount);
        System.out.println(Increment.staticCount);
        System.out.println(increment1.staticCount);
        Increment increment2 = new Increment();
        System.out.println(staticCount);
    }
}
