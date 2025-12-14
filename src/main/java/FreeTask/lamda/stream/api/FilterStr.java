package FreeTask.lamda.stream.api;

import ComplexTask2.Task5.Product;
import FreeTask.Class_Object.User;
import org.jetbrains.annotations.NotNull;

import javax.xml.transform.Source;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FilterStr {
    static List<String> l1 = new ArrayList<>(List.of("ывeаыа", "ываыadadadваж", "плдьып", "adaflepaf", "asfasf", "s", "Aaf", "A", "adf", "e"));
    static List<String> l3 = new ArrayList<>(List.of("ываыа", "ываываж", "плдьып", "adaflpaf", "asfasf", "s", "Aaf", "A", "adf"));
    static List<Integer> l2 = new ArrayList<>(List.of(1, -2, 2, 2, 1 - 3, 6, -5, 8, 0, 4, 7, 8, 96, 555));
    static List<Integer> l4 = new ArrayList<>(List.of(1, 0, 4, 7, 8, 96, 555));
    static User user1 = new User("олег", 123, "adauyhd@gmail.com");
    static User user2 = new User("олег2", 124, "9009yhd@gmail.yandex");
    static User user3 = new User("олег3", 125, "a123d@gmail.yandex");
    static List<User> l5 = new ArrayList<>(List.of(user1, user2, user3));
    static List<Product> l6 = new ArrayList<>(List.of(
            new Product("Potato5", 109),
            new Product("Potato1", 101),
            new Product("Potato2", 100)
    )
    );
    static List<Integer> l7 = new ArrayList<>();


    static void filter(@NotNull List<String> list) {
        List<String> result = list.stream().filter(string -> string.length() > 5)
                .skip(1)
                .limit(2)
                .toList();
        result.forEach(System.out::println);
    }

    static void numberStringFunction(@NotNull List<Integer> list) {
        Function<Integer, String> numberToString = i -> "Число: " + i;
        for (Integer l : list) {
            System.out.println(numberToString.apply(l));
        }
    }

    static void printWithConsumer(List<Integer> list) {
        Consumer<Integer> printer = n -> System.out.println(n);
        list.forEach(printer);
    }

    static void filterByPositiveAndEven(List<Integer> list) {
        list.stream().filter(n -> n % 2 == 0).filter(n -> n > 0)
                .forEach(System.out::println);
    }

    static void sortedByLengthDesc(List<String> list) {
        list.stream().sorted(Comparator.comparingLong(s -> s.length()))
                .forEach(System.out::println);
    }

    static void countEvenNumber(List<Integer> list) {
        Integer y = list.stream().filter(n -> n % 2 == 0 && n != 0)
                .toList().toArray().length;
        System.out.println(y);
    }

    static void unitElement(List<Integer> list) {
        list.stream().distinct().sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    static void findFirstElements(List<String> list) {
        System.out.println(list.stream().filter(str -> str.matches("^A.*")).findFirst());
    }

    static void sumAllElements(List<Integer> list) {
        int result = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(result);
    }

    static void checkNumberEven(List<Integer> list) {
        boolean result =
                list.stream().allMatch(n -> n >= 0);
        System.out.println(result);
    }

    static void checkMach(List<String> list) {
        boolean result =
                list.stream().anyMatch(n -> n.length() > 10);
        System.out.println(result);
    }

    static void getEmailUser(List<User> list) {
        list.stream().map(User::getEmail).forEach(System.out::println);
    }

    static void randomSupplier() {
        Supplier<Integer> getRandom = () -> (int) (Math.random() * 10) + 1;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            result.add(getRandom.get());
        }
        result.forEach(System.out::println);
    }

    static void sortedProduct(List<Product> list) {
        List<Product> result = list.stream().sorted(Comparator.comparing(Product::getPrice)).toList();
        result.forEach(System.out::println);
    }

    static void sortedByLength(List<String> str) {
        Map<Integer, List<String>> result = str.stream().collect(Collectors.groupingBy(s -> s.length()));
        result.entrySet().stream().sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + "–" + entry.getValue()));
    }

    static void getMaxCirculator(List<Integer> list) {
        Optional<Integer> result = list.stream().max(Integer::compareTo);
        System.out.println(result);
    }

    static <T extends String> void countElement(List<T> list) {
        long result = list.stream().filter(s -> s.contains("e")).count();
        System.out.println(result);
    }

    static void convert(List<User> user) {
        Map<Integer, String> result = user.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        result.forEach((k, v) -> System.out.println(k + "–" + v));
    }
    static void joiningString(List<String> list){
        String result = list.stream().collect(Collectors.joining(","));
        System.out.println(result);
    }
    static void countWord(List<String> list) {
        Map<String, Long> result = list.stream().collect(Collectors.groupingBy(s->s, Collectors.counting()));
        System.out.println(result);
    }
    static void usedLimit(List<Integer> list) {
      List<Integer> result = list.stream().limit(10).skip(1).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(result);
      result.removeIf(n->n<0);
        System.out.println(result);
    }


    static void main(String[] args) {
        //filter(l1);
        //numberStringFunction(l2);
        // printWithConsumer(l2);
        //filterByPositiveAndEven(l2);
        //sortedByLengthDesc(l1);
        //countEvenNumber(l2);
        //unitElement(l2);
        //findFirstElements(l1);
        // sumAllElements(l2);
        //checkNumberEven(l4);
        //checkNumberEven(l2);
        //  checkMach(l1);
        // getEmailUser(l5);
        // randomSupplier();
        //sortedProduct(l6);
        //sortedByLength(l1);
        // getMaxCirculator(l2);
        //getMaxCirculator(l7);
        //countElement(l1);
        // convert(l5);
        //joiningString(l1);
        usedLimit(l2);
    }
}
