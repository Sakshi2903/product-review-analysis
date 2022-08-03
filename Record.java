public class Record {
	
	//each type -> each rating

	private int rating; 
	private int clusterNumber; 
	
	public Record(int rating) {
		super();
		this.rating=rating;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getClusterNumber() {
		return clusterNumber;
	}
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}
	@Override
	public String toString() {
		return "Record [rating=" + rating +", clusterNumber=" + clusterNumber + "]";
	}
}
