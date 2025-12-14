package FreeTask.Cycles;

import java.util.Random;
import java.util.Scanner;

public class CalculationDevisorsNumber17_70 {


    public static void CalculatedMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int number = scanner.nextInt();
        int countDivide = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                countDivide++;
            }

        }
        System.out.println("у числа " + number + " делителей " + countDivide);
    }

    public static void FactorialMethod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int number = scanner.nextInt();
        int factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;

        }
        System.out.println("у числа " + number + " Факториал " + factorial);
    }

    public static void FindFirstDivisor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int number = scanner.nextInt();
        int first = 0;
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                first = i;
                break;
            }

        }
        System.out.println("у числа " + number + " первый делитель " + first);
    }

    public static void primeNumberCheck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int number = scanner.nextInt();
        boolean isPrime = true;
        for (int i = 2; i <= number - 1; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }

        }
        System.out.println("Число " + number + (isPrime ? " простое " : " составное "));
        scanner.close();

    }

    public static void findSumNumberDigits() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        int n = scanner.nextInt();
        int sumN = 0;
        for (int i = 0; i < n; i++) {
            sumN = sumN + i;
        }
        System.out.println(sumN);
    }

    public static void maxSeriesNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вводите серию до 0 ");
        int n;
        int max = scanner.nextInt();
        while ((n = scanner.nextInt()) != 0) {
            if (max < n) {
                max = n;

            }

        }
        System.out.println("максимальное число N = " + max);
    }

    public static void numberCounterPositiveNegative() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вводите серию bp 10 чисел ");
        int countNegative = 0;
        int countPositive = 0;
        int countZero = 0;
        int i = 0;
        while (i < 10) {
            i++;
            int n = scanner.nextInt();
            if (n > 0) {
                countPositive++;
            }
            if (n < 0) {
                countNegative++;
            }
            if (n == 0) {
                countZero++;
            }
        }
        System.out.println("Позитивных " + countPositive + "\nнегативных " + countNegative + " \nНулей " + countZero);
    }

    public static void ReversNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер");
        int n = scanner.nextInt();
        if (n == 0) {
            System.out.println("0");
        } else {
            while (n > 0) {
                System.out.print(n % 10);
                n /= 10;
            }
            // System.out.println();
        }

    }

    public static void simpleNumber100() {
        for (int num = 2; num <= 100; num++) {
            boolean isProme = true;
            for (int i = 2; num > i * i; i++) {
                if (num % i == 0) {
                    isProme = false;
                    break;
                }
            }
            System.out.print(isProme ? num + "\n" : "");
        }

    }

    public static void guessNimber() { //23. Угадать число
        Random random = new Random();
        int x = random.nextInt(100) + 1;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Угадайте число");
        int n = 0;
        while (x != n) {
            n = scanner.nextInt();
            if (n > x) {
                System.out.println("Меньше");
            } else {
                System.out.println("Больше");
            }
        }
        System.out.println("Угадал это " + x);
    }

    public static void minMaxFinder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 5 числе");
        int n;
        int max = -2147483648;
        int min = 2147483647;
        for (int i = 0; i < 5; i++) {
            n = scanner.nextInt();
            if (n > max) {
                max = n;
            }
            if (n < min) {
                min = n;
            }
        }
        System.out.println(max + "-max \n" + min + "-min");
    }

    public static void reverseString() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку");
        String str = scanner.nextLine();
        String revers = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            revers += str.charAt(i);
        }
        System.out.println(revers);
        scanner.close();

    }

    public static void checkInputIsNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1 символ");
        String str = scanner.nextLine();
        if (str.length() == 1) {
            char cr = str.charAt(0);
            if (cr >= '0' && cr <= '9') {
                System.out.println("Это число");
            } else {
                System.out.println("Это не число");
            }
        } else {
            throw new IllegalArgumentException("Введено много букв");
        }
    }

    ;

    public static void findSecondMaxNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите Число");
        long number = scanner.nextLong();
        number = Math.abs(number);
        int max = -1, preMax = -1;
        while (number > 0) {
            int digit = (int) (number % 10);
            number = number / 10;
            if (digit > max) {
                preMax = max;
                max = digit;
            } else if (digit > preMax && digit < max) {
                preMax = digit;
            }

        }
        System.out.println((preMax == -1) ? "Нет второго по величине числа" : "Второе по величине " + preMax);
    }

    public static void splitWords() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] srtArr = str.trim().split(" +");
        for (int i = 0; i < srtArr.length; i++) {
            System.out.println(srtArr[i]);
        }
    }

    public static void palindrome() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число");
        long n = Math.abs(scanner.nextLong());
        long nPalindrome = 0;
        long original = n;
        if (n % 10 == 0) {
            System.out.println("Не палиндром");
        } else {

            while (n > 0) {
                nPalindrome = nPalindrome * 10 + (n % 10);
                n = n / 10;
            }

        }
        System.out.println((original == nPalindrome) ? "Палиндром" : "не палиндром");
    }

    public static void rectangleDrawer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите символ");
        char ch = scanner.next().charAt(0);
        System.out.println("Введите Высоту");
        int length = scanner.nextInt();
        System.out.println("Введите Ширину");
        int width1 = scanner.nextInt();

        for (int i = 0; i < length; i++) {
            int width2 = width1;

            for (int x = 0; x < width2; x++) {
                System.out.print(ch);
                if (x == width2 - 1) {
                    System.out.println();
                }
            }
        }
    }

    public static void isRightTriangle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите сторону A");
        int a = scanner.nextInt();
        System.out.println("Введите сторону B");
        int b = scanner.nextInt();
        System.out.println("Введите сторону C");
        int c = scanner.nextInt();

        int max = Math.max(a, Math.max(b, c));
        long calculationSide = (long) (a * a) + (long) (b * b) + (long) (c * c) - (long) (max * max);
        System.out.println((calculationSide == (long) (max * max)) ? "Треугольник прямоугольный" : "Треугольник не прямоугольный");
    }

    public static void oneVowel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите слово");
        String str = scanner.nextLine();
        if (!str.trim().isEmpty()) {
            if (str.matches(".*[аеёуоэяиюыАЕЁУОЭЯИЮЫ].*")) {
                System.out.println("Гласная есть");
            } else {
                System.out.println("Глассной нет ");
            }
            ;
        } else {
            System.out.println("Строка пуста");
        }
    }

    public static void cyclicShiftLeft() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите число");
        int number = scanner.nextInt();
        int digit = 0;
        int temp = number;
        while (temp > 0) { //123 = 231
            digit++;
            temp = temp / 10;
        }
        int getFirst = (int) (number / Math.pow(10, digit - 1));
        int rest = number % (int) (Math.pow(10, digit - 1));
        System.out.println((rest * 10 + getFirst) + " Сдвиг влево на 1");
    }
    public static void dellSpace() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите строку");
        String str = scanner.nextLine();
        String result = str.replaceAll(" ","");
        System.out.println(result);

    }


    public static void task70() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число:");
        long n = Math.abs(scanner.nextLong());

        if (n == 0) {
            System.out.println("Все цифры в числе уникальны.");
            return;
        }

        boolean[] seen = new boolean[10];
        while (n > 0) {
            int digit = (int)(n % 10);
            if (seen[digit]) {
                System.out.println("Число содержит повторяющиеся цифры.");
                return;
            }
            seen[digit] = true;
            n /= 10;
        }
        System.out.println("Все цифры в числе уникальны.");
    }



    static void main() {
        //  CalculatedMethod();
        //  FactorialMethod();
        //FindFirstDivisor();
        //primeNumberCheck();
        // findSumNumberDigits();
        // maxSeriesNumber();
        //numberCounterPositiveNegative();
        //ReversNumber();
        //simpleNumber100();
        //guessNimber();
        //minMaxFinder();
        //reverseString();
        //checkInputIsNumber();
        //findSecondMaxNumber();
        //splitWords();
        //palindrome();
       // rectangleDrawer();
        // isRightTriangle();
        //oneVowel();
        //cyclicShiftLeft();
        //dellSpace();
        task70();
    }
}
