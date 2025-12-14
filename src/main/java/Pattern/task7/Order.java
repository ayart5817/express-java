package Pattern.task7;

import java.util.List;

class Order {
    protected List<Product> items;
    private double discountPercental;
    private String paymentMethod;
    private double totalPrice;


    public Order(List<Product> items, double discountPercental,
                 String paymentMethod) {
        this.items = items;
        this.discountPercental = discountPercental;
        this.paymentMethod = paymentMethod;
        this.totalPrice = calculateTotalPrice();
    }


    private double calculateTotalPrice() {
        double sum = items.stream()
                .mapToDouble(Pattern.task7.Product::getPrice)
                .sum();
        return sum * (1 - discountPercental / 100);
    }

    public void showOrder() {
        System.out.println("===Детали заказа===");
        double sumer = 0;
        for (Product product : items) {
            System.out.println("  " + product);
            sumer += product.getPrice();
        }
        totalPrice = sumer * (1 - discountPercental / 100);
        System.out.println("Скидка: " + discountPercental + "%");
        System.out.println("Способ оплаты:" + paymentMethod);
        System.out.println("Итоговая Сумма:" + totalPrice);
        System.out.println("==========================");
    }
}
