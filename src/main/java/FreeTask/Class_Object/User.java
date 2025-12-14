package FreeTask.Class_Object;

import java.util.Objects;

public class User {
    int id;
    String email;
    String name;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email) && Objects.equals(name, user.name);
    }

    @Override
    public String toString() {
        return "User" +
                " id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\''
                ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name);
    }

    public User(String name, int id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }


    static void main(String[] args) {


        User user = new User("маяк", 1, "ЛДОТА2");
        System.out.println(user.toString());
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}