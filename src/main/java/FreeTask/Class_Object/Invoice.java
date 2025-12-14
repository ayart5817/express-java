package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.*;

public class Invoice implements Comparable<Invoice> {
    String invoiceId;
    LocalDate date;
    int amount;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId);
    }

    public Invoice(String invoiceId, LocalDate date, int amount) {
        this.invoiceId = invoiceId;
        this.date = date;
        this.amount = amount;
    }

    public Invoice(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public int compareTo(@NotNull Invoice o) {
        int result = this.date.compareTo(o.date);
        if (result == 0) {
            result = this.invoiceId.compareTo(o.invoiceId);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId='" + invoiceId + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                '}';
    }

    static void main(String[] args) {

        Invoice i3 = new Invoice("16", LocalDate.now(), 10);
        Invoice i2 = new Invoice("14", LocalDate.now(), 9);
        Invoice i1 = new Invoice("15", LocalDate.now(), 8);
        Set<Invoice> map = new TreeSet<>();

        map.add(i1);
        map.add(i2);
        map.add(i3);

        map.stream().forEach(System.out::println);
    }
}
