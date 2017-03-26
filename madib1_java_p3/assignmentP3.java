import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class assignmentP3 {
	/*
	 * client for 0/1 knapsack dynamic programming.
	 * 
	 * @param args[0] = input file path.
	 * 
	 * @param args[1] = output file path.
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new RuntimeException("incorrect arguments, expecting only input file path and output file path.");
		}
		int size, weight;
		List<Integer> weights, profits;
		File inputFile = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String line;
		String values[];
		// iteratively solve each problem
		while ((line = br.readLine()) != null) {
			weights = new ArrayList<Integer>();
			profits = new ArrayList<Integer>();
			values = line.split("\\W+");
			size = Integer.parseInt(values[0]);
			weight = Integer.parseInt(values[1]);
			System.out.println("problem size " + size + " started");

			// parse file and populate weights and profits for current problem
			for (int i = 0; i < size; i++) {
				line = br.readLine();
				values = line.split("\\W+");
				int w = Integer.parseInt(values[0]);
				int p = Integer.parseInt(values[1]);
				weights.add(w);
				profits.add(p);
			}
			long start = System.currentTimeMillis();
			// solve current problem.
			KnapsackSolution ks = new KnapsackSolution(size, weight, weights, profits);
			ks.computeKnapsack();
			long end = System.currentTimeMillis();
			// write to ouptut file.
			ks.printResults((end - start)/1000, args[1]);

		}

		br.close();
	}
}
