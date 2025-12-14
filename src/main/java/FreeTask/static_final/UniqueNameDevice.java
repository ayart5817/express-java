package FreeTask.static_final;

public class UniqueNameDevice {
    private final String NAME ;
    private static int counter = 0;

    public UniqueNameDevice(String newName) {
        counter++;
        NAME = newName + counter;
    }

    @Override
    public String toString() {
      return NAME ;
    };

    static void main() {
        UniqueNameDevice name1 = new UniqueNameDevice("Device_");
        System.out.println(name1.toString());
        UniqueNameDevice name2 = new UniqueNameDevice("Device_");
        System.out.println(name2.toString());
    }

}
