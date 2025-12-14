package Pattern.task3;

public abstract class TransportFactory {
    abstract Transport createTransport();

    public Transport gerTransport() {
        Transport transport = createTransport();
        System.out.println("Транспорт создан " );
        return transport;
    }
}
