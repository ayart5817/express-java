package FreeTask.JenericAndException;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Range<T extends Comparable<T>> {
    T start;
    T end;

    //constructor
    public Range(T start, T end) {
        if (start == null || end == null) {
            throw new NullPointerException("Значения не должны бть пустыми");
        }

        if (start.compareTo(end) >0 ) {
            throw new IllegalArgumentException("Значение до д.б больше от");
        }
        this.start = start;
        this.end = end;


    }
    public boolean contains(@NotNull T value) {
        return value.compareTo(start)>=0 && value.compareTo(end)<=0;
    }
    //diapason
    @Contract("_, _ -> new")
    public static  <T extends Comparable <T>> @NotNull Range<T> rangeDiapasons(T start, T end) {
        return new Range<>(start, end);
    }


    static void main(String[] args) {
        Range<Integer> r1 = new Range<>(10,100);
        System.out.println(r1.contains(1));
        Range<String> r2 = new Range<>("A", "z");
        System.out.println(r2.contains("m"));
        System.out.println(r2.contains("1"));
    }
}
