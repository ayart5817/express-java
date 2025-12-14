package FreeTask.OOP.TranspotCompany;

class Ship extends TransportEntities{

    public Ship(String ID, int  capacity, String type) {
        super(ID, capacity, type);
    }

    @Override
    public void start() {
        System.out.println(getID() + " начал перевозку. Тип груза "+ getType() + " объём " + getCapacity());

    }
}

class Truck extends TransportEntities{

    public Truck(String ID, int  capacity, String type) {
        super(ID, capacity, type);
    }

    @Override
    public void start() {
        System.out.println(getID() + " начал перевозку. Тип груза "+ getType() + " объём " + getCapacity());

    }
}
class Plane extends TransportEntities{

    public Plane(String ID, int  capacity, String type) {
        super(ID, capacity, type);
    }

    @Override
    public void start() {
        System.out.println(getID() + "  начал перевозку. Тип груза "+ getType() + " объём " + getCapacity());

    }
}

