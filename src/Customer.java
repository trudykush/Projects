
public class Customer implements Comparable<Object> 
{
	private double latitude;
	private Integer UserId;
	private String name;
	private double longitude;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public int compareTo(Object o) 
	{
		// TODO Auto-generated method stub
		int comparing=((Customer)o).getUserId();
		/* For Ascending order*/
		return this.getUserId()-comparing;
	}


}
