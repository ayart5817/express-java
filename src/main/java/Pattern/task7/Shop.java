package Pattern.task7;

public class Shop {

    static void main(String[] args) {


        OrderBuilder builder = new OrderBuilder();

        Product laptop = new Product("Laptop", 50000);
        Product mouse = new Product("Mouse", 9000);
        Product keyword = new Product("Keyword", 10000);

        Order order1 = builder.addProduct(laptop)
                .addProduct(mouse)
                .addProduct("Headphones", 6000)
                .setDiscount(15)
                .setPaymentMethod("Картой")
                .build();
        order1.showOrder();
        Order order2 = new OrderBuilder()
                .addProduct(keyword)
                .addProduct("Monitor", 30000)
                .setDiscount(15)
                .setPaymentMethod("Наличными при получении")
                .build();

        order2.showOrder();

    }
}
