
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Tsp {
	private int size, nodesAdded;
	private long minCost;
	private int[][] costMatrix;
	private List<Integer> minTour;
	private PriorityQueue<Node> pq;

	// constructor for initialization
	public Tsp(int size, int[][] costMatrix) {
		this.size = size;
		this.costMatrix = costMatrix;
		nodesAdded = 0;
		minCost = Long.MAX_VALUE;
	}

	// initialize priority queue and add first element
	private void initPQ(int start) {
		pq = new PriorityQueue<>();
		Node v = new Node();
		v.level = 0;
		v.path = new ArrayList<Integer>();
		v.path.add(start);
		v.bound = getBound(v.path);
		pq.add(v);
		nodesAdded++;
	}

	// best first search algorithm for tsp, as given in Naepolitan.
	public void travel(int start) {
		Node v, u;
		initPQ(start);
		long cost = 0;
		while (!pq.isEmpty()) {
			v = pq.remove();
			if (v.bound < minCost) {
				for (int i = 1; i < size; i++) {
					if (v.path.contains(i)) {
						continue;
					}
					if (v.level + 1 == size - 2) {
						u = new Node();
						u.level = v.level + 1;
						u.path = new ArrayList<Integer>();
						u.path.addAll(v.path);
						u.path.add(i);
						for (int j = 1; j < size; j++) {
							if (!u.path.contains(j)) {
								u.path.add(j);
								break;
							}
						}
						u.path.add(0);
						cost = computeCost(u.path);
						if (cost < minCost) {
							minCost = cost;
							minTour = u.path;
						}
					} else {
						List<Integer> path = new ArrayList<Integer>();
						path.addAll(v.path);
						path.add(i);
						long bound = getBound(path);

						if (bound < minCost) {
							u = new Node();
							u.level = v.level + 1;
							u.path = path;
							u.bound = bound;
							pq.add(u);
							nodesAdded++;
						}
					}
				}
			}
		}
	}

	/*
	 * calculate lower bound for node
	 * path[0] - path[n-1] are ignored for rows (i.e all elements with outgoing edges)
	 * path[1] - path[n] are ignored for columns (i.e all elements with incoming edges)
	 * sum of minimums of remaining columns for each row is calculated.
	 * bound is calculated as sum of minimums + cost of tour in path
	 */
	public long getBound(List<Integer> path) {
		long min, sum = 0, cost = 0;
		List<Integer> skipColumns = new ArrayList<Integer>();
		List<Integer> skipRows = new ArrayList<Integer>();
		if (path.size() > 1) {
			skipRows = addElements(path, 0, path.size() - 1);
			skipColumns = addElements(path, 1, path.size());
			cost = computeCost(path);
		}
		for (int i = 0; i < size; i++) {
			if (skipRows.contains(i)) {
				continue;
			}
			min = Long.MAX_VALUE;
			for (int j = 0; j < size; j++) {
				if (ignoreColumn(path, i, j) || skipColumns.contains(j)) {
					continue;
				}
				if (costMatrix[i][j] < min) {
					min = costMatrix[i][j];
				}
			}
			sum += min;
		}
		return cost + sum;
	}

	//ignore column if it is same as row, or path has both row and column.
	private boolean ignoreColumn(List<Integer> path, int row, int col) {
		if ((row == col) || (path.contains(row) && path.contains(col))) {
			return true;
		}
		return false;
	}

	// creates sub-list of path based on start and end values.
	private List<Integer> addElements(List<Integer> path, int start, int end) {
		List<Integer> elements = new ArrayList<Integer>();
		for (int i = start; i < end; i++) {
			elements.add(path.get(i));
		}
		return elements;
	}

	// calculate cost of tour in path
	public long computeCost(List<Integer> path) {
		int cost = 0;
		if (path.size() == 1) {
			return cost;
		}
		for (int i = 0; i < path.size() - 1; i++) {
			cost += costMatrix[path.get(i)][path.get(i + 1)];
		}
		return cost;
	}

	// write result to output file
	public void printResults(long runTime, int problemCount, String outputPath) throws IOException {
		File file = new File(outputPath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);

		bw.write(problemCount + " " + size + " " + minCost + " " + nodesAdded + " " + runTime + "\n");
		System.out.println(problemCount + " " + size + " " + minCost + " " + nodesAdded + " " + runTime);
		minTour.remove(size);
		if (size < 15) {
			for (int element : minTour) {
				bw.write(element + "\n");
				System.out.println(element);
			}
		}
		bw.close();
	}
}
