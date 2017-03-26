
import java.util.List;

/*
 * Node object used to add elements to priority queue
 * It implements comparable to allow priority queue to maintain order based on bound value.
 */
public class Node implements Comparable<Node>{
	int level;
	List<Integer> path;
	long bound;
	
	@Override
	public int compareTo(Node o) {
		
		return Long.compare(this.bound, o.bound);
	}
}
