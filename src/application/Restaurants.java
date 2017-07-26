/*
* I took it a bit further and made it much easier to get the data
* Now all you have to do is create a new Restaurants class and then use the functions to get what you want. Ex(Inside of main class):
* Restaurants restuarant = new Restaurants();
* //The parameter for all the functions is the ID of the restaurant
* //I am using ID 24 in this case
*
* System.out.println(restaurant.getName(24));         //returns Fuji Sushi
* System.out.println(restaurant.getZipCode(24));      //returns 63052
* System.out.println(restuarant.getPhoneNumber(24));  //returns 6364648889
*
*/

package application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Restaurants {
    private JSONArray data;
    private int length = 0;
    public Restaurants() throws Exception{
        String site = "https://script.google.com/macros/s/AKfycbz5h3Sdh_M6jjLLpR_NwFoJNCxzb_d3vQozYkqnX-CptWK17kpF/exec?id=1xAnPy-WfQDg9KIuwIaQ7LIfkaVl2anXUXnfNOa1vDlo&sheet=Sheet1";
        StringBuilder result = new StringBuilder();
        URL url = new URL(site);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        reader.close();


        JSONObject jsonObject = new JSONObject(result.toString());
        data = jsonObject.getJSONArray("Sheet1");


        //Get the size
        int i = 0;
        while(true){
            try{
                if(!data.getJSONObject(i).get("RESTAURANT_NAME").toString().equals("")) {
                    length++;
                    i++;
                }
            }catch(Exception e){
                break;
            }
        }
    }

    public String getName(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_NAME").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public int getID(int ID){
        try {
            return Integer.parseInt(data.getJSONObject(ID-1).get("ID").toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public String getPhoneNumber(int ID){
        try {
            return data.getJSONObject(ID-1).get("_Phone_Number").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getZipCode(int ID){
        try {
            return data.getJSONObject(ID-1).get("ZIP_CODE").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getAddress(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_ADDRESS").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getState(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_STATE").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getCity(int ID){
        try {
            return data.getJSONObject(ID-1).get("RESTAURANT_CITY").toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public float getLatitude(int ID){
        try {
            return Float.parseFloat(data.getJSONObject(ID-1).get("LATITUDE").toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public float getLongitude(int ID){
        try {
            return Float.parseFloat(data.getJSONObject(ID-1).get("LONGITUDE").toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return -1;
    }

    public URL getImage(int ID){
        try {
            return new URL(data.getJSONObject(ID-1).get("URL_IMAGE").toString());
        } catch (JSONException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public int size(){
        return length;
    }
}