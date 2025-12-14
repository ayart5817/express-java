package FreeTask.Collection;

import java.util.HashSet;
import java.util.Set;

public class CheckDuplicateBasket15 {
   static Set<String> myBasket = new HashSet<>();
    public static void checkBasket(String str) {

        System.out.println( (myBasket.add(str)) ?  str +" – Добавлен": str + " – Не добавлен");


    }

    static void main(String[] args) {
        CheckDuplicateBasket15.checkBasket("банан");
        CheckDuplicateBasket15.checkBasket("банан");
        CheckDuplicateBasket15.checkBasket("яблоко");
        CheckDuplicateBasket15.checkBasket("яблоко");
    }
}
