import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Solves 0/1 knapsack problem using dynamic programming.
 */
public class KnapsackSolution {
	private int size, weight;
	private List<Integer> weights;
	private List<Integer> profits;
	private List<Integer> optimalSolution;
	private int knapsack[][];

	//constructor for initialization.
	public KnapsackSolution(int size, int weight, List<Integer> weights, List<Integer> profits) {
		this.size = size;
		this.weight = weight;
		this.weights = weights;
		this.profits = profits;
		knapsack = new int[size + 1][weight + 1];
		optimalSolution = new ArrayList<Integer>();
	}

	// dynamic programming algorithm to calculate maximum possible profit for given weight limit.
	public void computeKnapsack() {
		for (int i = 0; i <= size; i++) {
			for (int j = 0; j <= weight; j++) {
				if (i == 0 || j == 0) {
					knapsack[i][j] = 0;
					continue;
				}
				if (weights.get(i - 1) <= j) {
					knapsack[i][j] = Math.max(profits.get(i - 1) + knapsack[i - 1][j - weights.get(i - 1)],
							knapsack[i - 1][j]);
					continue;
				}
				knapsack[i][j] = knapsack[i - 1][j];

			}
		}
		computeOptimalSolution();
	}

	// gets optimal solution nodes based on knapsack values computed.
	private void computeOptimalSolution() {
		int i = size, j = weight;
		while (knapsack[i][j] != 0) {
			if (knapsack[i][j] > knapsack[i - 1][j]) {
				optimalSolution.add(i - 1);
				j = j - weights.get(i - 1);
			}
			i--;
		}
	}

	//writes result to output file.
	public void printResults(long millis, String outputPath) throws IOException {
		File file = new File(outputPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		System.out.println(size + " " + knapsack[size][weight] + " " + millis);
		bw.write(size + " " + knapsack[size][weight] + " " + millis + "\n");
		for (int val : optimalSolution) {
			System.out.println(weights.get(val) + " " + profits.get(val));
			bw.write(weights.get(val) + " " + profits.get(val) + "\n");
		}
		bw.close();
	}

}
