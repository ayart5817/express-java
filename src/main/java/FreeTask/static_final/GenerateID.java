package FreeTask.static_final;

import javax.naming.OperationNotSupportedException;

public class GenerateID {
    private static int counter = 1;
    private GenerateID() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Нельзя создать объект класса");
    }

    static public int generateIdMethod() {
        return counter++;
    }
}
