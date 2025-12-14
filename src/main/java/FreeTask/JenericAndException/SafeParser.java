package FreeTask.JenericAndException;

import java.util.Optional;

public class SafeParser {

   static public Optional<Integer> parser(String str) {
        if (str == null || str.trim().isEmpty()) {
            System.out.println("Cтрока пуста ");
            return Optional.empty();
        }
        try {
            long value = Long.parseLong(str);
            if (value < Integer.MIN_VALUE || value > Integer.MAX_VALUE) {
                return Optional.empty();
            }
            return Optional.of((int) value);
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    static void main(String[] args) {
        System.out.println(parser("-1000000000"));
    }
}
