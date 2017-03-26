package recommenderSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RecommenderClient {
	
	/*
	 * Client for recommender system
	 * args[0] = input file path
	 * args[1] = total number of users.
	 * args[2] = total number of items.
	 */
	public static void main(String[] args) throws IOException {
		File file = new File(args[0]);
		BufferedReader br = new BufferedReader(new FileReader(file));

		int users = Integer.parseInt(args[1]) + 1;
		int items = Integer.parseInt(args[2]) + 1;
		String line = "";
		int[][] ratingMatrix = new int[users][items];
		
		//parse file and populate rating matrix
		while ((line = br.readLine()) != null) {
			String[] values = line.split(" ");
			ratingMatrix[Integer.parseInt(values[0])][Integer.parseInt(values[1])] = Integer.parseInt(values[2]);
		}
		br.close();

		SimilarityGenerator sg = new SimilarityGenerator(ratingMatrix, users, items);
		// compute each user's average rating.
		int[] userAvg = sg.generateUserAvg();
		// generate most similar items to each item.
		Map<Integer, List<ItemSimlarity>> similarItems = sg.generateSimilarItems();
		
		Predictor predictor = new Predictor(ratingMatrix);
		for (int i = 1; i < users; i++) {
			for (int j = 1; j < items; j++) {
				if (ratingMatrix[i][j] == 0) {
					// make prediction for missing ratings.
					ratingMatrix[i][j] = predictor.Predict(j, i, similarItems.get(j),userAvg);
				}
			}
		}

		// write to output file.
		File outputFile = new File("output.txt");
		if (outputFile.exists()) {
			outputFile.delete();
		}
		outputFile.createNewFile();
		FileWriter fw = new FileWriter(outputFile, true);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 1; i < users; i++) {
			for (int j = 1; j < items; j++) {
				bw.write(i + " " + j + " " + ratingMatrix[i][j] + "\n");
			}
		}
		bw.close();
		System.out.println("completed");
	}

}