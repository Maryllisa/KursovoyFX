package men_student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_sort_student_men {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button down_missing;

    @FXML
    private Button down_rating;

    @FXML
    private Button exit;

    @FXML
    private Button group;

    @FXML
    private Button id_student;

    @FXML
    private Button missing;

    @FXML
    private Button name;

    @FXML
    private Button patronymic;

    @FXML
    private Button rating;

    @FXML
    private Button surname;

    @FXML
    private Button up_missing;

    @FXML
    private Button up_rating;

    private Stage stage;
    private Scene scene;
    private Parent root;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента

    @FXML
    void initialize() {
        exit.setOnAction(actionEvent ->
        {
            Socket socket = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());
                output.writeInt(16);
                socket.close();
                output.close();
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) exit.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/men_for_student.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        id_student.setOnAction(actionEvent ->
        {
            Socket socket = null;

            Stage stage = (Stage) id_student.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_id_student.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        surname.setOnAction(actionEvent ->
        {
            Socket socket = null;

            Stage stage = (Stage) surname.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_surname.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        name.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) name.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_name.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        patronymic.setOnAction(actionEvent ->
        {
            Socket socket = null;


            Stage stage = (Stage) patronymic.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_patronymic.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        group.setOnAction(actionEvent ->
        {

            Stage stage = (Stage) group.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_group.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });

        missing.setOnAction(actionEvent ->
        {


            Stage stage = (Stage) missing.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_missing.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        rating.setOnAction(actionEvent ->
        {

            Stage stage = (Stage) rating.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_rating.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        up_rating.setOnAction(actionEvent ->
        {


            Stage stage = (Stage) up_rating.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_up_inputRating.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        down_rating.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) down_rating.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_down_inputRating.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        up_missing.setOnAction(actionEvent ->
        {
            Socket socket = null;

            Stage stage = (Stage) up_missing.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_up_inputMissing.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        down_missing.setOnAction(actionEvent ->
        {
            Socket socket = null;
            Stage stage = (Stage) down_missing.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_down_inputMissing.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });


    }

}
