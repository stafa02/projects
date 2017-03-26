package recommenderSystem;

/*
 * Wrapper for maintaining item and similarity.
 * Implements comparable to sort by similarity in descending order. 
 */
public class ItemSimlarity implements Comparable<ItemSimlarity> {
	private int item;
	private double similarity;
	
	public ItemSimlarity(int item,double similarity){
		this.item = item;
		this.similarity = similarity;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	@Override
	public int compareTo(ItemSimlarity o) {
		return Double.compare(o.similarity, this.similarity);
	}
}
