package Task2_Global_validator_test;


import ComplexTask2.Task2.GloballyValidatorEnabled;
import ComplexTask2.Task2.InvalidUserException;
import ComplexTask2.Task2.User;
import ComplexTask2.Task2.UserValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Проверка имени: Имя должно быть не пустым и начинаться с заглавной буквы.
 * Проверка возраста: Возраст должен быть в пределах от 18 до 100 лет.
 * Проверка email: Email должен соответствовать стандартному формату электронной почты.
 * Управление валидацией: Валидация данных должна происходить только если флаг validationEnabled установлен в true.
 * Исключения: При обнаружении невалидных данных необходимо выбрасывать InvalidUserException.
 *
 *
 *
 *
 */

public class GlobalValidatorTest  {

    @AfterEach
    void resetValidFlag() {
        GloballyValidatorEnabled.ValidatorFlag = true;
    }

     User testUser = new User("Марат",  "Karamba@gmail.com", 18);



    @Test
    //проверка невалидного имени
    public void validateInvalidUserNameTest() throws InvalidUserException {
        assertDoesNotThrow(() -> UserValidator.validate(testUser));
        //Ошибка валидации в имени пустота
        User testUser = new User("артем",  "Karamba@gmail.com", 18);
        assertThrows(InvalidUserException.class,() ->  UserValidator.validate(testUser));
    }



     @Test
     //проверка Пустого имени
    public void validateUserTest() throws InvalidUserException {
         assertDoesNotThrow(() -> UserValidator.validate(testUser));
         //Ошибка валидации в имени пустота
         User testUser = new User("",  "Karamba@gmail.com", 18);
         assertThrows(InvalidUserException.class,() ->  UserValidator.validate(testUser));
     }

     @Test
     //управление валидацией: Валидация данных должна происходить только если флаг validationEnabled установлен в true.
    public void validFlagOff() {
         GloballyValidatorEnabled.ValidatorFlag = false;
         User testUser = new User("марат",  "Karambagmail.com", 17);
         assertDoesNotThrow(() -> testUser);
     }

     @Test
     // Проверка валидного Имени email возраста
     public void validEmailUser() throws InvalidUserException {

         User testUser = new User("Mарат", "Karamb@agmail.com", 18);
         assertDoesNotThrow(() -> UserValidator.validate(testUser));
     }

     @Test
     public void inValidEmailUser() throws InvalidUserException {

        //Karambagmail.com
        User testUser = new User("Mарат",  "Karambagmail.com", 18);
        assertThrows(InvalidUserException.class, () -> UserValidator.validate(testUser));
        //Karamb@agmail.c"
         User testUser2 = new User("Mарат",  "Karamb@agmail.c", 18);
         assertThrows(InvalidUserException.class, () -> UserValidator.validate(testUser2));
         //@gmail.com
         User testUser3 = new User("Mарат",  "@agmail.c", 18);
         assertThrows(InvalidUserException.class, () -> UserValidator.validate(testUser3));
     }

    @Test
    // Проверка валидного Имени email возраста Верхнюю границу
    public void validEmailUserBoundaryValue() throws InvalidUserException {

        User testUser = new User("M", "K@m.co", 100);
        assertDoesNotThrow(() -> UserValidator.validate(testUser));
    }

    @Test
    // Проверка валидного Имени email возраста Верхнюю границу/ по хорошему уточнить и проверить границу
    public void validEmailUserBoundaryValueMaxLength() throws InvalidUserException {
        //Необходимо ограничить на максимальную длину строки и уточнить по требованию
        User testUser = new User("Марат Артем АндрейFUJujiekgl.weggwej@#$%^&*()_+}{123456789/////////*jk.vlskz.dvnl/zsv",
                "aa112312312rgresghgrsegesrg312312312323d@adurgansergesrgergsegergaegargargergoiarngim.co",
                50);
        assertDoesNotThrow(() -> UserValidator.validate(testUser));
    }


}
