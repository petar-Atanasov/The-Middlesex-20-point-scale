import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;

class DegreeTest {

// -------> THREE tests for the constructor, one for each distinct way input can be invalid. <-------
	@ParameterizedTest
	@MethodSource("testNullGrades")
	void testConstructorWithNullGrades(List<Grade> gradesForYearTwo, List<Grade> gradesForYearThree) {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> new Degree(gradesForYearTwo, gradesForYearThree));
		assertEquals("Please try again! The provided list of grades is incorrect.", thrown.getMessage());

	}

	private static Stream<Arguments> testNullGrades() {
//		creates a list of valid grades which are used to check the null values
		List<Grade> grades = Arrays.asList(new Grade(1), new Grade(2), new Grade(3), new Grade(4));
		return Stream.of(Arguments.of(null, grades), // checks the null value for year two
				Arguments.of(grades, null) // checks the null value for year three
		);
	}

	@Test
	void testConstructorWithInvalidNumberOfGrades() {
		// creates a list with less number of four grades
		List<Grade> gradesYear2 = Arrays.asList(new Grade(3), new Grade(4));
		List<Grade> gradesYear3 = Arrays.asList(new Grade(1), new Grade(5), new Grade(2));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
		// this applies for both years - two and three
		new Degree(gradesYear2, gradesYear3));
		assertEquals("Please try again! The provided list of grades is incorrect.", exception.getMessage());
	}

	@Test
	void testConstructorWithFailedGrades() {
		// creates a grades with a failed values
		List<Grade> failedGrades = Arrays.asList(new Grade(1), new Grade(17), new Grade(3), new Grade(20));
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> new Degree(failedGrades, failedGrades));
		assertEquals("Please try again! The provided list of grades is incorrect.", exception.getMessage());
	}

// -------> FIVE tests, using Classifications as equivalence classes. <-------	
	@ParameterizedTest
	@MethodSource("testClassificationEQC")
	void testClassificationAsEQC(List<Grade> gradesForYearTwo, List<Grade> gradesForYearThree, Classification cls) {
		Degree degree = new Degree(gradesForYearTwo, gradesForYearThree);
		assertEquals(cls, degree.classify());
	}

	private static Stream<Arguments> testClassificationEQC() {
		// the arguments are: year two, third year, classification
		return Stream.of(
				Arguments.of(Arrays.asList(new Grade(1), new Grade(2), new Grade(3), new Grade(4)),
						Arrays.asList(new Grade(1), new Grade(2), new Grade(3), new Grade(4)), Classification.First),
				Arguments.of(Arrays.asList(new Grade(5), new Grade(6), new Grade(7), new Grade(8)),
						Arrays.asList(new Grade(5), new Grade(6), new Grade(6), new Grade(8)),
						Classification.UpperSecond),
				Arguments.of(Arrays.asList(new Grade(9), new Grade(10), new Grade(11), new Grade(12)),
						Arrays.asList(new Grade(9), new Grade(10), new Grade(11), new Grade(12)),
						Classification.LowerSecond),
				Arguments.of(Arrays.asList(new Grade(13), new Grade(14), new Grade(15), new Grade(16)),
						Arrays.asList(new Grade(13), new Grade(14), new Grade(15), new Grade(16)),
						Classification.Third),
				Arguments.of(Arrays.asList(new Grade(13), new Grade(13), new Grade(16), new Grade(16)),
						Arrays.asList(new Grade(16), new Grade(16), new Grade(16), new Grade(16)),
						Classification.Third));
	}

	@Test
	void testClassAbove() {
		List<Grade> higherGrades = Arrays.asList(new Grade(1), new Grade(1), new Grade(1), new Grade(1)); // First,clear
		List<Grade> lowerGrades = Arrays.asList(new Grade(5), new Grade(5), new Grade(6), new Grade(6)); // Upper Second,clear

		Degree degree = new Degree(higherGrades, lowerGrades);
		Degree degree2 = new Degree(lowerGrades, higherGrades);
		assertEquals(Classification.First, degree.classify(), degree.toString());
		assertEquals(Classification.First, degree2.classify(), degree2.toString());
	}

	@Test
	void TestDegreeClassificationDiscretion() {
		List<Grade> nearlyEQGrades1 = Arrays.asList(new Grade(1), new Grade(1), new Grade(3), new Grade(10)); 
		List<Grade> nearlyEQGrades2 = Arrays.asList(new Grade(9), new Grade(9), new Grade(9), new Grade(9));
		Degree degree = new Degree(nearlyEQGrades1, nearlyEQGrades2);

		assertEquals(Classification.Discretion, degree.classify());
	}
}
