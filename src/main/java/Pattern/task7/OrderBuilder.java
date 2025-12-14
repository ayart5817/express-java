package Pattern.task7;


import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {
    protected List<Product> products = new ArrayList<>();
    protected double discount = 0;
    protected String paymentMethod = "Карта онлайн";

    protected OrderBuilder addProduct(Pattern.task7.Product product) {
        products.add(product);
        return this;
    }

    protected OrderBuilder addProduct(String name, double price) {
        products.add(new Product(name, price));
        return this;
    }

    protected OrderBuilder setDiscount(double discount) {
        this.discount = discount;
        return this;
    }

    protected OrderBuilder setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    protected Order build() {
        return new Order(products, discount, paymentMethod);
    }

}
