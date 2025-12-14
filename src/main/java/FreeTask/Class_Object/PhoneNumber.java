package FreeTask.Class_Object;

import java.util.Objects;

public class PhoneNumber {
    String  countryCode;
     String       number;

     static String normalizeCounterCode(String countryCode) {
         countryCode = countryCode.replaceAll("[^0-9]", "").replaceAll("^0+(?=\\d+)", "");
         return countryCode;
     }
     static String normalizeNumber(String number) {
         number = number.replaceAll("[^0-9]", "");
         return number;
     }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(normalizeCounterCode(countryCode), normalizeCounterCode(that.countryCode)) && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(normalizeCounterCode(countryCode), number);
    }

    public PhoneNumber(String number, String countryCode) {
        this.number = number;
        this.countryCode = countryCode;
    }

    static void main(String[] args) {

    }
}
