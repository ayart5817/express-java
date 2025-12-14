package FreeTask.JenericAndException;

import java.util.Optional;
import java.util.function.Supplier;

public class Optionals {

    public static <T, E extends Throwable> T getOrThrow(Optional<T> opt, Supplier<E> exception) throws E {

        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw exception.get();
        }
    }

    static void main(String[] args) {
        Optional<String> opt1 = Optional.of("Морковь");
        String result1 = getOrThrow(opt1, ()-> new RuntimeException("нет такого значения"));
        System.out.println(result1);
        Optional<Integer> opt2 = Optional.empty();
        String result2 = String.valueOf(getOrThrow(opt2, () -> new IllegalArgumentException("BAG!")));
        System.out.println(result2);
    }
}