package FreeTask.Collection;

import java.util.HashSet;
import java.util.Set;

public class UnionSets23 {
    static Set<String> set1 = Set.of("1","2","3");
    static Set<String> set2 = Set.of("4","5","6");
    static Set<String> set3 = Set.of("7","8","9");

    static public void unionSet() {
        Set<String> union = new HashSet<>();
        union.addAll(set1);
        union.addAll(set2);
        union.addAll(set3);
        System.out.println(union);
    }

    static void main(String[] args) {
        UnionSets23.unionSet();
    }
}
