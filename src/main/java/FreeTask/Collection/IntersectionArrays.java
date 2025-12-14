package FreeTask.Collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IntersectionArrays {
    static List<Integer> lis1 = new ArrayList<>(List.of(1,2,3,4,5,2,2));
    static  List<Integer> lis2 = new ArrayList<>(List.of(6,7,8,4,5,2,2,2));

    static public void checkIntersection(List<Integer> x,List<Integer> y) {
        Set<Integer> result = new HashSet<>();
        for (Integer n : x) {
            for (Integer n2: y) {
                 if (n.equals(n2)) {
                     result.add(n2);
                 }
            }
        }
        System.out.println(result);
    }
    static public void checkIntersectionRetain(List<Integer> x,List<Integer> y) {
        Set<Integer> result2 = new HashSet<>(x);
        result2.retainAll(new HashSet<>(y));
        System.out.println(result2);
    }

    static void main(String[] args) {
        IntersectionArrays.checkIntersection(lis1, lis2);
        IntersectionArrays.checkIntersectionRetain(lis1, lis2);
    }
}
