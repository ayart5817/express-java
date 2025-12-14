package FreeTask.static_final;

public class SecureObject {
    private final String newObject;

    private SecureObject(String newObject) {
        this.newObject = newObject;
    }

    public String getNewObject() {
        return newObject;
    }

    public static SecureObject create() {
        return new SecureObject("Новый приватный объект");
    }
}
