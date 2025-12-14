package FreeTask.Collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ResultMapAlphabet {

static void printSorted( Map<String, String> data) {

    Map<String, String> sorted = new TreeMap<>();
    sorted.putAll(data);
    System.out.println("(алфавит):");
    for (Map.Entry<String, String> entry : sorted.entrySet()) {
        System.out.println(entry.getKey() + " → " + entry.getValue());
    }
};




    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("banana", "жёлтый");
        data.put("apple", "красный");
        data.put("cherry", "красная");
        data.put("авокадо", "зелёный");

        printSorted(data);
    }
}
