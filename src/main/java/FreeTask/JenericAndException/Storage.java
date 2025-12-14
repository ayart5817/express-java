package FreeTask.JenericAndException;

public interface Storage<T> {

    void add(T item);

    T get (int index);

    T remove(int index);

    int size();
}
