package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ListStorage<T> implements Storage<T> {
    List<T> storageList = new ArrayList<>();

    @Override
    public void add(T item) {
        storageList.add(item);
    }

    @Override
    public T get(int index) {
        if (storageList.isEmpty()) {throw new NoSuchElementException("масив пуст");}
        return storageList.get(index);
    }

    @Override
    public T remove(int index) {
        if (storageList.isEmpty()) {
            throw new NoSuchElementException("Маcсив пуст");
        }
        return storageList.remove(index);
    }

    @Override
    public int size() {
      return   storageList.size();
    }

    static void main(String[] args) {
        ListStorage<Integer> storage = new ListStorage<>();
        storage.add(1);
        storage.add(10);
        storage.add(4);
        System.out.println(storage.get(2));
        storage.remove(0);
        storage.remove(0);
        storage.remove(0);
        System.out.println(storage.size());
        storage.get(0);
    }
}
