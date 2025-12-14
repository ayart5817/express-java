package ComplexTask2.Task4;

public class Rating<T extends Number>{
    public T value;
    public Rating(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
}
