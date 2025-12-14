package ComplexTask2.Task2;
/*
Проверка имени: Имя должно быть не пустым и начинаться с заглавной буквы.
Проверка возраста: Возраст должен быть в пределах от 18 до 100 лет.
Проверка email: Email должен соответствовать стандартному формату электронной почты.

 */

import java.util.regex.Pattern;

import static ComplexTask2.Task2.GloballyValidatorEnabled.ValidatorFlag;

public class UserValidator {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    public static void validate(User user) throws InvalidUserException {
        if (!ValidatorFlag) {
            System.out.println("Валидация отключена");
            return;
        }
        //проверка имени
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new InvalidUserException("Имя пустое");
        }
        char x =  user.getUserName().charAt(0);
        if (!Character.isUpperCase(x)) {
            throw new InvalidUserException("имя с маленькой буквы");
        }
        // возраст
        if (user.getUserAge()< 18 || user.getUserAge() > 100) {
            throw new InvalidUserException("Возраст должен быть в диапазоне 18-100 лет");
        }

        //проверка Email


        String email = user.getEmail();
        if (email == null || email.isEmpty()){
            throw new InvalidUserException("Email не может быть пустым или null ");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidUserException("Email не подходит");
        }

    }

}
