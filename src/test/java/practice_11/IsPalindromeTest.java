package practice_11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class IsPalindromeTest extends StringProcessorTest {

    /**
   Тесты для проверки, является ли тест палиндромом
     * Позитивнеы кейсы
     * - четное количество
     * - нечетное количество
     * Негативные кейсы
     * - "john" -> false
     * Corner cases:
     * - "a" -> true
     * - "" -> true
     * - null IllegalArgumentException
     *

     */
    @ParameterizedTest
    @ValueSource(strings = {
            //позитивнеы кейсы
            "abba", "hah",
            //угловые кейсы
            "a",
            ""})
    public void userCanCheckIfValidStringIsPalindrome(String initialString) {

        boolean actualResult = stringProcessor.isPalindrome(initialString);
        assertTrue(actualResult);

    }
    @Test
    public void userCanCheckStringIsNotPalindrome() {
        String initialString = "john";
        boolean actualResult = stringProcessor.isPalindrome(initialString);
        assertFalse(actualResult);
       }
    @Test
    public void userCannotReversNullStringIsPalindrome() {
        StringProcessor stringProcessor = new StringProcessor();


        assertThrows(IllegalArgumentException.class, () -> {
                    stringProcessor.reverse(null);
                }, "Reversing of null string is Palindrome should lead to IllegalArgumentException"
        );
    }
}
