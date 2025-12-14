package FreeTask.Class_Object;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Order {
    String orderId;
    String date;

    public Order(String orderId, String date) {
        this.orderId = orderId;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    static void main(String[] args) {
        Order order1 = new Order("123", "22/06");
        Order order2 = new Order("123", "22/07");
        Order order3 = new Order("124", "22/08");

        Map<Order, String> mapOrder = new HashMap<>();
        mapOrder.put(order1, "Заказы 1");
        mapOrder.put(order2, "Заказы 2");
        mapOrder.put(order3, "Заказы 3");

        // Проверка
        System.out.println("Размер мапы: " + mapOrder.size()); // → 2 (не 3!)
        System.out.println("Для order1: " + mapOrder.get(order1)); // → "Заказ 2 — перезапись!"
        System.out.println("Для order3: " + mapOrder.get(order3)); // → "Заказ 3"

        // Вывод всей мапы
        mapOrder.forEach((order, desc) ->
                System.out.println(order + " → " + desc));
    }
}