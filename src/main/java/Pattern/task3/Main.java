package Pattern.task3;

public class Main {
    static void main(String[] args) {
        TransportFactory carFactory = new CarFactory();
        TransportFactory bikeFactory = new BicycleFactory();

        Transport car = carFactory.gerTransport();
        Transport bike = bikeFactory.gerTransport();
        bike.start();
        bike.stop();


    }
}
