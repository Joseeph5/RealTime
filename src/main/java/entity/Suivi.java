package entity;

import java.sql.Date;

public class Suivi {
	private Integer idmission;
	private Integer pointInterestId;
	private String poiName;
	private String time;
	private Long visitedTime;
	private Long goOutTime;
	
	public Integer getIdmission() {
		return idmission;
	}

	public void setIdmission(Integer idmission) {
		this.idmission = idmission;
	}

	public Integer getPointInterestId() {
		return pointInterestId;
	}

	public void setPointInterestId(Integer pointInterestId) {
		this.pointInterestId = pointInterestId;
	}

	public String getPoiName() {
		return poiName;
	}

	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}

	
	public Long getVisitedTime() {
		return visitedTime;
	}

	public void setVisitedTime(Long visitedTime) {
		this.visitedTime = visitedTime;
	}

	public Long getGoOutTime() {
		return goOutTime;
	}

	public void setGoOutTime(Long goOutTime) {
		this.goOutTime = goOutTime;
	}

	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Suivi [idmission=" + idmission + ", pointInterestId=" + pointInterestId + ", poiName=" + poiName
				+ ", time=" + time + ", visitedTime=" + visitedTime + ", goOutTime=" + goOutTime + "]";
	}

	
	
	
	
}
