import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;

class GradeTest {

	@ParameterizedTest
	@ValueSource(ints = { 1, 20 }) // valid grades
	void testGradeConstructorValid(int grade) {
		assertDoesNotThrow(() -> new Grade(grade));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 21 }) // testing invalid grades
	void testGradeConstructorInvalid(int grade) {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Grade(grade),
				"Expected from the constructor to throw, but it didn't");
		assertTrue(exception.getMessage().contains("The grades must be between 0 and 20"));
	}

	@ParameterizedTest
	@ValueSource(ints = { -1, 0, 100 }) // valid percentage
	void testFromPercentageValid(int percentage) {
		assertDoesNotThrow(() -> Grade.fromPercentage(percentage));
	}

	@ParameterizedTest
	@ValueSource(ints = { -2, 101 }) // testing invalid values for fromPercentage
	void testFromPercentageInvalid(int percentage) {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> Grade.fromPercentage(percentage),
				"Expected from fromPercentage() to throw, but it didn't");
		assertTrue(exception.getMessage().contains("The percentage must be between 0, 100 or excatly -1"));
	}
}
