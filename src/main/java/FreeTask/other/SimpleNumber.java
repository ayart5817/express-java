package FreeTask.other;

import java.util.Arrays;


/**
 * бинарный поиск
 *
 */
public class SimpleNumber {

    public static boolean binSearch(int[] a, int target) {

        int halfA = a.length/2;
        int min = 0;
        int max = a.length -1 ;
        int [] arrayTest = new int[]{1, 5, 6, 3, 2, 1, 7, 3, 9, 0, 91};
        Arrays.sort(arrayTest);
        System.out.println(Arrays.toString(arrayTest));

        while (min <= max) {
            int mid = (max - min) / 2 + min;
            if (target == a[mid]) {
                System.out.println("элемент найден по индексу " + mid);
                return true;
            } else if (target > a[mid]) {
                min = mid + 1; //ищем в правой стороне
            } else {
                max = mid - 1; // ищем в левой стороне
            }
        }
        System.out.println("не найден");
        return false;


    }


    public static void main(String[] args) {
        int [] a = {5,7,9,11,12};
        System.out.println(binSearch(a,7));
        System.out.println(binSearch(a,9));
        System.out.println(binSearch(a,10));
        System.out.println(binSearch(a,13));
        System.out.println(binSearch(a,3));
        System.out.println(binSearch(a,10));
    }
  }

