import java.util.ArrayList;
import java.util.List;

public class Profile {
	// Your additions/changes below this line
	private List<Grade> grades;

	public Profile(List<Grade> g) {
		// check for a null value inside the list as well as verifying that the list is
		// not empty and raise an exception
		if (g == null) {
			throw new IllegalArgumentException("The Profile list cannot be null or empty.");
		}
		if (g.isEmpty()){
			throw new IllegalArgumentException("The Profile list cannot be nur or empty list.");
		}
		// loop throughout the grades and checks for failed grades and raise an
		// exception if found one
		grades = new ArrayList<>(g);
		for (Grade grade : grades) {
			if (grade.getPoints() >= 17) { // know that 20 is Failed grade
				throw new IllegalArgumentException("The Profile cannot contain fail grades.");
			}
		}
		
	}

	public Classification classify() {
		
		int countFirst = 0,countUpperSecond = 0,countLowerSecond = 0;
		int total = grades.size();

		// loops through the list of grades and checks if the given points met the
		// requirements,
		// and increment the count of it
		for (Grade grade : grades) {
			if (grade.getPoints() <= 3) { // first class
				countFirst++;
			}
			if (grade.getPoints() <= 7) { // upper second class
				countUpperSecond++;
			}
			if (grade.getPoints() <= 11) { // lower second class
				countLowerSecond++;
			}
		}
//		then determines the classification based on the percentage
		if (countFirst >= total / 2) {
			return Classification.First;
		} else if (countUpperSecond >= total / 2) {
			return Classification.UpperSecond;
		} else if (countLowerSecond >= total / 2) {
			return Classification.LowerSecond;
		}
		return Classification.Third;
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
			return thirdClassPercentage <= 0.15;
		}

		return true; // Profiles classified as Lower Second or Third are clear
	}
}
