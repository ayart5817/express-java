package Practic_Collection.DZ.Lection;

public class Main {
    public static void main(String[] args) {


        {
            Practic_Collection.DZ.ArrayList.OperationArrayList operationArrayList = new Practic_Collection.DZ.ArrayList.OperationArrayList();
            operationArrayList.addAuto();
            operationArrayList.addInt(15);
            System.out.println(operationArrayList); // вывод всех записей из массива
            operationArrayList.printArray();
            operationArrayList.printEvenNumbArray(); // вывод всех четных из массива
            operationArrayList.addStringArray();
            // operationArrayList.printStringArray();
            operationArrayList.getMaxLengthArray(); // вывод самой длинной строки
            operationArrayList.sumAllIntArray();  // выводит сумму всех чисел в списке.


        }
    }
}
