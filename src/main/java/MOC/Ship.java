package MOC;

public class Ship extends TypeTransport {
    public Ship(String name, String value) {
        super(name, value);
    }

    @Override
    public void start() {

        System.out.println(name + " начал движение с грузом " + value);
    }
}


