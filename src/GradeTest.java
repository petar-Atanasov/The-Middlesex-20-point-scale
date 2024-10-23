import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GradeTest {

// -------> TWO tests for inputs below and above the valid range for the constructor. <----	
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
	
// -------> TWO tests for inputs below and above the valid range for fromPercentage <-------
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

// ------->ONE test for a valid input, checking that getPoints returns the right value.	<-------

	@Test
	void testGetPoints() {
		int input = 10; // choosing a valid input between 0-20
		Grade grade = new Grade(input);
		assertEquals(input, grade.getPoints(), "getPoints() must return the correct value of input");
	}

// -------> FIVE tests for classify, using Classifications as equivalence classes <-------	
	@ParameterizedTest
	@MethodSource("testClassifyAsEQC")
	void testClassify(int input, Classification cls) {
		Grade grade = new Grade(input);
		assertEquals(cls, grade.classify(), "The Classification should match expected");
	}
	
	private static Stream<Arguments> testClassifyAsEQC(){
		return Stream.of(
				Arguments.of(2, Classification.First),
				Arguments.of(6, Classification.UpperSecond),
				Arguments.of(10, Classification.LowerSecond),
				Arguments.of(14, Classification.Third),
				Arguments.of(17, Classification.Fail),
				// additional tests added 
				Arguments.of(18, Classification.Fail),
				Arguments.of(19, Classification.Fail),
				Arguments.of(20, Classification.Fail)
				);
	}
	
// -------> TWENTY tests for fromPercentage, using each point in the 20-point scale as an equivalence class. <-------	
	@ParameterizedTest
	@MethodSource("testFromPrecentageAsEQC")
	void testFromPrecentage(int percentage, int expectedGrade) {
		Grade grade = Grade.fromPercentage(percentage);
		assertEquals(expectedGrade, grade.getPoints(), "The grade should correctly convert from percentage to grade point");
	}
	
	private static Stream<Arguments> testFromPrecentageAsEQC(){
		return Stream.of(
				Arguments.of(85, 1),
				Arguments.of(79, 2),
				Arguments.of(74, 3),
				Arguments.of(72, 4),
				Arguments.of(68, 5),
				Arguments.of(66, 6),
				Arguments.of(63, 7),
				Arguments.of(60, 8),
				Arguments.of(58, 9),
				Arguments.of(55, 10),
				Arguments.of(53, 11),
				Arguments.of(50, 12),
				Arguments.of(48, 13),
				Arguments.of(45, 14),
				Arguments.of(43, 15),
				Arguments.of(40, 16),
				Arguments.of(36, 17),
				Arguments.of(31, 18),
				Arguments.of(15, 19),
				Arguments.of(-1, 20)
				);
	}
}
