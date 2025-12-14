import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Student {
    String name;
    String group;

    public Student(String name, String group) {
        this.name = name;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) &&
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group);
    }

    @Override
    public String toString() {
        return "name=" + name + ", group=" + group ;
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("a", "a"));
        students.add(new Student("a", "a"));
        students.add(new Student("a", "b"));

        System.out.println("Уникальных студентов: " + students.size());
        students.forEach(System.out::println);
    }
}