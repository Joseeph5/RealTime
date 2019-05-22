package entity;

import java.util.List;
import java.util.Map;

public class Mission {

	private Integer idmission;
	private String dateDeb;
	private String dateFin;
	private Integer vehiculeId;
	private Long deviceId;
	private List<Poi> poi = null;

	public Integer getIdmission() {
		return idmission;
	}

	public void setIdmission(Integer idmission) {
		this.idmission = idmission;
	}

	public String getDateDeb() {
		return dateDeb;
	}

	public void setDateDeb(String dateDeb) {
		this.dateDeb = dateDeb;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getVehiculeId() {
		return vehiculeId;
	}

	public void setVehiculeId(Integer vehiculeId) {
		this.vehiculeId = vehiculeId;
	}

	public List<Poi> getPoi() {
		return poi;
	}

	public void setPoi(List<Poi> poi) {
		this.poi = poi;
	}
	

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public String toString() {
		return "Mission [idmission=" + idmission + ", dateDeb=" + dateDeb + ", dateFin=" + dateFin + ", vehiculeId="
				+ vehiculeId + " device Id = "+deviceId+ ", poi=" + poi + "]";
	}


}