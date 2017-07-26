package application;

/**
 * Created by nicholas on 7/19/17.
 */
public class Haversine {


    //Returns distance in miles
    public static double getDistance(Double lat1,Double lng1,Double lat2, Double lng2){
        Double dLng = lng2 - lng1;
        Double dLat = lat2 - lat1;

        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(toRadian(lat1)) * Math.cos(toRadian(lat2)) *
                                Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double d = 3961 * c;


        return toRadian(d);
    }


    //Converts degrees for radian
    private static double toRadian(double deg){
        return deg * (Math.PI/180);
    }
}
