package MOCTask2;

public class Main {
    static void main(String[] arg) {
        ServesMail servesMail = new ServesMail();
        Fragle a = new Fragle(100);
        servesMail.add(a);
        servesMail.Send(a);
        Litter b = new Litter(30);
        servesMail.add(b);
        servesMail.Send(b);
        Shipment c = new Shipment(25);
        servesMail.add(c);
        servesMail.Send(c);



    }
}
