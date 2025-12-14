package MOCTask2.Pavel;

import java.util.ArrayList;
import java.util.List;

public class Systems {
    List<User> users = new ArrayList<>();


    public void addUser(User user) {
        users.add(user);
    }

    public void getAllUserInfo() {
        users.forEach(s -> s.getInfo());
    }

}
