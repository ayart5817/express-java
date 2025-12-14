package MOC;

/**
 * Передается число задача: возвращает true если число простое иначе false
 * простое делится на себя и на 1
 * пeредаем только 1 число от 1 до max.int
 * 1 , 2, 3, 5 - простое
 * 4.6 - не простое
 */


public class GetSimpleNumber {
    static boolean checkSimpleNumber(int n) {
        boolean flag = true;
        if (n == 0 || n == 1) {
            flag = false;
        }
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                flag = false;

            }

        }
        return flag;
    }

    static void main(String[] args) {
        System.out.println(checkSimpleNumber(1));
        System.out.println(checkSimpleNumber(2));
        System.out.println(checkSimpleNumber(3));
        System.out.println(checkSimpleNumber(4));
        System.out.println(checkSimpleNumber(5));
        System.out.println(checkSimpleNumber(6));
        System.out.println(checkSimpleNumber(0));


    }
}
