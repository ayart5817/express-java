package FreeTask.Class_Object;

import java.util.Objects;

public class Client {
    String fullName;
    String clientCode;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return  Objects.equals(clientCode, client.clientCode);
    }

    @Override
    public String toString() {
        return "Client{" +
                "fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientCode);
    }

    public Client(String fullName, String clientCode) {
        this.fullName = fullName;
        this.clientCode = clientCode;
    }
}
