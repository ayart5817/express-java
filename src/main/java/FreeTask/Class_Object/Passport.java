package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Passport implements Comparable<Passport> {
    Integer series;
    Integer number;

    @Override
    public String toString() {
        return "Passport" +
                "series = " + series +
                ", number = " + number
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return Objects.equals(series, passport.series) && Objects.equals(number, passport.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(series, number);
    }

    public Passport(Integer series, Integer number) {
        this.series = series;
        this.number = number;
    }

    @Override
    public int compareTo(@NotNull Passport o) {
        if (o == null) {
            return 1;
        }
        int cmp = this.series.compareTo(o.series);
        if (cmp == 0) {
            cmp = this.number.compareTo(o.number);
        }
        return cmp;
    }

    static void main(String[] args) {
        Set<Passport> setPassport = new  TreeSet<>();
        setPassport.add(new Passport(123,45678));
        setPassport.add(new Passport(122,45678));
        setPassport.add(new Passport(122,45677));
        setPassport.forEach(System.out::println);
    }
}
