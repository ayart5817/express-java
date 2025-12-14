package FreeTask.OOP.TranspotCompany;

public class Main {
    public static void main(String[] arg) {


    TransportationManagement transportationManagement = new TransportationManagement();
   TransportEntities a = transportationManagement.addTransport( new Ship("крабль1", 100, "Контейнер с рыбой" ));
        TransportEntities b =    transportationManagement.addTransport(new Ship("крабль11", 100, "Контейнер с рыбой" ));
        TransportEntities c = transportationManagement.addTransport(new Truck("Грузовик", 10, "коробки"));
        TransportEntities d = transportationManagement.addTransport(new Plane("Самолет1", 18, "Багаж" ));
        TransportEntities e =  transportationManagement.addTransport(new Plane("Самолет1", 19, "Багаж"));

        transportationManagement.startTransportation(a);
        transportationManagement.startTransportation(b);
        transportationManagement.startTransportation(c);
        transportationManagement.startTransportation(d);
        transportationManagement.startTransportation(e);

        Ship s1 = new Ship("Корабль3", 100, "Рыба");
        Truck t1 = new Truck("Грузовик3", 10, "Коробки");
        Plane p1 = new Plane("Самолёт3", 20, "Багаж");

        // Добавляем
        transportationManagement.addTransport(s1, t1, p1);

        // Запускаем выбранные
        transportationManagement.startTransportation(s1, p1,t1);
}
}
