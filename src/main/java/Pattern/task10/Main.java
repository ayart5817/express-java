package Pattern.task10;

public class Main {
    static void main(String[] args) {
        Miles distance1 = new Miles(50);
        Miles distance2 = new Miles(0);
        Distance adapter = new MilesToKilometersAdapter(distance1);
        System.out.println(distance1.toString());
        System.out.println(adapter.toString());
        System.out.println(new MilesToKilometersAdapter(distance2).toString());
    }
}
