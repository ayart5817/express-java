package FreeTask.static_final;

public class LimitedObject {
    private String name;
    private static int counter = 1;

    public LimitedObject(String name) {
        if (counter > 3) {
          throw new IllegalStateException("Превышен лимит объекта на класс " + name);

        }
        counter++;
        this.name = name;
    }

    static void main() {
        LimitedObject limitedObject = new LimitedObject("1");
        LimitedObject limitedObject1 =new LimitedObject("2");
        LimitedObject limitedObject2= new LimitedObject("3");
        LimitedObject limitedObject3 = new LimitedObject("4");
        System.out.println();
    }
}
