import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import java.util.Arrays;
import java.util.List;

class ProfileTest {

// -------> THREE tests for the constructor, one for each distinct way input can be invalid. <-------	
	@Test
	// Test the constructor with a null value
	void testConstructorWithNullValue() {
		// create a Profile with null list of grades
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			new Profile(null);
		});

		assertTrue(exception.getMessage().contains("The Profile list cannot be null."));
	}

	@Test
	// Test the constructor with empty list of grades
	void testConstructorWithEmptyList() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			// passing an empty list
			new Profile(List.of());
		});
		assertEquals("The Profile list cannot be an empty list.", exception.getMessage());
	}

//	@Test
//	// Test the constructor with an invalid list of grades
//	void testConstructorWithInvalidGrades() {
//		List<Grade> invalidGrades = List.of(new Grade(21)); // known that 21 is invalid grade
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			new Profile(invalidGrades);
//		});
//
//		assertEquals("The Profile cannot contain invalid grades.", exception.getMessage());
//	}

// -------> SIX tests, one for each possible combination of Classification and truth <-------
// ------->  value (whether the profile is clear or not) as an equivalence class <-------

	@ParameterizedTest
	@MethodSource("testProfileData")
	void testClassificationAndClarityAsEQC(List<Grade> grades, Classification cls, boolean expectedIsClear) {
		Profile profile = new Profile(grades);
		assertEquals(cls, profile.classify(), "Expected classifications did not match.");
		assertEquals(expectedIsClear, profile.isClear(), "Expected clarity status did not match.");
	}

	private static Stream<Arguments> testProfileData() {
		// the arguments are: grades, classification and the expected value form isClear
		return Stream.of(
				Arguments.of(Arrays.asList(new Grade(1), new Grade(2), new Grade(3), new Grade(4)),
						Classification.First, true), //First, clear
				Arguments.of(Arrays.asList(new Grade(5), new Grade(6), new Grade(7), new Grade(8)),
						Classification.UpperSecond, true), //Upper Second, clear
				Arguments.of(Arrays.asList(new Grade(9), new Grade(10), new Grade(11), new Grade(12)),
						Classification.LowerSecond, true), // Lower Second, clear
				Arguments.of(Arrays.asList(new Grade(13), new Grade(14), new Grade(15), new Grade(16)),
						Classification.Third, true), // Third, clear
				Arguments.of(Arrays.asList(new Grade(17), new Grade(18), new Grade(19), new Grade(20)),
						Classification.Fail, true), // Fail, clear
				// additional test for checking the clarity and borderline or not clear
				Arguments.of(Arrays.asList(new Grade(1), new Grade(1), new Grade(7), new Grade(15)),
						Classification.Third, true), // clear
				Arguments.of(Arrays.asList(new Grade(1), new Grade(1), new Grade(15), new Grade(15)),
						Classification.First, false), // First, not clear 
				Arguments.of(Arrays.asList(new Grade(3), new Grade(3), new Grade(4), new Grade(4)),
						Classification.UpperSecond, true), // Upper Second, clear
				Arguments.of(Arrays.asList(new Grade(5), new Grade(5), new Grade(9), new Grade(9)),
						Classification.UpperSecond, false), //Upper Second, borderline not clear case as 50% fails into a lower class
				Arguments.of(Arrays.asList(new Grade(5), new Grade(5), new Grade(6), new Grade(6)),
						Classification.LowerSecond, true), // Lower Second, clear
				Arguments.of(Arrays.asList(new Grade(7), new Grade(7), new Grade(8), new Grade(8)),
						Classification.Third, true), // Third, clear
				Arguments.of(Arrays.asList(new Grade(9), new Grade(9), new Grade(10), new Grade(10)),
						Classification.Fail, true), // Fail, clear
				Arguments.of(Arrays.asList(new Grade(1), new Grade(1), new Grade(7), new Grade(7)),
						Classification.First, true)); //First, clear 
	}
}
