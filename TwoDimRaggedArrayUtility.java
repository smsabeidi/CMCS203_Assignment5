import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This utility class provides methods for working with two-dimensional ragged arrays.
 * 
 * @author Muhammed Salim
 * @version 7/13/2022
 */
public class TwoDimRaggedArrayUtility {
	
	/**
	 * Reads from a file and returns a ragged array of doubles
	 * The maximum rows is 10 and the maximum columns for each row is 10
	 * Each row in the file is separated by a new line
	 * Each element in the row is separated by a space
	 * 
	 * @param file the file to read from
	 * @return a two dimensional ragged (depending on data) array of doubles if the file is not empty, returns a null if file is empty
	 * @throws FileNotFoundException
	 */
	public static double[][] readFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		
		// First, count the number of rows
		int rowCount = 0;
		while (scanner.hasNextLine()) {
			scanner.nextLine();
			rowCount++;
		}
		scanner.close();
		
		// If file is empty, return null
		if (rowCount == 0) {
			return null;
		}
		
		// Reopen scanner to read the file again
		scanner = new Scanner(file);
		double[][] array = new double[rowCount][];
		
		int row = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			if (line.isEmpty()) {
				array[row] = new double[0];
				row++;
				continue;
			}
			
			String[] tokens = line.split("\\s+");
			array[row] = new double[tokens.length];
			
			for (int col = 0; col < tokens.length; col++) {
				array[row][col] = Double.parseDouble(tokens[col]);
			}
			row++;
		}
		
		scanner.close();
		return array;
	}
	
	/**
	 * Writes the ragged array of doubles into the file.
	 * Each row is on a separate line within the file
	 * Each double is separated by a space.
	 * 
	 * @param data the two dimensional array
	 * @param outputFile the file to write to
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(outputFile);
		
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (col > 0) {
					writer.print(" ");
				}
				writer.print(data[row][col]);
			}
			writer.println();
		}
		
		writer.close();
	}
	
	/**
	 * Returns the total of all the elements of the array.
	 * 
	 * @param data the two dimensional array
	 * @return the sum of all elements in the array
	 */
	public static double getTotal(double[][] data) {
		double total = 0.0;
		
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				total += data[row][col];
			}
		}
		
		return total;
	}
	
	/**
	 * Returns the average of the elements in the two dimensional array
	 * 
	 * @param data the two dimensional array
	 * @return the average of the elements in the array
	 */
	public static double getAverage(double[][] data) {
		double total = getTotal(data);
		int count = 0;
		
		for (int row = 0; row < data.length; row++) {
			count += data[row].length;
		}
		
		if (count == 0) {
			return 0.0;
		}
		
		return total / count;
	}
	
	/**
	 * Returns the total of the selected row in the two dimensional array
	 * index 0 refers to the first row.
	 * 
	 * @param data the two dimensional array
	 * @param row the row index to take the total of (index 0 refers to the first row)
	 * @return the total of the row
	 */
	public static double getRowTotal(double[][] data, int row) {
		if (row < 0 || row >= data.length) {
			return 0.0;
		}
		
		double total = 0.0;
		for (int col = 0; col < data[row].length; col++) {
			total += data[row][col];
		}
		
		return total;
	}
	
	/**
	 * Returns the total of the selected column in the two dimensional array
	 * index 0 refers to the first column.
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data the two dimensional array
	 * @param col the column index to take the total of (index 0 refers to the first column)
	 * @return the total of the column
	 */
	public static double getColumnTotal(double[][] data, int col) {
		double total = 0.0;
		
		for (int row = 0; row < data.length; row++) {
			if (col < data[row].length) {
				total += data[row][col];
			}
		}
		
		return total;
	}
	
	/**
	 * Returns the largest element of the selected row in the two dimensional array
	 * index 0 refers to the first row.
	 * 
	 * @param data the two dimensional array
	 * @param row the row index to find the largest element of (index 0 refers to the first row)
	 * @return the largest element of the row
	 */
	public static double getHighestInRow(double[][] data, int row) {
		if (row < 0 || row >= data.length || data[row].length == 0) {
			return 0.0;
		}
		
		double highest = data[row][0];
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] > highest) {
				highest = data[row][col];
			}
		}
		
		return highest;
	}
	
	/**
	 * Returns the largest element of the selected row in the two dimensional array
	 * index 0 refers to the first row.
	 * 
	 * @param data the two dimensional array
	 * @param row the row index to find the largest element of (index 0 refers to the first row)
	 * @return the index of the largest element of the row
	 */
	public static int getHighestInRowIndex(double[][] data, int row) {
		if (row < 0 || row >= data.length || data[row].length == 0) {
			return -1;
		}
		
		int highestIndex = 0;
		double highest = data[row][0];
		
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] > highest) {
				highest = data[row][col];
				highestIndex = col;
			}
		}
		
		return highestIndex;
	}
	
	/**
	 * Returns the smallest element of the selected row in the two dimensional array
	 * index 0 refers to the first row.
	 * 
	 * @param data the two dimensional array
	 * @param row the row index to find the smallest element of (index 0 refers to the first row)
	 * @return the smallest element of the row
	 */
	public static double getLowestInRow(double[][] data, int row) {
		if (row < 0 || row >= data.length || data[row].length == 0) {
			return 0.0;
		}
		
		double lowest = data[row][0];
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] < lowest) {
				lowest = data[row][col];
			}
		}
		
		return lowest;
	}
	
	/**
	 * Returns the index of the smallest element of the selected row in the two dimensional array
	 * index 0 refers to the first row.
	 * 
	 * @param data the two dimensional array
	 * @param row the row index to find the smallest element of (index 0 refers to the first row)
	 * @return the index of the smallest element of the row
	 */
	public static int getLowestInRowIndex(double[][] data, int row) {
		if (row < 0 || row >= data.length || data[row].length == 0) {
			return -1;
		}
		
		int lowestIndex = 0;
		double lowest = data[row][0];
		
		for (int col = 1; col < data[row].length; col++) {
			if (data[row][col] < lowest) {
				lowest = data[row][col];
				lowestIndex = col;
			}
		}
		
		return lowestIndex;
	}
	
	/**
	 * Returns the largest element in the two dimensional array
	 * 
	 * @param data the two dimensional array
	 * @return the largest element in the array
	 */
	public static double getHighestInArray(double[][] data) {
		if (data == null || data.length == 0) {
			return 0.0;
		}
		
		double highest = Double.NEGATIVE_INFINITY;
		boolean found = false;
		
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (!found || data[row][col] > highest) {
					highest = data[row][col];
					found = true;
				}
			}
		}
		
		return found ? highest : 0.0;
	}
	
	/**
	 * Returns the smallest element in the two dimensional array
	 * 
	 * @param data the two dimensional array
	 * @return the smallest element in the array
	 */
	public static double getLowestInArray(double[][] data) {
		if (data == null || data.length == 0) {
			return 0.0;
		}
		
		double lowest = Double.POSITIVE_INFINITY;
		boolean found = false;
		
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[row].length; col++) {
				if (!found || data[row][col] < lowest) {
					lowest = data[row][col];
					found = true;
				}
			}
		}
		
		return found ? lowest : 0.0;
	}
	
	/**
	 * Returns the largest element of the selected column in the two dimensional array
	 * index 0 refers to the first column.
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data the two dimensional array
	 * @param col the column index to find the largest element of (index 0 refers to the first column)
	 * @return the largest element of the column
	 */
	public static double getHighestInColumn(double[][] data, int col) {
		if (data == null || data.length == 0) {
			return 0.0;
		}
		
		double highest = Double.NEGATIVE_INFINITY;
		boolean found = false;
		
		for (int row = 0; row < data.length; row++) {
			if (col < data[row].length) {
				if (!found || data[row][col] > highest) {
					highest = data[row][col];
					found = true;
				}
			}
		}
		
		return found ? highest : 0.0;
	}
	
	/**
	 * Returns index of the largest element of the selected column in the two dimensional array
	 * index 0 refers to the first column.
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data the two dimensional array
	 * @param col the column index to find the largest element of (index 0 refers to the first column)
	 * @return the index of the largest element of the column
	 */
	public static int getHighestInColumnIndex(double[][] data, int col) {
		if (data == null || data.length == 0) {
			return -1;
		}
		
		int highestIndex = -1;
		double highest = Double.NEGATIVE_INFINITY;
		
		for (int row = 0; row < data.length; row++) {
			if (col < data[row].length) {
				if (highestIndex == -1 || data[row][col] > highest) {
					highest = data[row][col];
					highestIndex = row;
				}
			}
		}
		
		return highestIndex;
	}
	
	/**
	 * Returns the smallest element of the selected column in the two dimensional array
	 * index 0 refers to the first column.
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data the two dimensional array
	 * @param col the column index to find the smallest element of (index 0 refers to the first column)
	 * @return the smallest element of the column
	 */
	public static double getLowestInColumn(double[][] data, int col) {
		if (data == null || data.length == 0) {
			return 0.0;
		}
		
		double lowest = Double.POSITIVE_INFINITY;
		boolean found = false;
		
		for (int row = 0; row < data.length; row++) {
			if (col < data[row].length) {
				if (!found || data[row][col] < lowest) {
					lowest = data[row][col];
					found = true;
				}
			}
		}
		
		return found ? lowest : 0.0;
	}
	
	/**
	 * Returns the index of the smallest element of the selected column in the two dimensional array
	 * index 0 refers to the first column.
	 * If a row in the two dimensional array doesn't have this column index, it is not an error, it doesn't participate in this method.
	 * 
	 * @param data the two dimensional array
	 * @param col the column index to find the smallest element of (index 0 refers to the first column)
	 * @return the index of the smallest element of the column
	 */
	public static int getLowestInColumnIndex(double[][] data, int col) {
		if (data == null || data.length == 0) {
			return -1;
		}
		
		int lowestIndex = -1;
		double lowest = Double.POSITIVE_INFINITY;
		
		for (int row = 0; row < data.length; row++) {
			if (col < data[row].length) {
				if (lowestIndex == -1 || data[row][col] < lowest) {
					lowest = data[row][col];
					lowestIndex = row;
				}
			}
		}
		
		return lowestIndex;
	}
}

