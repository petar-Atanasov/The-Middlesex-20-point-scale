import java.util.List;

public class Profile {
	// Your additions/changes below this line
	private List<Grade> grades;

	public Profile(List<Grade> g) {
		// check for a null value inside the list as well as verifying that the list is
		// not empty and raise an exception
		if (g == null || g.isEmpty()) {
			throw new IllegalArgumentException("The Profile list cannot be null or empty.");
		}
		// loop throughout the grades and checks for failed grades and raise an
		// exception if found one
		for (Grade grade : g) {
			if (grade.getPoints() == 20) { // know that 20 is Failed grade
				throw new IllegalArgumentException("The Profile cannot contain fail grades.");
			}
		}
		grades = g;
	}

	public Classification classify() {
		int countFirst, countUpperSecond, countLowerSecond, countThird;
		countFirst = countUpperSecond = countLowerSecond = countThird = 0;

		int total = grades.size() / 2;
		// loops through the list of grades and checks if the given points met the
		// requirements,
		// and increment the count of it
		for (Grade grade : grades) {
			if (grade.getPoints() <= 4) { // first class
				countFirst++;
				System.out.println(countFirst);
			}
			if (grade.getPoints() <= 8) { // upper second class
				countUpperSecond++;
				System.out.println(countUpperSecond);
			}
			if (grade.getPoints() <= 12) { // lower second class
				countLowerSecond++;
				System.out.println(countLowerSecond);
			}
			if (grade.getPoints() <= 16) { // third class
				countThird++;
				System.out.println(countThird);
			}
			
		}
//		then determines the classification based on the percentage
		if (countFirst >= total) {
			return Classification.First;
		} else if (countUpperSecond >= total) {
			return Classification.UpperSecond;
		} else if (countLowerSecond >= total) {
			return Classification.LowerSecond;
		} else if (countThird >= total) {
			return Classification.Third;
		}
		return Classification.Fail; // if no criteria is met return the Fail class
	}

	public boolean isClear() {
		int total = grades.size();
		int countThirdClass = 0;

		for (Grade grade : grades) {
			if (grade.getPoints() >= 12) {
				countThirdClass++;
			}
		}
//		 now checks if more then 25% of the grades fall into third class profiles classified as first or upper
		double thirdClassPercentage = (double) countThirdClass / total;
		Classification cls = classify();

		if (cls == Classification.First || cls == Classification.UpperSecond) {
			return thirdClassPercentage <= 0.25;
		}

		return true; // Profiles classified as Lower Second or Third are clear
	}
}
