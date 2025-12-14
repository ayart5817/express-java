package FreeTask.OOP.Transport;

public class Plane  extends Transportation {

        public Plane() {
            super(40, "контейнер");
        }



    public void startTransportation() {
            System.out.println("Самолет начал перемещение. Объем груза "+getValueCargo() +" тип " +getCargo());
        }

}
