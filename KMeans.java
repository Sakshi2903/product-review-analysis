import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KMeans {

	List<Record> data = new ArrayList<Record>();
	List<Cluster> clusters = new ArrayList<Cluster>();
	Map<Cluster, List<Record>> clusterRecords = new HashMap<Cluster, List<Record>>();
	
	public void genereateRecord(int[] rating,int n) {
		Record record = new Record(1);
		data.add(record);
		record = new Record(3);
		data.add(record);
		record = new Record(5);
		data.add(record);
		for(int i=0;i<n;i++)
		{
			record = new Record(rating[i]);
			data.add(record);
		}
	}

	public void initiateClusterAndCentroid(int clusterNumber) {
		int counter = 1;
		Iterator<Record> iterator = data.iterator();
		Record record = null;
		
		while(iterator.hasNext()) {
			record = iterator.next();
			if(counter <= clusterNumber) {
				record.setClusterNumber(counter);
				initializeCluster(counter, record);
				counter++;
			}else {
				/*System.out.println();
				System.out.println();
				for(Cluster cluster : clusters) {
					System.out.println();
				}
				System.out.println();*/
                double minDistance = Integer.MAX_VALUE;
                Cluster whichCluster = null;
                
                for(Cluster cluster : clusters) {
                	double distance = cluster.calculateDistance(record);
                	if(minDistance > distance) {
                		minDistance = distance;
                		whichCluster = cluster;
                	}
                }
                
                record.setClusterNumber(whichCluster.getClusterNumber());
				whichCluster.updateCentroid(record);
				clusterRecords.get(whichCluster).add(record);
			}
			
		}

	}

	public void initializeCluster(int clusterNumber, Record record) {
		
		Cluster cluster = new Cluster(clusterNumber,record.getRating(),record.getRating(),record.getRating());
		clusters.add(cluster);
		List<Record> clusterRecord = new ArrayList<Record>();
		clusterRecord.add(record);
		clusterRecords.put(cluster, clusterRecord);

	}

	public void printRecordInformation() {
		int c1=0,c2=0,c3=0;
		for(Record record : data) {
		//System.out.println(record);
		if(record.getClusterNumber()==1)
		{
			c1++;
		}
		else if(record.getClusterNumber()==2)
		{
			c2++;
		}
		else if(record.getClusterNumber()==3)
		{
			c3++;
		}

		   }

		if((c1==c2 && c2==c3))
		{
			System.out.println("You have Equal reviews of all on this Cake!");
		}
		else if(c1 > c2 && c1 > c3)
        		{
            		System.out.println("You have Bad reviews on this Cake!");
        		}
        		else if(c2>c3)
        		{
           		System.out.println("You have Average reviews on this Cake!");
        		}
        		else
        		{
            		System.out.println("You have Good reviews on this Cake!");
        		}
	   }
}
