package maven_demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
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
import entity.Path;

public class hello {

	public static String url = "http://fleet.tn/ws_rimtrack_all/signin";
	public static String url2 = "tokenhttp://fleet.tn/ws_rimtrack_all/paths/813785";
	public static String urlDetails = "http://fleet.tn/ws_rimtrack_all/paths/details/813785";

	public static JSONObject body;
	public static ArrayList<Path> paths;
	public static HttpResponse<JsonNode> jsonResponse;
	public static List<Coordinate> corrdinateListe;
	public static ArrayList<Client> clientList;

	public static void main(String[] args) throws UnirestException {
		body = new JSONObject();
		getCLientList();
		String token = signIn();
		if (token.length() > 0) {
			getPaths(token);
			getPassedClient();
		}
	}
	private static void getPassedClient() {
		boolean visited =false;
		for (int i = 0; i < clientList.size(); i++) {
			Client client = clientList.get(i);
			int j =0;
			visited = false;
			while(j< paths.size()&& visited==false) {
				List<Coordinate> coordinates = paths.get(j).getCoordinates();
				int k = 0;
				 
				while ( k < coordinates.size() && visited==false) {
					try {
						if(coordinates.get(k).getSpeed()==0)
						{
							double distance = PathUtils.distance(Double.parseDouble(client.getLatitude()),
									Double.parseDouble(client.getLongitude()), coordinates.get(k).getLat(),
									coordinates.get(k).getLng(), "K");
							if(distance<=0.05) {
								visited = true;
								System.out.println(k<coordinates.size());
								System.out.println("client " +client.getName()+ "is visited ");
							}
						}
					}catch (NullPointerException e) {
						// TODO: handle exception
						System.out.println(coordinates.get(k));
					}
					k+= 1;
					/*System.out.println("CLient : " + client.getName() + ",date : "
							+ coordinates.get(k).getDate() + 
							" ,distance restante :" + distance);
					*/
				}
			}
		}
	}

	/*
	 * for (int i = 0; i < paths.size(); i++) { System.out.println(paths.get(i)); }
	 */
	private static List<Coordinate> getDetails(String token, Long beginTime, Long endTime) {

		body = new JSONObject();
		body.accumulate("startDate", beginTime);
		body.accumulate("endDate", endTime);
		corrdinateListe = new ArrayList<Coordinate>();
		try {
			jsonResponse = Unirest.post(urlDetails).header("Accept", "application/json")
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

	private static void getCLientList() {
		clientList = new ArrayList<Client>();
		try {
			jsonResponse = Unirest.get("http://127.0.0.1:8000/missiondriverpoi/21").asJson();
			if (jsonResponse.getStatus() == 200) {
				JSONArray result = jsonResponse.getBody().getArray();
				for (int i = 0; i < result.length(); i++) {
					JSONObject obj = result.getJSONObject(i);
					Client client = new Client();
					client.setAddress(obj.getString("address"));
					client.setLatitude(obj.getString("latitude"));
					client.setLongitude(obj.getString("longitude"));
					client.setName(obj.getString("name"));
					client.setPointInterestId(obj.getString("point_interest_id"));
					clientList.add(client);

				}
			}
		} catch (UnirestException e1) {
			// TODO Auto-generated catch block
			// System.out.println(el);
		}

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

	private static void getPaths(String token) {
		body = new JSONObject();
		body.accumulate("endDate", "2019-04-05");
		body.accumulate("startDate", "2019-04-01");
		paths = new ArrayList<Path>();
		System.out.println("token ====>" + token);
		try {
			jsonResponse = Unirest.post(url2).header("content-Type", "application/json;charset=UTF-8")
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

					List<Coordinate> l = getDetails(token, path.getBeginPathTime(), path.getEndPathTime());
					path.setCoordinates(l);
					paths.add(path);
				}
			}
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
