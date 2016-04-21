
/*
 * We have some customer records in a text file (customers.json) -- one customer per line, JSON-encoded. 
 * We want to invite any customer within 100km of our Dublin office for some food and drinks on us. 
 * Write a program that will read the full list of customers and output the names and user ids of
 * matching customers (within 100km), sorted by User ID (ascending).
 *  
 * The GPS coordinates for our Dublin office are 53.3381985, -6.2592576.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CustomerInvitation 
{
	static double DublinLatitude = 53.3381985;
	static double DublinLongitude = -6.2592576;

	@SuppressWarnings({ "resource" })
	public static void main(String[] args)
	{

		List<Customer> Customer = new ArrayList<>();

		try {
			String filename = ".\\InputFiles\\Customer.txt";
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));

			String line;
			System.out.println("Invitation Sent to: ");
			while ((line = br.readLine()) != null) 
			{
				String x1 = line.substring(1, line.length()-1);
				String[] x2 = x1.split(",");
				Customer cust = new Customer();
				for (int i = 0; i < x2.length; i++) 
				{
					String x3[] = x2[i].split(":");

					if(x3[0].trim().equals("\"latitude\""))
					{
						x3[1] = x3[1].trim().substring(1, x3[1].length()-2);
						cust.setLatitude(Double.parseDouble(x3[1]));
					}
					if(x3[0].trim().equals("\"user_id\""))
					{
						x3[1] = x3[1].trim();
						cust.setUserId(Integer.parseInt(x3[1]));
					}
					if(x3[0].trim().equals("\"name\""))
					{
						x3[1] = x3[1].trim().substring(1, x3[1].length()-2);
						cust.setName(x3[1]);
					}
					if(x3[0].trim().equals("\"longitude\""))
					{
						x3[1] = x3[1].trim().substring(1, x3[1].length()-2);
						cust.setLongitude(Double.parseDouble(x3[1]));
					}
				}
				Customer.add(cust);
			}


			Collections.sort(Customer);
			for (Customer temp : Customer) 
			{
				//				System.out.println(temp.getLatitude());
				//				System.out.println(temp.getUserId());
				//				System.out.println(temp.getName());
				//				System.out.println(temp.getLongitude());
				double dist=distance(temp.getLatitude(),temp.getLongitude(), DublinLatitude,DublinLongitude);

				if (dist <= 100)
				{
					System.out.println(temp.getUserId() + " " + temp.getName());			
				}
			}	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This function calculates distance between to points on Earth in Kilometers
	private static double distance(double latitude1, double longitude1, double
			latitude2, double longitude2)
	{
		double theta = longitude1 - longitude2;
		double dist = Math.sin(deg2rad(latitude1)) * Math.sin(deg2rad(latitude2)) +
				Math.cos(deg2rad(latitude1)) * Math.cos(deg2rad(latitude2)) *
				Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		long radius = 6371; // Radius of Earth
		double distance = radius * dist;

		return (distance);

	}

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg)
	{
		return (deg * Math.PI / 180.0);
	}
}
