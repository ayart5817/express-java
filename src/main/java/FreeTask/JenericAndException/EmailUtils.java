package FreeTask.JenericAndException;

public class EmailUtils {

    public static boolean checkEmail(String email) throws InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Email is empty");
        }
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.trim().matches(regex)) {
            throw new InvalidEmailException("Email is not valid");
        }
        return true;
    }

    static void main(String[] args) throws InvalidEmailException {
        System.out.println( checkEmail("aaa@gg.com"));
        System.out.println( checkEmail("a"));
    }
}
