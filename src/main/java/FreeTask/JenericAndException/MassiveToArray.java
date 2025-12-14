package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.List;

public class MassiveToArray<T> {

    static <T> List<T> methodToLust(T[] arr) {
        List<T> resultList = new ArrayList<>();
        for (T t : arr) {
            resultList.add(t);
        }
        return resultList;
    }

    static void main(String[] args) {
        Integer[] intArr = {1, 2, 3, 6, 5, 8, 4};
        System.out.println(methodToLust(intArr));

    }
}
