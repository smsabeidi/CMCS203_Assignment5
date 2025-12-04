/**
 * This class calculates holiday bonuses for retail stores based on their sales performance.
 * 
 * @author Muhammed Salim
 * @version 7/13/2022
 */
public class HolidayBonus {
	
	// Bonus amounts
	private static final double HIGHEST_BONUS = 5000.0;
	private static final double LOWEST_BONUS = 1000.0;
	private static final double OTHER_BONUS = 2000.0;
	
	/**
	 * Calculates the holiday bonus for each store.
	 * The holiday bonus is calculated as follows:
	 * - The store with the highest sales in a category gets $5,000
	 * - The store with the lowest sales in a category gets $1,000
	 * - All other stores get $2,000
	 * - If a store doesn't have some categories, it doesn't get a bonus for those categories
	 * - If a category has only one store, that store gets the highest bonus of $5,000 for that category
	 * 
	 * @param data the two dimensional array of store sales
	 * @return an array of the bonus for each store
	 */
	public static double[] calculateHolidayBonus(double[][] data) {
		if (data == null || data.length == 0) {
			return new double[0];
		}
		
		double[] bonuses = new double[data.length];
		
		// Find the maximum number of columns
		int maxColumns = 0;
		for (int row = 0; row < data.length; row++) {
			if (data[row].length > maxColumns) {
				maxColumns = data[row].length;
			}
		}
		
		// Calculate bonus for each column
		for (int col = 0; col < maxColumns; col++) {
			double highest = TwoDimRaggedArrayUtility.getHighestInColumn(data, col);
			double lowest = TwoDimRaggedArrayUtility.getLowestInColumn(data, col);
			
			// If highest and lowest are the same, all stores in this column get highest bonus
			if (highest == lowest) {
				for (int row = 0; row < data.length; row++) {
					if (col < data[row].length) {
						bonuses[row] += HIGHEST_BONUS;
					}
				}
			} else {
				// Find the row indices for highest and lowest
				int highestIndex = TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, col);
				int lowestIndex = TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, col);
				
				// Assign bonuses
				for (int row = 0; row < data.length; row++) {
					if (col < data[row].length) {
						if (row == highestIndex) {
							bonuses[row] += HIGHEST_BONUS;
						} else if (row == lowestIndex) {
							bonuses[row] += LOWEST_BONUS;
						} else {
							bonuses[row] += OTHER_BONUS;
						}
					}
				}
			}
		}
		
		return bonuses;
	}
	
	/**
	 * Calculates the total holiday bonuses.
	 * 
	 * @param data the two dimensional array of store sales
	 * @return the total of all holiday bonuses
	 */
	public static double calculateTotalHolidayBonus(double[][] data) {
		double[] bonuses = calculateHolidayBonus(data);
		double total = 0.0;
		
		for (double bonus : bonuses) {
			total += bonus;
		}
		
		return total;
	}
}
