package MOC;
//певозок земля - куб вода-контейнер воздух - багаж
public class Main {
    int x = 1;




    static void main(String[] args) {
        ManagerTransport manager = new ManagerTransport();
        Plane plane = new Plane("Самолет", "багаж");
        Truck truck = new Truck("Грузовик", "кубы");
        Ship ship = new Ship("Корабль", "контейнеры");
        manager.add(ship);
        manager.add(truck);
        manager.add(truck);
        manager.add(plane);
        manager.star();


    }
}
