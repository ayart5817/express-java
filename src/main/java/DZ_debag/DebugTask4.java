package DZ_debag;

public class DebugTask4 {
    public static void main(String[] args) {
        System.out.println(isPalindrome(null));
    }
    public static boolean isPalindrome(String str) {
        if (str != null) {
            String reversed = new StringBuilder(str).reverse().toString();
            return str.equals(reversed);
        }
        System.out.println("Значение аргумента Null");
        return false;
    }
}

