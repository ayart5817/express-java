package Pattern.task10;

public class Kilometer {

    private final double kilometer;

    public Kilometer(double kilometer) {
        this.kilometer = kilometer;
    }

    public double getKilometer() {
        return kilometer;
    }

    @Override
    public String toString() {
        return "Kilometer: " + kilometer;
    }
}


