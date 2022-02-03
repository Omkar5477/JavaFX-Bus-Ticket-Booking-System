package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.connectivity.ConnectionClass;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Adminlogin implements Initializable {
    public PasswordField passwordField;
    public TextField textField;
    public Button button;
    public Hyperlink back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void adminlogin(ActionEvent actionEvent) throws SQLException {

        String uname = textField.getText();
        String pwd = passwordField.getText();
        if (uname.equals("") && pwd.equals("")) {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText("Wrong username or password!");
            a.show();
        } else {
            try {
                ConnectionClass connectionClass = new ConnectionClass();
                Connection connection = connectionClass.getConnection();
                PreparedStatement pst = connection.prepareStatement("select * from admin where username=? and password=?");
                pst.setString(1, uname);
                pst.setString(2, pwd);
                ResultSet resultSet = pst.executeQuery();
                if (resultSet.next()) {
                    Parent parent = FXMLLoader.load(getClass().getResource("admin.fxml"));
                    Scene scene = new Scene(parent);
                    Stage window = (Stage)((Node) actionEvent.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void back(ActionEvent actionEvent) {

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