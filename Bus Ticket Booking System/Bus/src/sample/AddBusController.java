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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.connectivity.ConnectionClass;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddBusController implements Initializable {
    public TextField service,source,destination,fare,depart,arrival,totalseat;
    public Button add;
    public DatePicker datepicker;


    @FXML
    private TableView<AddBusTable> table;
    @FXML
    private TableColumn<AddBusTable,String>service1;
    @FXML
    private TableColumn<AddBusTable,String>source1;
    @FXML
    private TableColumn<AddBusTable,String>destination1;
    @FXML
    private TableColumn<AddBusTable,Integer>fare1;
    @FXML
    private TableColumn<AddBusTable,Time> depart1;
    @FXML
    private TableColumn<AddBusTable, Time>arrival1;
    @FXML
    private TableColumn<AddBusTable,Integer>seat1;
    @FXML
    private TableColumn<AddBusTable,String>date1;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        service1.setCellValueFactory(new PropertyValueFactory<>("service1"));
        source1.setCellValueFactory(new PropertyValueFactory<>("source1"));
        destination1.setCellValueFactory(new PropertyValueFactory<>("destination1"));
        fare1.setCellValueFactory(new PropertyValueFactory<>("fare1"));
        depart1.setCellValueFactory(new PropertyValueFactory<>("depart1"));
        arrival1.setCellValueFactory(new PropertyValueFactory<>("arrival1"));
        seat1.setCellValueFactory(new PropertyValueFactory<>("seat1"));
        date1.setCellValueFactory(new PropertyValueFactory<>("date1"));

    }

    public void add(ActionEvent actionEvent) {

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        try {
            String departure = depart.getText();
            String arrive = arrival.getText();
            String ser = service.getText();
            String start = source.getText();
            String end = destination.getText();
            String amt = fare.getText();
            String tseat = totalseat.getText();
            String dat = ((LocalDate)this.datepicker.getValue()).format(DateTimeFormatter.ISO_LOCAL_DATE);
            PreparedStatement ps =connection.prepareStatement("Insert into search values('"+ser+"','"+start+"','"+end+"','"+amt+"','"+departure+"','"+arrive+"','"+tseat+"','"+dat+"')");

            int count =ps.executeUpdate();
            if (count > 0){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Saved");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){


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
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void viewbuses(ActionEvent actionEvent) {
        ObservableList<AddBusTable>  list = FXCollections.observableArrayList();
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        try {
            PreparedStatement ps =connection.prepareStatement("select * from search");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                 list.add(new AddBusTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getTime(5),rs.getTime(6),rs.getInt(7),rs.getString(8)));
                 table.setItems(list);

            }
        } catch (SQLException t) {
            t.printStackTrace();
        }


    }

    public void back(ActionEvent actionEvent) {

        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } catch (IOException e) {

        }
    }

    public void cancel(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you sure?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){


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
    }
}
