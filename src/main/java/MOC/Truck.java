package MOC;

public class Truck extends TypeTransport{
    public Truck(String name, String value) {
        super(name, value);
    }

    @Override
    public void start() {

        System.out.println(name + " начал движение с грузом " + value);
    }
}
