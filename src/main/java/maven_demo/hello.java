package maven_demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import Util.PathUtils;
import entity.Client;
import entity.Coordinate;
import entity.Detail;
import entity.Mission;
import entity.Path;
import entity.Poi;
import entity.Suivi;


public class hello {

	public static String url = "http://fleet.tn/ws_rimtrack_all/signin";
	public static String urlPaths = "http://fleet.tn/ws_rimtrack_all/paths/";
	public static String urlDetails = "http://fleet.tn/ws_rimtrack_all/paths/details/";
	public static String urlMission = "http://127.0.0.1:8000/mission_v2";
	public static String urlDevice = "http://127.0.0.1:8000/device";
	public static String AddUrl	="http://127.0.0.1:8000/addcalsuivi";
	public static JSONObject body;
	public static ArrayList<Path> paths;
	public static HttpResponse<JsonNode> jsonResponse;
	public static List<Coordinate> corrdinateListe;
	public static ArrayList<Suivi> suiviList = new ArrayList<Suivi>();
	public static ArrayList<Mission> missionList;

	public static void main(String[] args) throws UnirestException {
		
		//store();
		getAllMissions();
		body = new JSONObject();
		
		String token = signIn();
		if (token.length() > 0) {
			
		for (int i = 0; i < missionList.size(); i++) {
				
				paths = new ArrayList<Path>();
				paths = getPaths(token,missionList.get(i).getDateDeb(),missionList.get(i).getDateFin(),missionList.get(i).getDeviceId());
					
				getPassedClient(missionList.get(i).getIdmission(),missionList.get(i).getPoi(),paths);
			}
		}
		store();
		

	}
	private static void store() throws UnirestException {
		
		for (int j = 0;j < suiviList.size(); j++) {
			System.out.println(suiviList.get(j).getGoOutTime());
			final JSONObject ExJson = new JSONObject()
					.put("date_visite", suiviList.get(j).getVisitedTime().toString())
					.put("date_sortie", suiviList.get(j).getGoOutTime().toString())
					.put("poi_visite", suiviList.get(j).getPointInterestId())
					.put("duree", suiviList.get(j).getTime().toString());
			final HttpResponse<String> jsonResponse = Unirest.post(AddUrl).header("content-Type", "application/json;charset=UTF-8")
					.header("Accept", "application/json").body(ExJson).asString();
			System.out.println(jsonResponse.getBody());
			
		}
	}
	private static void getPassedClient(Integer idMission, List<Poi> poiList,ArrayList<Path> pathList) {

		boolean visited = false;
		for (int i = 0; i < poiList.size(); i++) {
			Poi client = poiList.get(i);
			
			visited = false;
			int j = 0;
			boolean stop = false;
			while (j < pathList.size() && stop == false) {
				//List<Coordinate> coordinates = paths.get(j).getCoordinates();
				int k = 0;
				Suivi l =new Suivi();
				
				Long visitedTime = null;
				Long goOutTime;
				String time;
				
					double distance = PathUtils.distance(client.getLatitude(),
							client.getLongitude(), pathList.get(j).getBeginPathLatitude(),
							pathList.get(j).getBeginPathLongitude(), "K");
					try {
						
							
							if (distance <= 0.3 && visited==false) {
								visited=true;
								// System.out.println(k<coordinates.size());
								System.out.println("client " + client.getName() + " est visité ");
								visitedTime =pathList.get(j).getBeginPathTime();
								System.out.println("visited Time: "+visitedTime);
							}
						 else {
							if(visited==true) {
								
								
								goOutTime =pathList.get(j).getEndPathTime();
								/*Timestamp ts=new Timestamp(goOutTime);  
				                Date outDate=new Date(ts.getTime()); */
							
								System.out.println("go out Time: "+goOutTime);
								time = pathList.get(j).getNextStopDurationStr();
								l.setTime(time);
								l.setVisitedTime(pathList.get(j).getBeginPathTime());
								l.setGoOutTime(goOutTime);
								l.setPoiName(client.getName());
								l.setPointInterestId(client.getPointInterestId());
								l.setIdmission(idMission);
								suiviList.add(l);
								System.out.println("Time: "+time);
								System.out.println("****************************************************************************************");
									
								stop=true;
								
							}
							/*System.out.println("CLient : " + client.getName() + ",date : "
									+ coordinates.get(k).getDate() + " ,distance restante :" + distance);*/

						}
					} catch (NullPointerException e) {
						// TODO: handle exception
						//System.out.println(pathList.get(j).getBeginPathLatitude());
					}
				
				j += 1;
			}
			if (j >= paths.size()) {
				System.out.println("client " + client.getName() + " est non visité ");
				System.out.println("********************************************************************************************************");
			}
		}
	}
	
	private static Long getDeviceId(int vehiculeId) {
		Long id = -1L;
		try {
			jsonResponse = Unirest.get(urlDevice + "/" +vehiculeId).asJson();
			if (jsonResponse.getStatus() == 200)
				id = jsonResponse.getBody().getArray().getJSONObject(0).getLong("id_device");

		} catch (UnirestException e1) {
			System.out.println(e1.getMessage());
		}
		return id;
	}

	private static void getAllMissions() {
		missionList = new ArrayList<Mission>();
		try {
			jsonResponse = Unirest.get(urlMission).asJson();
			if (jsonResponse.getStatus() == 200) {
				JSONArray result = jsonResponse.getBody().getArray();
				//System.out.println(result);
				for (int i = 0; i < result.length(); i++) {
					JSONObject obj = result.getJSONObject(i);
					 //System.out.println(obj);
					Mission m = new Mission();
					m.setIdmission(obj.getInt("idmission"));
					m.setDateDeb(obj.getString("date_deb"));
					m.setDateFin(obj.getString("date_fin"));
					m.setVehiculeId(obj.getInt("vehicule_id"));
					Long dvceid = getDeviceId(m.getVehiculeId());
					  //System.out.println(dvceid);
					m.setDeviceId(dvceid);
					ArrayList<Poi> pois = new ArrayList<Poi>();
					JSONArray poi = obj.getJSONArray("poi");
					
					for (int j = 0; j < poi.length(); j++) {
						JSONObject objPoi = poi.getJSONObject(j);
						//System.out.println(objPoi);
						Poi p = new Poi();
						//System.out.println(objPoi.getString("nom"));
						p.setPointInterestId(objPoi.getInt("point_interest_id"));
						p.setAddress(objPoi.getString("address"));
						p.setLatitude(objPoi.getDouble("latitude"));
						p.setLongitude(objPoi.getDouble("longitude"));
						p.setName(objPoi.getString("nom"));
						pois.add(p);
					}
					m.setPoi(pois);
					missionList.add(m);
					
				}
			}
		} catch (UnirestException e1) {
			// TODO Auto-generated catch block
			// System.out.println(el);
		}
	}

	private static List<Coordinate> getDetails(String token, Long beginTime, Long endTime,Long deviceId) {

		body = new JSONObject();
		body.accumulate("startDate", beginTime);
		body.accumulate("endDate", endTime);
		corrdinateListe = new ArrayList<Coordinate>();
		try {
			jsonResponse = Unirest.post(urlDetails+deviceId).header("Accept", "application/json")
					.header("content-Type", "application/json").header("Authorization", token).body(body.toString())
					.asJson();
			if (jsonResponse.getStatus() == 200) {
				Detail detail = new Detail();
				detail.setNextStopDuration(jsonResponse.getBody().getObject().getInt("nextStopDuration"));
				JSONArray coord = jsonResponse.getBody().getObject().getJSONArray("coordinates");
				for (int i = 0; i < coord.length(); i++) {
					JSONObject c = coord.getJSONObject(i);
					Coordinate co = new Coordinate();
					co.setLat(c.getDouble("lat"));
					co.setLng(c.getDouble("lng"));
					co.setDate(c.getLong("date"));
					co.setSpeed(c.getInt("speed"));
					corrdinateListe.add(co);
				}
				detail.setCoordinates(corrdinateListe);
				return corrdinateListe;
			}

		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return corrdinateListe;
	}



	private static String signIn() {
		body = new JSONObject();
		body.accumulate("username", "ecoti");
		body.accumulate("password", "ecoti500p");
		try {
			jsonResponse = Unirest.post(url).header("Accept", "application/json")
					.header("content-Type", "application/json").body(body.toString()).asJson();
			if (jsonResponse.getStatus() == 200) {
				JSONObject myObj = jsonResponse.getBody().getObject();
				return myObj.getString("token");
			}

		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	private static ArrayList<Path> getPaths(String token,String db,String df,Long deviceId) {

		body = new JSONObject();
		body.accumulate("endDate", df);
		body.accumulate("startDate", db);
		paths = new ArrayList<Path>();
		
		try {
			jsonResponse = Unirest.post(urlPaths+deviceId).header("content-Type", "application/json;charset=UTF-8")
					.header("Authorization", token).header("Accept", "application/json").body(body.toString()).asJson();
			if (jsonResponse.getStatus() == 200) {
				JSONArray jsonObj = new JSONArray(jsonResponse.getBody().toString());
				// System.out.println(jsonObj.toString());
				for (int i = 0; i < jsonObj.length(); i++) {
					JSONObject c = jsonObj.getJSONObject(i);
					Path path = new Path();
					path.setLastName(c.getString("lastName"));
					path.setEndPathTime(c.getLong("endPathTime"));
					path.setBeginPathTime(c.getLong("beginPathTime"));
					path.setDeviceId(c.getInt("deviceId"));
					path.setBeginPathLatitude(c.getDouble("beginPathLatitude"));
					path.setBeginPathLongitude(c.getDouble("beginPathLongitude"));
					path.setNextStopDurationStr(c.getString("nextStopDurationStr"));
					List<Coordinate> l = getDetails(token, path.getBeginPathTime(), path.getEndPathTime(),deviceId);
					path.setCoordinates(l);
					//System.out.println(path);
					paths.add(path);
				}
				
			}
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paths;
	}

	
}
