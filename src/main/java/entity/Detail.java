package entity;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detail {

private Integer nextStopDuration;
private Object lastName;
private Integer endPathTime;
private Double beginPathLatitude;
private Double beginPathLongitude;
private Double endPathLatitude;
private Double endPathLongitude;
private Integer pathDuration;
private Double distanceDriven;
private String nextStopDurationStr;
private Integer unchangedEndPathTime;
private Integer beginPathTime;
private Integer maxSpeed;
private String pathDurationStr;
private Integer deviceId;
private List<Coordinate> coordinates = null;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public Integer getNextStopDuration() {
return nextStopDuration;
}

public void setNextStopDuration(Integer nextStopDuration) {
this.nextStopDuration = nextStopDuration;
}

public Object getLastName() {
return lastName;
}

public void setLastName(Object lastName) {
this.lastName = lastName;
}

public Integer getEndPathTime() {
return endPathTime;
}

public void setEndPathTime(Integer endPathTime) {
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

public Integer getBeginPathTime() {
return beginPathTime;
}

public void setBeginPathTime(Integer beginPathTime) {
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

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}