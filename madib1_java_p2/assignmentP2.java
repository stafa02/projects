
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/*
 * Client for traveling salesman problem.
 * It parses input file and creates a n*n cost matrix, n is number of cities
 * It also records time taken to run the branch and bound algorithm.
 */
public class assignmentP2 {

	/*
	 * args[0] = path to input file.
	 * args[1] = path to output file.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new RuntimeException("incorrect arguments, expecting only input file path and output file path.");
		}
		int size;
		int[][] costMatrix;
		File inputFile = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		int problemCount = 1;
		// iteratively solve each problem
		while ((line = br.readLine()) != null) {

			size = Integer.parseInt(line);
			System.out.println("problem size " + size + " started");

			// n*n matrix
			costMatrix = new int[size][size];
			// parse file and populate cost matrix
			for (int i = 0; i < size * size; i++) {
				line = br.readLine();
				String[] values = line.split("\\W+");
				int m = Integer.parseInt(values[0]);
				int n = Integer.parseInt(values[1]);
				int data = Integer.parseInt(values[2]);
				costMatrix[m][n] = data;
			}

			Tsp tsp = new Tsp(size, costMatrix);
			long StartTime = System.currentTimeMillis();

			tsp.travel(0);
			long endTime = System.currentTimeMillis();

			// record time taken to generate all permutations
			long runTime = endTime - StartTime;

			// write to output file
			tsp.printResults(runTime, problemCount++, args[1]);
			System.out.println("problem size " + size + " completed, check " + args[1]);
		}
		br.close();
	}
}
