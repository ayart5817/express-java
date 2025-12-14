package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NumberUtils22 {

    public static double sum(Collection<? extends Number> l) {
        double result = 0;
        for (Number n : l) {
            result += n.doubleValue();
        }
    return result;
    }


    static void main(String[] args) {
        List<Float> l1 = new ArrayList<>(List.of(1.3f,2.1f,1.89021f));
        List<Integer> l2 = new ArrayList<>(List.of(1,2,33,6));

        System.out.println(sum(l1));
        System.out.println(sum(l2));
    }
}
