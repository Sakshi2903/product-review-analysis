//product review

import java.sql.*;
import java.util.*;

public class productReview
{
	public static void main(String args[])
	{
		String driver="oracle.jdbc.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user_name="system";
		String pwd="sakshi";
		int ch=0;
		
		int pinata[]=new int[100];
		int ice[]=new int[100];
		int cheese[]=new int[100];
		int fondant[]=new int[100];
		int tier[]=new int[100];
		
		int i=0,j=0,k=0,l=0,m=0;

		try {
			Class.forName(driver);
			Connection con= DriverManager.getConnection(url,user_name,pwd);
		    	Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select type,rating from cakefeedback");  
			while(rs.next())  {
				String name=rs.getString("type");
				
				if(name.equals("Pinata"))
				{
					int rat=rs.getInt("rating");
					pinata[i++]=rat;
				}
	
				if(name.equals("Ice-Cake"))
				{
					int rat=rs.getInt("rating");
					ice[j++]=rat;
				}

				if(name.equals("Cheese-Cake"))
				{
					int rat=rs.getInt("rating");
					cheese[k++]=rat;
				}

				if(name.equals("Fondant"))
				{
					int rat=rs.getInt("rating");
					fondant[l++]=rat;
				}
			
				if(name.equals("3-tier"))
				{
					int rat=rs.getInt("rating");
					tier[m++]=rat;
				}

			}
			con.close();

		int clusterNumber = 3;

		//calling method for analysis for pinata cake
		KMeans demo=new KMeans();
		int size=i;
		demo.genereateRecord(pinata,size);
		demo.initiateClusterAndCentroid(clusterNumber);
		System.out.println("*************** Review Analysis of Pinata Cake ***************");
		demo.printRecordInformation();

		//calling method for analysis for Ice cake
		KMeans demo1=new KMeans();
		size=j;
		demo1.genereateRecord(ice,size);
		demo1.initiateClusterAndCentroid(clusterNumber);
		System.out.println("\n*************** Review Analysis of Ice Cake ***************");
		demo1.printRecordInformation();

		//calling method for analysis for Cheese cake
		KMeans demo2=new KMeans();
		size=k;
		demo2.genereateRecord(cheese,size);
		demo2.initiateClusterAndCentroid(clusterNumber);
		System.out.println("\n*************** Review Analysis of Cheese Cake ***************");
		demo2.printRecordInformation();

		//calling method for analysis for Fondant cake
		KMeans demo3=new KMeans();
		size=l;
		demo3.genereateRecord(fondant,size);
		demo3.initiateClusterAndCentroid(clusterNumber);
		System.out.println("\n*************** Review Analysis of Fondant Cake ***************");
		demo3.printRecordInformation();
	
		//calling method for analysis for 3-tier cake
		KMeans demo4=new KMeans();
		size=m;
		demo4.genereateRecord(tier,size);
		demo4.initiateClusterAndCentroid(clusterNumber);
		System.out.println("\n*************** Review Analysis of 3-tier Cake ***************");
		demo4.printRecordInformation();

		}catch(Exception e)
		{
			System.out.println("Exception is: "+e);
		}
	}
}