package spreadsheetUpdates.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CycleDetector {
	private Map<String, List<String>> adjacencyList;

	/**
	 * 
	 * @param adjacencyListIn
	 *            cell graph
	 */
	public CycleDetector(Map<String, List<String>> adjacencyListIn) {
		Logger.writeMessage("In Cycle detector constructor\n", Logger.DebugLevel.CONSTRUCTOR);
		adjacencyList = adjacencyListIn;
	}

	/**
	 * 
	 * @return boolean based on cycle detection.
	 */
	public boolean hasCycle() {
		List<String> visited = new ArrayList<String>();
		List<String> currentTree = new ArrayList<String>();

		for (String id : adjacencyList.keySet()) {
			if (!visited.contains(id)) {
				if (dfs_visit(id, visited, currentTree))
					return true;
				currentTree.remove(id);
			}
		}
		return false;
	}

	/**
	 * 
	 * @param id
	 *            of cell
	 * @param visited
	 *            list
	 * @param currentTree
	 *            list
	 * @return
	 */
	private boolean dfs_visit(String id, List<String> visited, List<String> currentTree) {
		visited.add(id);
		currentTree.add(id);
		for (String v : adjacencyList.get(id)) {
			if (currentTree.contains(v)) {
				return true;
			}
			if (!visited.contains(v))
				if (dfs_visit(v, visited, currentTree)) {
					return true;
				}
		}
		return false;
	}

	@Override
	public String toString() {
		return "utility class to detect cycles in directed graph";
	}
}
