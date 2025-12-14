package Pattern.task7;

public class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - " + price + " руб.";
    }

    public double getPrice() {
        return price;
    }
}
