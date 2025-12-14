package FreeTask.OOP.Transport;

public class Truck extends Transportation {

    public Truck() {
        super(90,"коробки");
    }

    public void startTransportation() {
        System.out.println("Грузовик начал перемещение. Объем груза "+getValueCargo() +" тип " +getCargo());
    }

}
