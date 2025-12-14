package MOC;

public class Plane extends TypeTransport{
    public Plane(String name, String value) {
        super(name, value);
    }

    @Override
    public void start() {

        System.out.println(name + " начал движение с грузом " + value);
    }
}

