package entity;


import java.util.HashMap;
import java.util.Map;

public class Coordinate {

private Long date;
private Integer speed;
private Double lat;
private Double lng;
private Boolean aboveTheSpeedLimit;
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

public Long getDate() {
return date;
}

public void setDate(Long date) {
this.date = date;
}

public Integer getSpeed() {
return speed;
}

public void setSpeed(Integer speed) {
this.speed = speed;
}

public Double getLat() {
return lat;
}

public void setLat(Double lat) {
this.lat = lat;
}

public Double getLng() {
return lng;
}

public void setLng(Double lng) {
this.lng = lng;
}

public Boolean getAboveTheSpeedLimit() {
return aboveTheSpeedLimit;
}

public void setAboveTheSpeedLimit(Boolean aboveTheSpeedLimit) {
this.aboveTheSpeedLimit = aboveTheSpeedLimit;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "Coordinate [date=" + date + ", speed=" + speed + ", lat=" + lat + ", lng=" + lng + ", aboveTheSpeedLimit="
			+ aboveTheSpeedLimit + ", additionalProperties=" + additionalProperties + "]";
}


}