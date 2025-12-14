package FreeTask.Class_Object;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EmailAddress {
    String address;

    public EmailAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return Objects.equals(address.toLowerCase(), that.address.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(address.toLowerCase());
    }

    @Override
    public String toString() {
        return "EmailAddress{" +
                "address='" + address + '\'' +
                '}';
    }

    static void main(String[] args) {
        Set<EmailAddress> setEmail = new HashSet<>();
        EmailAddress address1 = new EmailAddress("adada@fapoief");
        EmailAddress address2 = new EmailAddress("adada@fapoIef");
        EmailAddress address3 = new EmailAddress("adada@fapoie");
    setEmail.add(address1);
    setEmail.add(address2);
    setEmail.add(address3);
        System.out.println(setEmail);
    }
}
