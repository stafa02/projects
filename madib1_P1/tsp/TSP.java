package tsp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/*
 * Generates all permutations of n cities.
 * Records lowest and highest cost of the tour.
 */
public class TSP {
	private int size, minCost = Integer.MAX_VALUE, maxCost = Integer.MIN_VALUE;
	private int[][] costMatrix;
	private int[] cities;
	String minTour;

	//constructor for initialization
	public TSP(int size, int[][] costMatrix) {
		this.size = size;
		this.costMatrix = costMatrix;

		cities = new int[size];
		for (int i = 0; i < size; i++) {
			cities[i] = i;
		}
	}

	/*
	 * Recursively generates permutations of all cities
	 * When a permutation is found, cost of the tour is calculated
	 * Min and max costs are updated accordingly 
	 */
	public void permutation(int start) {
		//generate permutation
		if (start < size - 1) {
			for (int j = start; j < size; j++) {
				int temp = cities[j];
				cities[j] = cities[start];
				cities[start] = temp;
				permutation(start + 1);
				temp = cities[j];
				cities[j] = cities[start];
				cities[start] = temp;
			}
		}
		// permutation found
		else {
			int cost = 0;
			String tour = "";
			
			//calculate cost of generated permutation
			for (int i = 0; i < size; i++) {
				if (i != size - 1) {
					cost += costMatrix[cities[i]][cities[i + 1]];
				} else {
					cost += costMatrix[cities[i]][cities[0]];
				}
				tour += cities[i];
				tour += "-";
			}
			
			//update min or max cost if needed
			if (cost < minCost) {
				minCost = cost;
				minTour = tour;
			}
			if (cost > maxCost) {
				maxCost = cost;
			}
		}
	}

	// write result to output.txt
	public void printResults(long runTime) throws IOException {
		File file = new File("output.txt");
		if (!file.exists()) {
			file.createNewFile();
		}
		file.createNewFile();
		FileWriter fw = new FileWriter(file,true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write("1 " + size + " " + minCost + " " + maxCost + " " + runTime + "\n");
		String[] minTourElements = minTour.split("-");
		for(String element: minTourElements){
			bw.write(element+"\n");
		}
		bw.close();
	}

}
