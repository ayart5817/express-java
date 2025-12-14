package FreeTask.JenericAndException;

import java.util.Optional;

public class ParserInt10 {
   static public Optional<Integer> parseInt(String str) {
        if (str == null || str.trim().isEmpty()) {
            return Optional.empty();
        }
        try {


            String clear = str;
            return Optional.of(Integer.parseInt(clear));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

    }

    static void main(String[] args) {
        System.out.println(parseInt("-09876"));
        System.out.println(parseInt("+,l;fm09876"));
    }

}
