package FreeTask.Cycles;

import java.util.Scanner;

public class PasswordWithAttempts {
    private static int count = 0;

    public static void attemptPassword() {
        Scanner scanner = new Scanner(System.in);
        final String currentPassword = "password";
        while (count < 3) {

            System.out.println("Введите пароль");
            String input = scanner.nextLine();
            if (input.equals(currentPassword)) {
                System.out.println("Пароль верен");
                return;
            } else {
                count++;
                System.out.println("Пароль не верен");

            };
        }
        System.out.println("Превышено максимум попыток");
    }

    static void main() {
        PasswordWithAttempts.attemptPassword();
    }
}
