package FreeTask.OOP.Transport;

public class Main {
    static void main() {
        Transportation[] transportation = {
            new Plane(),
            new Ship(),
            new Truck(),
        };

        SuppliesServes suppliesServes = new SuppliesServes();
        for (Transportation t: transportation) {
            t.startTransportation();
        }

    }
}
