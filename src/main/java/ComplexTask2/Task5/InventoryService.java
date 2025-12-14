package ComplexTask2.Task5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryService {
    Map<String, List<Product>> mapProduct = new HashMap<>();
    public boolean isInventoryOpen = true;

    public void addProduct(String key, Product product) throws OutOfStockException {
        if (!isInventoryOpen) throw new OutOfStockException("Склад закрыт");
        if (product.getProductName() == null || product.getProductName().trim().isEmpty()) {
            throw new OutOfStockException("Нет имени у товара либо товар пуст");
        }

        mapProduct.computeIfAbsent(key, k -> new ArrayList<>()).add(product);
    }


    public List<Product> getProduct(String key) throws OutOfStockException {
        if (key == null || key.trim().isEmpty()) {
            throw new OutOfStockException("категория пуста " + key);
        }
        List<Product> products = mapProduct.get(key);
        if (products == null || products.isEmpty()) {
            throw new OutOfStockException("Нет товара по данной категории " + key);
        }
        return new ArrayList<>(products);
    }

     List<Product> filterPrice(int min, int max) throws IllegalArgumentException {
        if (min > max) {
            throw new IllegalArgumentException("min > max");
        }
        return mapProduct.values().stream()
                .flatMap(List::stream)
                .filter(product -> {
                            int price = (int) product.getPrice();
                            return price >= min && price <= max;
                        }
                )
                .collect(Collectors.toList());
    }

}
