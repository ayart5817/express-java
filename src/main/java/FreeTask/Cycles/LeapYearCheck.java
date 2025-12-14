package FreeTask.Cycles;

public class LeapYearCheck {
    public static void LeapYearCheckMethod(int year) {
        boolean isLeap = false;
        if (year % 400 == 0) {
            isLeap = true;
        }
        else if (year % 100 == 0) {
            isLeap = false;
        }
        else if (year % 4 == 0) {
            isLeap = true;
        }
        if (isLeap) {
            System.out.println(year + " Високосный");
        } else {
            System.out.println(year + " Невисокосный");
        }

        boolean isLeaps = (year % 4 == 0) && (year % 100 == 0) || (year % 400 == 0);
        System.out.println(year + (isLeaps ? " високосный" : "Не високосный"));
    }

    static void main() {
        LeapYearCheckMethod(2000);
        LeapYearCheckMethod(1000);
        LeapYearCheckMethod(2024);
        LeapYearCheckMethod(2021);
        LeapYearCheckMethod(1900);


    }
}
