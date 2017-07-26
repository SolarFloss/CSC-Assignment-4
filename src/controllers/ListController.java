package controllers;

import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Created by nicholas on 7/19/17.
 */
public class ListController {
    public HBox hMain;

    public void itemClicked(MouseEvent mouseEvent) {
        hMain.setStyle("-fx-background-color: #4775c1");
    }

    public void mouseExited(MouseEvent mouseEvent) {
        hMain.setStyle("-fx-background-color: #fff");
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        hMain.setStyle("-fx-background-color: #70a6ff");
    }
}
