package FreeTask.Class_Object;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class EmployeeRecord implements Comparable<EmployeeRecord> {
    private final long id;
    private final String name;
    private final double salary;

    @Override
    public String
    toString() {
        return "EmployeeRecord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeRecord that = (EmployeeRecord) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public EmployeeRecord(long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public int compareTo(@NotNull EmployeeRecord o) {
        if (o == null) return 1;
        int result = Double.compare(this.salary, o.salary);
        if (result != 0) {
            return result;
        }
        return Long.compare(this.id, o.id);
    }
}
