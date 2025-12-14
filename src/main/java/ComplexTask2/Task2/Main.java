package ComplexTask2.Task2;

public class Main {
    public static void main(String[] args) {
        GloballyValidatorEnabled.ValidatorFlag = true;
        User test1 = new User("Марат", "mastergmail.com", 18);

        try {
            UserValidator.validate(test1);
        } catch (InvalidUserException e) {
            throw new RuntimeException(e);
        }
    }
}
