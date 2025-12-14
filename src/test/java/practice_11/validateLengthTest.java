package practice_11;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class validateLengthTest extends  StringProcessorTest {
    /**
     * Тест на валидацию
     * позитивные сценарии
     * "abba", 3 ->
     * "abba, 4 -> IlegalExceptin"
     * Угловые кейсы
     * "abba", 0 -> "abba"
     * "", 0 -> ""
     * "hello", -1 -> IllegalException
     *
     */
    public static Stream<Arguments> stringForValidationNegativeCases() {
        return Stream.of(
                // позитивные сценарии
                Arguments.of("abba", 5),

                // угловые сценарии
                Arguments.of("Hrllo", -1));
    }
    @ParameterizedTest
    @MethodSource("stringForValidationPositiveCases")
    public void userCanValidateStringWithLengthMoreOrEqualsToMinValue(String inicialString, int minValue) {
        String expectedResult = stringProcessor.validateLength(inicialString, minValue);

        assertEquals(expectedResult, inicialString);
    }
    public static Stream<Arguments> stringForValidationPositiveCases() {
        return Stream.of(
                // позитивные сценарии
                Arguments.of("abba", 3),
                Arguments.of("abba", 4),
                // угловые сценарии
                Arguments.of("abba", 0),
                Arguments.of("", 0));
    }
   @ParameterizedTest
    @MethodSource("stringForValidationNegativeCases")
    public void userCanValidateStringWithLengthLessThenMinValue(String inicialString, int minValue) {

        assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.validateLength(inicialString, minValue);
        }, "Validation Of String with length than MinValue should lead to lead IllegalArgumentException");


    }
}
