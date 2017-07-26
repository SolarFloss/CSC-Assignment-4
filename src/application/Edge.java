package application;

/**
 * Created by nicholas on 7/25/17.
 */
public class Edge {
    double disance;
    GraphNode end;

    public Edge(double lat1, double long1, double lat2, double long2, GraphNode end){
        this.disance = Haversine.getDistance(lat1,long1,lat2,long2);
        this.end = end;
        //System.out.println(this.disance);
    }
}
