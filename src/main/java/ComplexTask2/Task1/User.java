package ComplexTask2.Task1;

import java.util.Objects;

public abstract class User {
    private int age;
    private String name;
    private boolean isActive = true;

    public User(int age, String name, boolean isActive) {
        this.age = age;
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                isActive == user.isActive &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name, isActive);
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getAge() {
        return age;
    }
}
