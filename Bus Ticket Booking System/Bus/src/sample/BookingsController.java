package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.connectivity.ConnectionClass;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookingsController  implements Initializable {
    @FXML
    private TableView <ViewBookings> table;
    @FXML
    private TableColumn <ViewBookings, String> name;
    @FXML
    public TableColumn<ViewBookings,Integer> phone;
    @FXML
    public TableColumn <ViewBookings,String>source;
    @FXML
    public TableColumn<ViewBookings,String> destination;
    @FXML
    public TableColumn <ViewBookings,String>service;
    @FXML
    public TableColumn <ViewBookings, Time>depart;
    @FXML
    public TableColumn <ViewBookings,Time> arrival;
    @FXML
    public TableColumn <ViewBookings,String>date;
    @FXML
    public TableColumn <ViewBookings,Integer>seats;
    @FXML
    public TableColumn <ViewBookings,Integer> amount;
    @FXML
    public Button back;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        load();

    }
    private void initCol(){
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        source.setCellValueFactory(new PropertyValueFactory<>("source"));
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        service.setCellValueFactory(new PropertyValueFactory<>("service"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        seats.setCellValueFactory(new PropertyValueFactory<>("seats"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
    private void load(){
        ObservableList<ViewBookings> list = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        try {
            pst=connection.prepareStatement("select * from bookings");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                list.add(new ViewBookings(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8)));

                table.setItems(list);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addbus(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("AddBus.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {

        try {
            Parent parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

}
