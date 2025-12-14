package FreeTask.OOP.Transport;

public class Ship extends Transportation {



    public Ship() {
        super(10,  "багаж");
    }


    public void startTransportation() {
        System.out.println("Самолет начал перемещение. Объем груза "+getValueCargo() +" тип " +getCargo());
    }

}
