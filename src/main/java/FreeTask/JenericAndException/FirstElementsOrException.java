package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FirstElementsOrException<T> {
    List<T> list = new ArrayList<>();

    public T getElement() {
        if (list.isEmpty()) {
            throw new NoSuchElementException("Список пуст");
        }
        return list.getFirst();
    }
    public void addList(T o) {
        list.add(o);
    }

   public static void main(String[] args) {
        FirstElementsOrException<String> p1 = new FirstElementsOrException<>();
        FirstElementsOrException<String> p2= new FirstElementsOrException<>();
        p1.addList("123");
        System.out.println(p1.getElement());
        System.out.println(p2.getElement());
    }
}
