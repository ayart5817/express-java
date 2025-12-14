package FreeTask.static_final;

public class Order {
    private static int id ;
    private static int countId = 1;


    public Order() {
        this.id = countId++;
    }

    public int getOrderId() {
        return id;
    }
}
