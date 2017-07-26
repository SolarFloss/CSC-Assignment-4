package application;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 **/
public class Location {

    private String address;
    private String ip;
    private String region;
    private Double latitude,longitude;

    public Location(String address,String region) throws IOException {
        this.address = address;
        StringBuilder result;
        BufferedReader reader;
        JSONObject jsonObject;

        URL ipRequest = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(ipRequest.openStream()));
        ip = in.readLine();



        String regionSite = "http://ip-api.com/json/" + ip;
        URL regionURL = new URL(regionSite);
        HttpURLConnection regionConnection = (HttpURLConnection) regionURL.openConnection();
        regionConnection.setRequestMethod("GET");
        reader = new BufferedReader(new InputStreamReader(regionConnection.getInputStream()));
        String line;
        result = new StringBuilder();
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();


        jsonObject = new JSONObject(result.toString());
        region = jsonObject.get("region").toString();



        String googleKey = "AIzaSyBdffLuBloabj5LhoboDNWqP-vXQL6Pwno";
        String site = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                address + "," + region +
                "&key=" + googleKey;


        site = site.replaceAll("\\s+","+");
        result = new StringBuilder();
        URL url = new URL(site);
        HttpURLConnection locationRegion = (HttpURLConnection) url.openConnection();
        locationRegion.setRequestMethod("GET");
        reader = new BufferedReader(new InputStreamReader(locationRegion.getInputStream()));
        line = "";
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();
        jsonObject = new JSONObject(result.toString());

        JSONObject locationData = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        this.latitude = Double.parseDouble(locationData.get("lat").toString());
        this.longitude = Double.parseDouble(locationData.get("lng").toString());
    }

    public Location(String address)throws IOException{
        this.address = address;
        StringBuilder result;
        BufferedReader reader;
        JSONObject jsonObject;

        URL ipRequest = new URL("http://checkip.amazonaws.com");
        BufferedReader in = new BufferedReader(new InputStreamReader(ipRequest.openStream()));
        ip = in.readLine();



        String regionSite = "http://ip-api.com/json/" + ip;
        URL regionURL = new URL(regionSite);
        HttpURLConnection regionConnection = (HttpURLConnection) regionURL.openConnection();
        regionConnection.setRequestMethod("GET");
        reader = new BufferedReader(new InputStreamReader(regionConnection.getInputStream()));
        String line;
        result = new StringBuilder();
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();


        jsonObject = new JSONObject(result.toString());
        this.region = jsonObject.get("region").toString();



        String googleKey = "AIzaSyBdffLuBloabj5LhoboDNWqP-vXQL6Pwno";
        String site = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                address + "," + region +
                "&key=" + googleKey;


        site = site.replaceAll("\\s+","+");
        result = new StringBuilder();
        URL url = new URL(site);
        HttpURLConnection locationRegion = (HttpURLConnection) url.openConnection();
        locationRegion.setRequestMethod("GET");
        reader = new BufferedReader(new InputStreamReader(locationRegion.getInputStream()));
        line = "";
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();
        jsonObject = new JSONObject(result.toString());

        JSONObject locationData = jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        this.latitude = Double.parseDouble(locationData.get("lat").toString());
        this.longitude = Double.parseDouble(locationData.get("lng").toString());
    }

    public Double getLatitude(){
        return this.latitude;
    }

    public Double getLongitude(){
        return this.longitude;
    }

    //public String getRegion(){ return this.region; }

    public String getIP(){ return this.ip; }
}
