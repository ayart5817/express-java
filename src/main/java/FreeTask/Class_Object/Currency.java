package FreeTask.Class_Object;

import java.util.Objects;

public class Currency {
    int code;
    String symbol;

    public Currency(int code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return code == currency.code;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code=" + code +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    static void main(String[] args) {
        Currency s1 = new Currency(1, "a");
        Currency s2 = new Currency(2, "b");
        Currency s3 = new Currency(2, "c");
        System.out.println(s1);
        System.out.println(s2);

    }
}
