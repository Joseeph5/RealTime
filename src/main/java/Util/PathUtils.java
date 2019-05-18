package Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PathUtils {

	public PathUtils() {

	}

	public static Timestamp diff(java.util.Date t1, java.util.Date t2) throws Exception {
		try {
			// Make sure the result is always > 0
			if (t1.compareTo(t2) < 0) {
				java.util.Date tmp = t1;
				t1 = t2;
				t2 = tmp;
			}

			// Timestamps mix milli and nanoseconds in the API, so we have to
			// separate the two
			long diffSeconds = (t1.getTime() / 1000) - (t2.getTime() / 1000);
			// For normals dates, we have millisecond precision
			int nano1 = ((int) t1.getTime() % 1000) * 1000000;
			// If the parameter is a Timestamp, we have additional precision in
			// nanoseconds
			if (t1 instanceof Timestamp)
				nano1 = ((Timestamp) t1).getNanos();
			int nano2 = ((int) t2.getTime() % 1000) * 1000000;
			if (t2 instanceof Timestamp)
				nano2 = ((Timestamp) t2).getNanos();

			int diffNanos = nano1 - nano2;
			if (diffNanos < 0) {
				// Borrow one second
				diffSeconds--;
				diffNanos += 1000000000;
			}

			// mix nanos and millis again
			Timestamp result = new Timestamp((diffSeconds * 1000) + (diffNanos / 1000000));
			// setNanos() with a value of in the millisecond range doesn't affect
			// the value of the time field
			// while milliseconds in the time field will modify nanos! Damn, this
			// API is a *mess*
			result.setNanos(diffNanos);
			return result;
		} catch (Exception e) {
			return new Timestamp(0);
		}
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		if (Double.isNaN(dist))
			dist = 0;
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static void main(String[] args) {
		System.out.println(
				"mm=>" + new PathUtils().distance(35.80869166666666, 10.6291, 35.80869166666666, 10.6292, "K"));
	}

	//
	public static String toDate(String timestamp) {
		String dt = "";
		if (timestamp == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		dt = sdf.format(timestamp);
		return dt;

	}
}