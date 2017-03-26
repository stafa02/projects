package tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Client for traveling salesman problem.
 * It parses input file and creates a n*n cost matrix, n is number of cities
 * It also records time taken to generate all permutations.
 */
public class TSPClient {

	/*
	 * args[0] = path to input file.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new RuntimeException("incorrect arguments, expecting only input file path.");
		}
		int size;
		int[][] costMatrix;
		File inputFile = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		while ((line = br.readLine()) != null) {
			
			size = Integer.parseInt(line);
			System.out.println("problem size "+size+" started");
			
			// n*n matrix
			costMatrix = new int[size][size];

			// parse file and populate cost matrix
			for (int i = 0; i < size * size; i++) {
				line = br.readLine();
				String[] values = line.split(" ");
				int m = Integer.parseInt(values[0]);
				int n = Integer.parseInt(values[1]);
				int data = Integer.parseInt(values[2]);
				costMatrix[m][n] = data;
			}

			TSP tsp = new TSP(size, costMatrix);
			long StartTime = System.currentTimeMillis();

			// generate all permutations
			tsp.permutation(0);
			long endTime = System.currentTimeMillis();

			// record time taken to generate all permutations
			long runTime = endTime - StartTime;

			// write to output file
			tsp.printResults(runTime);
			System.out.println("problem size "+size+" completed, check output.txt");
		}
		br.close();
	}
}