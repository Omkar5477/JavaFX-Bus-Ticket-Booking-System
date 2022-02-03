package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.connectivity.ConnectionClass;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Mybooking implements Initializable{

    @FXML
    private TableColumn<?, ?> amount;

    @FXML
    private Hyperlink back;

    @FXML
    private TableColumn<?, ?> date;

    @FXML
    private Hyperlink delete;

    @FXML
    private TableColumn<?, ?> destination;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> phone;

    @FXML
    private TableColumn<?, ?> seats;

    @FXML
    private TableColumn<?, ?> service;

    @FXML
    private TableColumn<?, ?> source;

    @FXML
    private TableView<ViewBookings_user> table;





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
        ObservableList<ViewBookings_user> list12 = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        PreparedStatement pst;
        try {
            pst=connection.prepareStatement("select s.service,sum(st.count),date,s.source,s.dest,s.dtime,s.atime,st.uname from search s inner join seats st on s.service=st.service where count = 1 GROUP by s.service");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                list12.add(new ViewBookings_user(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)));

                table.setItems(list12);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    void back_(ActionEvent event) {
        try {


            Parent parent = FXMLLoader.load(getClass().getResource("book.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    void delete(ActionEvent event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
