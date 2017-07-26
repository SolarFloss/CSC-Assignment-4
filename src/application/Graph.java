package application;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nicholas on 7/25/17.
 */
public class Graph {



    public Graph() throws Exception {
        ArrayList<Integer> available = new ArrayList<>();
        Restaurants restaurants = new Restaurants();


        for(int i = 1; i < restaurants.size()+1; i++){
            //if(Haversine.getDistance())
            available.add(restaurants.getID(i));
        }

        int i = 1;
        Edge edge;
        GraphNode start = null;
        while(available.size() > 1){



            double lat1 = restaurants.getLatitude(i);
            double long1 = restaurants.getLongitude(i);

            int randomID = available.get((int)Math.floor(Math.random()*available.size()));
            double lat2 = restaurants.getLatitude(randomID);
            double long2 = restaurants.getLongitude(randomID);
            available.remove(available.indexOf(randomID));
            //available.remove(available.indexOf(i));


            start = new GraphNode(i);
            edge = new Edge(lat1,long1,lat2,long2,new GraphNode(randomID));


            start.setEdge(edge);
            //end.setEdge(edge);

            i++;
        }

        System.out.println(start.getEdge().disance);
        while(start.getEdge() != null){
            System.out.println(start.getEdge().disance);
            start = start.getEdge().end;
        }

    }
}
