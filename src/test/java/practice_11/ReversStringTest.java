package practice_11;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Class StringProcessor, method revers")
public class ReversStringTest extends StringProcessorTest {
    /*
    тесты для переворота строки
    happy path: sasha -> ahsas
    corner case:
        ""->""
        "a"->"a"
        bull -> IllegalArgument Exception


     */
    public static Stream<Arguments> validStringToReverse() {
        // happy path
        return Stream.of(
                Arguments.of("sasha", "ahsas"),
                Arguments.of("", ""),
                Arguments.of("a", "a"));

    }

    @Test
    public void UserCanReverseValidString() {
        StringProcessor stringProcessor = new StringProcessor();
        String intitialString = "sasha";
        String expectedString = "ahsas";

        String reverseString = stringProcessor.reverse(intitialString);

        assertEquals(expectedString, reverseString, "String reversed incorrectly " + expectedString + " , but happened " + reverseString);
    }

    @Test
    public void UserCanReverseOneLetter() {
        StringProcessor stringProcessor = new StringProcessor();
        String intitialString = "a";
        String expectedString = "a";

        String reverseString = stringProcessor.reverse(intitialString);

        assertEquals(expectedString, reverseString, "String reversed incorrectly " + expectedString + " , but happened " + reverseString);
    }

    @ParameterizedTest
    @MethodSource("validStringToReverse")
    public void UserCanReverseValidStringUpdate(String intitialString, String expectedString) {
        StringProcessor stringProcessor = new StringProcessor();


        String reverseString = stringProcessor.reverse(intitialString);

        assertEquals(expectedString, reverseString, "String reversed incorrectly " + expectedString + " , but happened " + reverseString);
    }

    @Test
    public void userCannotReversNullString() {
        StringProcessor stringProcessor = new StringProcessor();


        assertThrows(IllegalArgumentException.class, () -> {
                    stringProcessor.reverse(null);
                }, "Reversing of null string should lead to IllegalArgumentException"
        );
    }
}
