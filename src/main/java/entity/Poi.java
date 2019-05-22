package entity;

public class Poi {

	private String name;
	private Integer pointInterestId;
	private String address;
	private Double latitude;
	private Double longitude;

	public Integer getPointInterestId() {
		return pointInterestId;
	}

	public void setPointInterestId(Integer pointInterestId) {
		this.pointInterestId = pointInterestId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Poi [pointInterestId=" + pointInterestId + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}