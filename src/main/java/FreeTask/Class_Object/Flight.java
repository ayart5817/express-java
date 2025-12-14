package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Flight implements Comparable<Flight>{
    String flightNumber;
    String departureTime;

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureTime='" + departureTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return Objects.equals(flightNumber, flight.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber);
    }

    public Flight(String flightNumber, String departureTime) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
    }

    static void main(String[] args) {

    }

    @Override
    public int compareTo(@NotNull Flight o) {
        if (o == null) {return 1;}
        int fn = this.flightNumber.compareTo(o.flightNumber);
        return fn;

    }


}
