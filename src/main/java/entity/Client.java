package entity;


public class Client {

	private String name;
	private String longitude;
	private String latitude;
	private String pointInterestId;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPointInterestId() {
		return pointInterestId;
	}

	public void setPointInterestId(String pointInterestId) {
		this.pointInterestId = pointInterestId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Client [name=" + name + ", longitude=" + longitude + ", latitude=" + latitude + ", pointInterestId="
				+ pointInterestId + ", address=" + address + "]";
	}
	
	

}
