package FreeTask.static_final;

public class Task3 {
    /**
     * Количесвто нечетных чисел в массиве 3-7
     **/

    static int[] myArray = {1, 2, 3, 4, 5, 6, 7};

   static void checkNumb(int[] myArray) {
        if (myArray.length < 3 || myArray.length > 7) {
            System.out.println("Массив должен быть размером не более 7 и не менее 3");
            return;
        }
        ;
        int count = 0;
        for (int n : myArray) {
            if (n % 2 == 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    static void main(String[] args) {
        checkNumb(myArray);
    }

}
