package entity;

import java.util.List;

public class Path {

	private Integer nextStopDuration;
	//private Boolean stops;
	private String lastName;
	private Long endPathTime;
	private Double beginPathLatitude;
	private Double beginPathLongitude;
	private Double endPathLatitude;
	private Double endPathLongitude;
	private Integer pathDuration;
	private Double distanceDriven;
	private String nextStopDurationStr;
	private Integer unchangedEndPathTime;
	private Long beginPathTime;
	private Integer maxSpeed;
	private String pathDurationStr;
	private Integer deviceId;
	private List<Coordinate> coordinates = null;

	public Integer getNextStopDuration() {
		return nextStopDuration;
	}

	public void setNextStopDuration(Integer nextStopDuration) {
		this.nextStopDuration = nextStopDuration;
	}

	/*public Boolean getStops() {
		return stops;
	}

	public void setStops(Boolean stops) {
		this.stops = stops;
	}*/

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getEndPathTime() {
		return endPathTime;
	}

	public void setEndPathTime(Long endPathTime) {
		this.endPathTime = endPathTime;
	}

	public Double getBeginPathLatitude() {
		return beginPathLatitude;
	}

	public void setBeginPathLatitude(Double beginPathLatitude) {
		this.beginPathLatitude = beginPathLatitude;
	}

	public Double getBeginPathLongitude() {
		return beginPathLongitude;
	}

	public void setBeginPathLongitude(Double beginPathLongitude) {
		this.beginPathLongitude = beginPathLongitude;
	}

	public Double getEndPathLatitude() {
		return endPathLatitude;
	}

	public void setEndPathLatitude(Double endPathLatitude) {
		this.endPathLatitude = endPathLatitude;
	}

	public Double getEndPathLongitude() {
		return endPathLongitude;
	}

	public void setEndPathLongitude(Double endPathLongitude) {
		this.endPathLongitude = endPathLongitude;
	}

	public Integer getPathDuration() {
		return pathDuration;
	}

	public void setPathDuration(Integer pathDuration) {
		this.pathDuration = pathDuration;
	}

	public Double getDistanceDriven() {
		return distanceDriven;
	}

	public void setDistanceDriven(Double distanceDriven) {
		this.distanceDriven = distanceDriven;
	}

	public String getNextStopDurationStr() {
		return nextStopDurationStr;
	}

	public void setNextStopDurationStr(String nextStopDurationStr) {
		this.nextStopDurationStr = nextStopDurationStr;
	}

	public Integer getUnchangedEndPathTime() {
		return unchangedEndPathTime;
	}

	public void setUnchangedEndPathTime(Integer unchangedEndPathTime) {
		this.unchangedEndPathTime = unchangedEndPathTime;
	}

	public Long getBeginPathTime() {
		return beginPathTime;
	}

	public void setBeginPathTime(Long beginPathTime) {
		this.beginPathTime = beginPathTime;
	}

	public Integer getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Integer maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getPathDurationStr() {
		return pathDurationStr;
	}

	public void setPathDurationStr(String pathDurationStr) {
		this.pathDurationStr = pathDurationStr;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	@Override
	public String toString() {
		String message = "Path : lastName=" + lastName + ", endPathTime=" + endPathTime + ", beginPathTime="
				+ beginPathTime +", My device Id= "+deviceId +", My Coordinates :\n";
		for (int i = 0; i < coordinates.size(); i++) {
			message = message+ "lat : "+coordinates.get(i).getLat()+" lng : "+coordinates.get(i).getLng()+"\n";
			
		}
		return message;
	}
	
}
