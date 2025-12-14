package Pattern.task10;

public class MilesToKilometersAdapter implements Distance{
    private static final double MILES_TO_KM = 1.60934;
    private final Miles miles;

    @Override
    public double getValue() {
        return this.miles.getMiles() * MILES_TO_KM;
    }
    public MilesToKilometersAdapter(Miles miles) {
        this.miles = miles;
    }

    @Override
    public String toString() {
        return miles.toString() + " – " +getValue() + " км.";
    }
}
