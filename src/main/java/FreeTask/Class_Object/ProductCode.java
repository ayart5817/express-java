package FreeTask.Class_Object;

import java.util.Objects;

public class ProductCode {
    String code;
    static String first8Sumvol(String code) {
        return code.substring(0, 8);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductCode that = (ProductCode) o;
        return Objects.equals(first8Sumvol(code), first8Sumvol(that.code));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first8Sumvol(code));
    }

    @Override
    public String toString() {
        return "ProductCode" +
                "code = " + code +
                "first8sumbol = " + first8Sumvol(code);
    }

    public ProductCode(String code) {
        this.code = code;
    }

    static void main(String[] args) {
        ProductCode p1 = new ProductCode("1234567890123");
        System.out.println(p1.toString());
    }
}
