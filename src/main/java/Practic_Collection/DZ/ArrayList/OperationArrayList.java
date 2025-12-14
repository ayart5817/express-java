package Practic_Collection.DZ.ArrayList;

import java.util.ArrayList;

public class OperationArrayList {
    public ArrayList<Integer> integerArrayList = new ArrayList<>();
    public ArrayList<String> stringArrayList = new ArrayList<>();



    public void addAuto() {
        integerArrayList.add(10);
        integerArrayList.add(11);
        integerArrayList.add(12);
        integerArrayList.add(13);
        integerArrayList.add(14);

    }
    public  void addInt(int newInt) {
        integerArrayList.add(newInt);
    }

    public void printArray() {
        integerArrayList.forEach((integerArrayList) -> {
            System.out.println("номер:" + integerArrayList);
        });
    }
    public void printEvenNumbArray() {
        integerArrayList.forEach(number -> {
            if (number % 2 == 0) {
                System.out.println("Четное:" + number);
            }

       });
    }
    public void addStringArray() {
        stringArrayList.add("Яблоко");
        stringArrayList.add("Банан");
        stringArrayList.add("Апельсин");
        stringArrayList.add("Грейпфрут");
    }
    public void printStringArray() {
        for (String number : stringArrayList) {
            System.out.println("строка: " + number);
        }
    }
    public void getMaxLengthArray() {
        String longest = stringArrayList.get(0);
        for (int i = 0; i < stringArrayList.size(); i++) {
            String str = stringArrayList.get(i);  // ← ВОТ ЗДЕСЬ мы "задаём" str вручную!
            if (str.length() > longest.length()) {
                longest = str;
            }
        }
        System.out.println(longest + " Самый длинный в массиве");

    }
    public void sumAllIntArray() {
        int sum = 0;
        for (int number : integerArrayList) {
            sum = sum + number;
        }
        System.out.println("Сумма чисел в массиве" + sum);
    }
}

