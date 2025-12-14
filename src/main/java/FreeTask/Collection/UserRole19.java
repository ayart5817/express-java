package FreeTask.Collection;
import java.util.List;

import java.util.*;

public class UserRole19 {
   static Map<Object, List<String>> userRole = new HashMap<>();
    static public void addUserRole(String user, String role) {
        userRole.computeIfAbsent(user,k-> new ArrayList<>()).add(role);
        System.out.println("user:" +user + " Успешно добавлено роль " + role);
    }

    static public void getAllUsers() {
        if (userRole.isEmpty()) {
            System.out.println("Нет записей");
            return;
        }
        for (Map.Entry<Object, List<String>> entry : userRole.entrySet()) {
            System.out.println(entry.getKey() + " — " + entry.getValue());
        }

    }


    static void main(String[] args) {
        UserRole19.addUserRole("Майкл", "Админ");
        UserRole19.addUserRole("Майкл", "СуперАдмин");
        UserRole19.addUserRole("Серёжа", "Админ");
        UserRole19.addUserRole("Макс", "пользователь");
        getAllUsers();

    }
}
