package FreeTask.lamda.stream.api;

import ComplexTask2.Task2.User;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sorted25 {

    static List<User> userList = new ArrayList<>();

    static {
        userList.add(new ComplexTask2.Task2.User("Alex3", "afdn@gmail.com", 20));
        userList.add(new ComplexTask2.Task2.User("Alex3", "afdn@gmail.com", 20));
        userList.add(new ComplexTask2.Task2.User("Alex2", "afdn@gmail.com2", 22));
        userList.add(new ComplexTask2.Task2.User("David", "a@fdn@gmail.com2", 23));
    }

    static List<String> l1 = new ArrayList<>(List.of("ывeаыа", "ываыadadadваж", "плдьып", "adaflepaf", "asfasf", "s", "Aaf", "A", "adf", "e"));
    static List<String> l2 = List.of("");
    static List<String> l3 = Arrays.asList("яблоко", "банан", "апельсин");
    static List<String> l4 = new ArrayList<>(List.of("ываыа", "ываываж", "плдьып", "5698", "asfasf", "5", "Aa5f", "A", "adf"));

    static void sortFirstNameThenEdge(List<User> userList) {
        System.out.println(userList.stream().sorted(Comparator.comparing(User::getUserName).thenComparing(User::getUserAge)).toList());
    }

    static ArrayList<Integer> generateRandomList() {
        Supplier<Integer> randomInt = () -> new Random().nextInt(101);
        return Stream.generate(randomInt).limit(10).collect(Collectors.toCollection(ArrayList::new));

    }

    static void sortedByNotRegister(List<String> list) {
        System.out.println(list.stream().sorted(String.CASE_INSENSITIVE_ORDER).toList());
    }

    static void distinctMail(List<User> list) {
        List<User> uniqueUsers = new ArrayList<>(
                list.stream()
                        .collect(Collectors.toMap(
                                User::getEmail,
                                Function.identity(),
                                (a, b) -> a  // или b, если хотите оставить последний
                        ))
                        .values()
        );
        System.out.println(uniqueUsers);
    }

    static void findAVGage(List<User> list) {
        System.out.println(list.stream().mapToInt(User::getUserAge).average().orElse(0.0));
    }

    static boolean checkAnyEmptyToList(List<String> list) {
        return list.stream().anyMatch(s -> s == null || s.isBlank());
    }
    static void stringBuilder(List<String> list) {
        System.out.println(list.stream().collect(Collectors.joining(",", "[", "]")));
    }
    static void dellAllNumber(List<String> list) {
        Predicate<String> onlyString = s->s != null && s.matches("\\d+");
        list.removeIf(onlyString);
        System.out.println(list);
    }
    static void maxCalculatedMax(List<String> list) {

    }


    static void main(String[] args) {
        //sortFirstNameThenEdge(userList);
        // generateRandomList();
        //sortedByNotRegister(l1);
        //distinctMail(userList);
        //findAVGage(userList);
        //System.out.println(checkAnyEmptyToList(l2));
        //stringBuilder(l3);
        dellAllNumber(l4);
    }
}
