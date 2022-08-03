public class Cluster {

	private int GoodCentroid;
	private int AvgCentroid;
	private int BadCentroid;
	private int clusterNumber;
	
	public Cluster(int clusterNumber, int GoodCentroid, int AvgCentroid, int BadCentroid) {
		super();
		this.clusterNumber = clusterNumber;
		this.GoodCentroid = GoodCentroid;
		this.AvgCentroid = AvgCentroid;
		this.BadCentroid = BadCentroid;
	}
	
	public int getGoodCentroid() {
		return GoodCentroid;
	}
	public void setGoodCentroid(int GoodCentroid) {
		this.GoodCentroid = GoodCentroid;
	}
	public int getAvgCentroid() {
		return AvgCentroid;
	}
	public void setAvgCentroid(int AvgCentroid) {
		this.AvgCentroid = AvgCentroid;
	}
	public int getBadCentroid() {
		return BadCentroid;
	}
	public void setBadCentroid(int BadCentroid) {
		this.BadCentroid = BadCentroid;
	}
	public int getClusterNumber() {
		return clusterNumber;
	}
	public void setClusterNumber(int clusterNumber) {
		this.clusterNumber = clusterNumber;
	}

	@Override
	public String toString() {
		return "Cluster [GoodCentroid=" + GoodCentroid + ", AvgCentroid=" + AvgCentroid + ", BadCentroid="
				+ BadCentroid + ", clusterNumber=" + clusterNumber + "]";
	}
	
	// Euclidean distance calculation
	public double calculateDistance(Record record) {
		return Math.sqrt(Math.pow((getGoodCentroid() - record.getRating()), 2) + Math.pow((getAvgCentroid() - record.getRating()),2) + Math.pow((getBadCentroid() - record.getRating()), 2));
    }

	public void updateCentroid(Record record) {
		setGoodCentroid((getGoodCentroid()+record.getRating())/2);
		setAvgCentroid((getAvgCentroid()+record.getRating())/2);
		setBadCentroid((getBadCentroid()+record.getRating())/2); 
	}

}
