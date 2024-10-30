import java.util.List;

public class Degree {
	// Your additions/changes below this line

	private Profile year2Profile;
	private Profile year3Profile;

	/*
	 * throw an IllegalArgumentException whenever either list given is null, does
	 * not contain four grades, or contains a fail grade.
	 */
	public Degree(List<Grade> year2, List<Grade> year3) {
		if (year2 == null || year3 == null || year2.size() < 4 || year3.size() < 4) {
			throw new IllegalArgumentException("Please try again! The provided list of grades is incorrect.");
		}

		year2Profile = new Profile(year2);
		year3Profile = new Profile(year3);
	}

	public Classification classify() {
		Classification clsYear2 = year2Profile.classify();
		Classification clsYear3 = year3Profile.classify();

		boolean isClearYear2 = year2Profile.isClear();
		boolean isClearYear3 = year3Profile.isClear();
// If both profiles have the same classification then that classification is awarded.
		if (clsYear2 == clsYear3) {
			return clsYear2; // returns Level 5 only
		}

		// level 6 profile is better and that profile is clear, and no more than
		// one class above the level 5 profile
		if (isClearYear3 && classAbove(clsYear2, clsYear3)) {
			return clsYear3;
		}

		// level 5 profile is better and that profile is clear, and no more than
		// one class above the level 6 profile
		if (isClearYear2 && classAbove(clsYear2, clsYear3)) {
			return clsYear2;
		}

		// Otherwise, a procedure called discretion is applied
		return Classification.Third;
	}

	// helper function which determines if a class is above another based on ordinal
	// value
	private boolean classAbove(Classification lowerClass, Classification higherClass) {
		// this function checks if the the classification is ordinal by comparing
		return lowerClass.ordinal() > higherClass.ordinal();
	}

	@Override
	public String toString() {
		return "Degree [year2Profile=" + year2Profile + ", year3Profile=" + year3Profile + "]";
	}
	
	
}
