package FreeTask.Class_Object;

import java.util.Objects;

public class Ticket {
    String event;
    String row;
    Integer seatNumber;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(event, ticket.event) && Objects.equals(row, ticket.row) && Objects.equals(seatNumber, ticket.seatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(event, row, seatNumber);
    }

    public Ticket(String event, String row, Integer seatNumber) {
        this.event = event;
        this.row = row;
        this.seatNumber = seatNumber;
    }

    static void main(String[] args) {
        Ticket ticket1 = new Ticket("123","123", 10);
        Ticket ticket2 = new Ticket("123","123", 10);
        System.out.println(ticket2.equals(ticket1));
    }

}
