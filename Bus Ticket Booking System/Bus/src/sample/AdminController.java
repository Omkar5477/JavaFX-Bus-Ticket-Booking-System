package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public Button addbus;
    public Button viewbookings;
    @FXML
    private ImageView q;
    @FXML
    private Button buses;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        

    }

    public void addbus(ActionEvent actionEvent) {
        addbus.setStyle("-fx-background-color:  #0598ff");


        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("AddBus.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    public void viewbookings(ActionEvent actionEvent) {
        {

            try {
                Parent parent = FXMLLoader.load(getClass().getResource("ViewBookings.fxml"));
                Scene scene = new Scene(parent);
                Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            } catch (IOException e) {

            }
        }

    }

    public void logout(ActionEvent actionEvent) {

        try {
           Parent parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {

        }
    }
}
