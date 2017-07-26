package controllers;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by nicholas on 7/18/17.
 */
public class MainController {

    public static TextField txtAddress;
    public static ComboBox comboRadius;
    public static Button btnGo;
    private static Scene scene;
    private static Stage stage;
    private static Parent listItem;
    public static VBox vList;
    public static ScrollPane scrollContainer;
    private URL resource;
    private static Restaurants restaurants;
    private Location locationData;
    private int radius = Integer.MAX_VALUE;

    public MainController(){
        scene = Main.getScene();
        stage = Main.getStage();
        restaurants = Main.getData();

        if(scene != null) {
            try {
                //listItem = FXMLLoader.load(getClass().getResource("/fxml/listItem.fxml"));


                txtAddress = (TextField)scene.lookup("#txtAddress");
                comboRadius = (ComboBox)scene.lookup("#comboRadius");
                btnGo = (Button)scene.lookup("#btnGo");
                vList = (VBox)scene.lookup("#vList");
                scrollContainer = (ScrollPane)scene.lookup("#scrollContainer");
                scrollContainer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

                ObservableList<Integer> radiusOptions = FXCollections.observableArrayList(1,5,10,15,20,25,50,100);
                comboRadius.setItems(radiusOptions);







                Label lblDistance;
                HBox hMain;
                Label lblName;
                Label lblAddress;

                for(int i = 1; i < restaurants.size()+1; i++) {
                    Parent newList = FXMLLoader.load(getClass().getResource("/fxml/listItem.fxml"));

                    lblDistance = (Label)newList.lookup("#lblDistance");
                    hMain = (HBox)newList.lookup("#hMain");
                    lblName = (Label)newList.lookup("#lblName");
                    lblAddress = (Label)newList.lookup("#lblAddress");


                    lblName.setText(restaurants.getName(i));
                    lblAddress.setText(restaurants.getAddress(i));
                    hMain.setPrefWidth(vList.getPrefWidth()-15);
                    lblDistance.setText("");
                    vList.getChildren().add(newList);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void drawList(){
        Label lblDistance;
        HBox hMain;
        Label lblName;
        Label lblAddress;
        //System.out.println(Haversine.getDistance(locationData.getLatitude(),locationData.getLongitude(),47.70855,-122.200968));
        //System.out.println(locationData.getLatitude() + "," + locationData.getLongitude());
        MinHeap heap = new MinHeap(restaurants.size()+1);
        OrderedList list = new OrderedList();

        //Create list
        for(int i = 1; i < restaurants.size()+1; i++){
            Double distance = (Haversine.getDistance(locationData.getLatitude(), locationData.getLongitude(), (double) restaurants.getLatitude(i), (double) restaurants.getLongitude(i)));
            DecimalFormat formatter = new DecimalFormat("#0.00");
            if(!distance.isNaN()) {
                distance = Double.parseDouble(formatter.format(distance));
                //System.out.println(distance);
                list.add(distance,i);
            }
        }
        vList.getChildren().clear();



        for(int i = 0; i < list.size(); i++){
            //distance is value
            double value = list.get(i).getValue();
            if(value < radius){
                Parent newList = null;
                try {
                    newList = FXMLLoader.load(getClass().getResource("/fxml/listItem.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int id = list.get(i).getID();

                lblDistance = (Label) newList.lookup("#lblDistance");
                hMain = (HBox) newList.lookup("#hMain");
                lblName = (Label) newList.lookup("#lblName");
                lblAddress = (Label) newList.lookup("#lblAddress");


                lblName.setText(restaurants.getName(id));
                lblAddress.setText(restaurants.getAddress(id));
                hMain.setPrefWidth(vList.getWidth());

                lblDistance.setText(value + " mi.");
                vList.getChildren().add(newList);
            }
        }
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName().equals("Enter")){
            getData();
        }
    }


    private void getData(){
        try {
            if(comboRadius.getSelectionModel().getSelectedItem() != null)
                radius = Integer.parseInt(comboRadius.getSelectionModel().getSelectedItem().toString());
            else
                radius = Integer.MAX_VALUE;


            if(txtAddress.getText().contains(","))
                locationData = new Location(txtAddress.getText(),txtAddress.getText().substring(txtAddress.getText().indexOf(",")));
            else
                locationData = new Location(txtAddress.getText());

            drawList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnClicked(MouseEvent mouseEvent) {
        getData();
    }
}
