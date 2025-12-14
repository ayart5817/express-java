package FreeTask.Class_Object;

import java.util.Objects;

public class Product {
    String name;
    Integer price;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public String toString() {
        return "Product name = " + name  +
                ", price = " + price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name );
    }

    static void main(String[] args) {


        Product product = new Product("12345", 1000);
        System.out.println(product.toString());
    }
}
