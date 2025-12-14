package ComplexTask2.Task5;


/**
 * Склад - управление товарами Product(Abstract) -add - isInventoryOpen(метод) + (Исключение OutOfStockException)
 * название, цена и категория (свойства)
 * InventoryService - управление складом отдельный класс
 * Тип хранения данных Map<String, List<Product>> где ключ - это категория товара
 * Метод для добавления товара на склад. Если флаг isInventoryOpen равен false,
 * операция добавления не должна выполняться.
 */

public class Product {
    private final String productName;
    private final double price;


    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;

    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}
