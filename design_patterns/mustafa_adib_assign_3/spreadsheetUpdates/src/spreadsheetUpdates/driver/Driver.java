package spreadsheetUpdates.driver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import spreadsheetUpdates.observer.Cell;
import spreadsheetUpdates.util.CycleDetector;
import spreadsheetUpdates.util.FileProcessor;
import spreadsheetUpdates.util.Logger;

public class Driver {

	private static Map<String, Cell> spreadsheet = new HashMap<String, Cell>();

	/**
	 * 
	 * @param args[0] input file
	 * @param args[1] output file
	 * @param args[2] debug level
	 */
	public static void main(String[] args) {
		try {
			validateArgs(args);
			FileProcessor fp1 = new FileProcessor(new BufferedReader(new FileReader(args[0])));
			FileProcessor fp2 = new FileProcessor(args[1]);
			parseInput(fp1, fp2);

			Logger.writeMessage("The sum of all cell values is: " + calculateSum() + "\n",
					Logger.DebugLevel.PRINT_RESULTS);
			fp2.writeLineToFile("The sum of all cell values is: " + calculateSum());
			fp2.close();
			fp1.close();
		} catch (IOException e) {
			System.err.println("Error in file handling");
			e.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * 
	 * @param args
	 *            command line arguments
	 */
	private static void validateArgs(String args[]) throws IOException {
		if (args.length == 3) {

			try {
				int debugLevel = Integer.parseInt(args[2]);
				Logger.setDebugValue(debugLevel);

				if (debugLevel < 0 || debugLevel > 4) {
					System.err.println("Invalid value for debug level, expected range between 0-4");
					System.exit(1);
				}
				BufferedReader br = new BufferedReader(new FileReader(args[0]));
				if (br.readLine() == null) {
					System.err.println("Input file is empty\n");
					System.exit(1);
				}
				br.close();
			} catch (IllegalArgumentException ex) {
				System.err.println("NumberFormatException-Cannot parse to integer.");
				ex.printStackTrace();
				System.exit(1);
			}
		} else {
			System.err.println("Invalid number of arguments. Expected 3 arguments");
			System.exit(1);
		}
	}

	/**
	 * 
	 * @return sum total of all cells.
	 */
	private static int calculateSum() {
		int sum = 0;
		for (String id : spreadsheet.keySet()) {
			sum += spreadsheet.get(id).getValue();
		}
		return sum;
	}

	/**
	 * 
	 * @param s string to be checked
	 * @return boolean stating if given string is a number.
	 */
	private static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param fp1 input file handle
	 * @param fp2 output file handle
	 * @throws IOException
	 */
	private static void parseInput(FileProcessor fp1, FileProcessor fp2) throws IOException {
		String line, lhs, rhs, op1, op2;
		Map<String, List<String>> cellGraph = new HashMap<String, List<String>>();
		CycleDetector detector = new CycleDetector(cellGraph);
		while ((line = fp1.readLineFromFile()) != null) {
			Cell operand1 = null, operand2 = null;
			int v1 = 0, v2 = 0;
			lhs = line.split("=")[0];
			rhs = line.split("=")[1];
			op1 = rhs.split("\\+")[0];
			op2 = rhs.split("\\+")[1];
			if (!cellGraph.containsKey(lhs)) {
				cellGraph.put(lhs, new ArrayList<String>());
			}
			if (!isInteger(op1)) {
				operand1 = createOperand(op1, lhs, cellGraph);
			} else {
				v1 = Integer.parseInt(op1);
			}
			if (!isInteger(op2)) {
				operand2 = createOperand(op2, lhs, cellGraph);
			} else {
				v2 = Integer.parseInt(op2);
			}
			createCell(line, lhs, op1, op2, operand1, operand2, v1, v2, fp2, cellGraph, detector);
		}
	}

	/**
	 * 
	 * @param line one line of input file
	 * @param lhs of equation
	 * @param op1 first operand string
	 * @param op2 second operand string
	 * @param operand1 first operand cell
	 * @param operand2 second operand cell
	 * @param v1 first operand value
	 * @param v2 second operand value
	 * @param fp output file handle
	 * @param cellGraph adjacencyList of cells
	 * @param CycleDetector
	 * @throws IOException
	 */
	public static void createCell(String line, String lhs, String op1, String op2, Cell operand1, Cell operand2, int v1,
			int v2, FileProcessor fp, Map<String, List<String>> cellGraph, CycleDetector detector) throws IOException {
		Cell cell = null;
		if (detector.hasCycle()) {
			cellGraph.get(lhs).remove(op1);
			cellGraph.get(lhs).remove(op2);
			fp.writeLineToFile("Cycle detected, skipped line: " + line + "\n");
			Logger.writeMessage("Cycle detected, skipped line: " + line + "\n", Logger.DebugLevel.PRINT_RESULTS);
		} else {
			if (!spreadsheet.containsKey(lhs)) {
				cell = new Cell(operand1, operand2, v1, v2);
				spreadsheet.put(lhs, cell);
			} else {
				cell = spreadsheet.get(lhs);
				cell.modify(operand1, operand2, v1, v2);
			}
			if (operand1 != null) {
				operand1.registerObserver(cell);
			}
			if (operand2 != null) {
				operand2.registerObserver(cell);
			}
		}
	}

	/**
	 * 
	 * @param op operand string
	 * @param lhs of equation
	 * @param cellGraph adjacencyList of cells
	 * @return Cell operand
	 */
	public static Cell createOperand(String op, String lhs, Map<String, List<String>> cellGraph) {
		Cell operand = null;
		if (!spreadsheet.containsKey(op)) {
			operand = new Cell(null, null, 0, 0);
			spreadsheet.put(op, operand);
		} else {
			operand = spreadsheet.get(op);
		}
		cellGraph.get(lhs).add(op);
		if (!cellGraph.containsKey(op)) {
			cellGraph.put(op, new ArrayList<String>());
		}
		return operand;
	}
}
