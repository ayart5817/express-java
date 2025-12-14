package FreeTask.Collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserListWithAddresses {
    static Map<String, List<String>> userAddress = new HashMap<>();

    static void addUserAddress(String user, String address) {
         userAddress.computeIfAbsent(user, a -> new ArrayList<>()).add(address);
    }
    static void getUserAddress(String user) {
        System.out.println(userAddress.get(user));
    }
    static void getAll() {
        System.out.println(userAddress.entrySet());
    }

    static void main(String[] args) {
        UserListWithAddresses.addUserAddress("Vlad", "Ulanovsk, 40 Years Linin");
        UserListWithAddresses.addUserAddress("Uan", "Ulanovsk, 30 Years Linin");
        UserListWithAddresses.addUserAddress("Max", "Moscow, 1-street 40/9");
        UserListWithAddresses.getAll();
        UserListWithAddresses.getUserAddress("Max");
    }
}
