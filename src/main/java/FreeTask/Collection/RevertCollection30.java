package FreeTask.Collection;

import java.util.LinkedList;
import java.util.List;

public class RevertCollection30 {

    static  List<String> myList = new LinkedList<>();

    public static void addElement(String str) {
        myList.add(str);
    }

    public static void revertCollectionMethod() {

        System.out.println(myList.reversed());
    }

    static void main(String[] args) {
        RevertCollection30.addElement("a");
        RevertCollection30.addElement("b");
        RevertCollection30.addElement("c");
        System.out.println(myList);
        RevertCollection30.revertCollectionMethod();
    }
}
