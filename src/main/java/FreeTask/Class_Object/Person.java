package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Person implements Comparable<Person> {
    String firstName;
    String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public int compareTo(@NotNull Person o) {
        int lastNameComparison = this.lastName.compareTo(o.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return this.firstName.compareTo(o.firstName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    static void main(String[] args) {

        Person p1 = new Person("alan","Delan");
        Person p3 = new Person("blan","Delan");
        Person p2 = new Person("alan","Belan");



    List<Person> list = new ArrayList<>();
    list.add(p1);
    list.add(p2);
    list.add(p3);
    list.forEach(person -> System.out.println(person.toString()));
    list.sort(Comparator.naturalOrder());
        System.out.println();
    list.forEach(person -> System.out.println(person.toString()));
}}
