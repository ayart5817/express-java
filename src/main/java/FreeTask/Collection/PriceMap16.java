package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;

public class PriceMap16 {
    static Map<String, Integer> myPriceMap = new HashMap<>(Map.of("aple", 100, "banana", 150, "chocolate", 200));
    public static void setPrice(String str, Integer newPrice) {
        myPriceMap.put(str, newPrice);
    }
    public static void checkPrice(String name) {
        System.out.println( name + "–" +myPriceMap.get(name));
    }

    static void main(String[] args) {
        PriceMap16.checkPrice("banana");
        PriceMap16.setPrice("banana", 160);
        PriceMap16.checkPrice("banana");
    }
}
