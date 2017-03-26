package recommenderSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Computes similarity between items using adjustedCosine similarity.
 * Stores results in map containing list of most similar items for each item.
 */
public class SimilarityGenerator {
	private int[][] ratingMatrix;
	private int users, items;
	private int[] userAvg;
	private Map<Integer, List<ItemSimlarity>> similarItems;

	public SimilarityGenerator(int[][] ratingMatrix, int users, int items) {
		this.ratingMatrix = ratingMatrix;
		userAvg = new int[users];
		similarItems = new HashMap<Integer, List<ItemSimlarity>>();
		this.users = users;
		this.items = items;
	}

	//computes each user's average rating
	public int[] generateUserAvg() {
		int sum, elements;
		for (int i = 1; i < users; i++) {
			sum = 0;
			elements = 0;
			for (int j = 1; j < items; j++) {
				if (ratingMatrix[i][j] > 0) {
					sum += ratingMatrix[i][j];
					elements++;
				}
			}
			userAvg[i] = Math.round(((float)sum) / elements);
		}
		return userAvg;
	}

	//computes similarity between all items using adjusted cosine similarity.
	public Map<Integer, List<ItemSimlarity>> generateSimilarItems() {
		double similarity, sumN, prod, sumD1, sumD2;
		for (int i = 1; i < items; i++) {
			if (similarItems.get(i) == null) {
				similarItems.put(i, new ArrayList<ItemSimlarity>());
			}
			int itemCount = 0;
			for (int j = i + 1; j < items; j++) {
				if (similarItems.get(j) == null) {
					similarItems.put(j, new ArrayList<ItemSimlarity>());
				}
				if (itemCount > users) {
					break;
				}
				sumN = sumD1 = sumD2 = similarity = 0;
				prod = 1;
				int userCount = 0;
				for (int k = 1; k < users; k++) {
					if (ratingMatrix[k][i] > 0 && ratingMatrix[k][j] > 0) {
						userCount++;
						prod = (getDiff(k, i)) * (getDiff(k, j));
						sumN += prod;
						sumD1 = sumD1 + ((getDiff(k, i)) * (getDiff(k, i)));
						sumD2 = sumD2 + ((getDiff(k, j)) * (getDiff(k, j)));
					}
				}
				if (userCount > 0) {
					itemCount++;
				}
				if (sumD1 > 0.0 && sumD2 > 0.0) {
					similarity = sumN / (Math.sqrt(sumD1) * Math.sqrt(sumD2));
				}
				ItemSimlarity item = new ItemSimlarity(j, similarity);
				addToMap(i, item);
				item = new ItemSimlarity(i, similarity);
				addToMap(j, item);
			}
		}
		return similarItems;
	}

	// calculates difference between given rating and user's average rating.
	private int getDiff(int user, int item) {
		return ratingMatrix[user][item] - userAvg[user];
	}

	//maintains a list of 200 most similar items to current item in map.
	private void addToMap(int i, ItemSimlarity item) {
		similarItems.get(i).add(item);
		if (similarItems.get(i).size() > 200) {
			Collections.sort(similarItems.get(i));
			similarItems.get(i).remove(200);
		}
	}

}