package FreeTask.OOP.TranspotCompany;

import java.util.ArrayList;
import java.util.List;

public class TransportationManagement {

    public TransportEntities addTransport(TransportEntities transportEntities) {
        return transportEntities;
    };

    public void startTransportation(TransportEntities transportEntities) {
        transportEntities.start();
}
    List<TransportEntities> transport= new ArrayList<>();
    public void addTransport(TransportEntities ... transportEntities) {
        for (TransportEntities t: transport) {
            this.transport = transport;
        }
    }
    public void startTransportation(TransportEntities ... transportToStart) {
        for (TransportEntities t : transportToStart) {
            startTransportation(t);
        }
    }
}
