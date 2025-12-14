package MOC;

import java.util.ArrayList;
import java.util.List;

public class ManagerTransport {
    List<TypeTransport> transportList = new ArrayList<>();

    public void add(TypeTransport tr) {
        transportList.add(tr);
    }

    public void star() {
        transportList.forEach(s -> s.start());
    }
}
