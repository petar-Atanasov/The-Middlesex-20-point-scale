public class Grade {
	private final int points;

	public int getPoints() {
		return points;
	}

	public Grade(int p) throws IllegalArgumentException {
		if (p < 1 || p > 20)
			throw new IllegalArgumentException("The grades must be between 1 and 20.");
		points = p;
	}

	// Your additions/changes below this line

	public Classification classify() {
		if ( points <= 4 ) {
			return Classification.First;
		} else if (points <= 8) {
			return Classification.UpperSecond;
		} else if( points <= 12) {
			return Classification.LowerSecond;
		} else if( points <= 16) {
			return Classification.Third;
		}
		return Classification.Fail;
	}

	public static Grade fromPercentage(int g) throws IllegalArgumentException {

		if (g == -1) {
			return new Grade(20); // (non-participation) is returned
		} else if (g < 0 || g > 100) {
			throw new IllegalArgumentException("The percentage must be between 0, 100 or excatly -1.");
		}

		if (g >= 70) {
			return new Grade(1); // return First class
		} else if (g >= 60) {
			return new Grade(2); // return Second class
		} else if (g >= 50) {
			return new Grade(3); // return Lower Second class
		} else if (g >= 40) {
			return new Grade(4); // return Third class
		} else {
			return new Grade(5); // return Fail class
		}

	}
}
