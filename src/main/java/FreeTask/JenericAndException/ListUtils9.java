package FreeTask.JenericAndException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListUtils9<T> {
    public <T> void swap(List<T> list, int i, int j) {


        if (list == null) {
            throw new NullPointerException("Список не должен бь null");
        }
        int s = list.size();
        if (s <= i || s <= j || i<0 || j<0) {
            throw new IndexOutOfBoundsException(String.format("Индексы %d и %d за границами списка %d", i,j,s));
        }
        if (i == j) {
            return;
        }

        T temp = list.get(i);
        list.set(i,list.get(j));
        list.set(j,temp);

    }

    static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(List.of(1,2,3,4,5,6));
        List<String> l2 = new LinkedList<>(List.of("apple", "baana", "kivi", "orange"));
        List<Short> l3 = new ArrayList<>();
        ListUtils9<Integer> listUtil = new ListUtils9();
        listUtil.swap(l1,0,5);
        l1.forEach(System.out::println);
        listUtil.swap(l2,2,1);
        l2.forEach(System.out::println);
       //listUtil.swap(l3,2,1);
        listUtil.swap(l2,2,-1);
    }
}
