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

public class Controller_sort_for_step_men {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button amount;

    @FXML
    private Button coef;

    @FXML
    private Button down_amount;

    @FXML
    private Button down_coef;

    @FXML
    private Button exit;

    @FXML
    private Button up_amount;

    @FXML
    private Button up_coef;
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
                output.writeInt(7);
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
        amount.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) amount.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_amount.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        coef.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) coef.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_coeff.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        up_coef.setOnAction(actionEvent ->
        {
            Socket socket = null;



            Stage stage = (Stage) up_coef.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_up_inputCoeff.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        down_coef.setOnAction(actionEvent ->
        {
            Socket socket = null;



            Stage stage = (Stage) down_coef.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_down_inputCoeff.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        up_amount.setOnAction(actionEvent ->
        {

            Stage stage = (Stage) up_amount.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_up_inpuAmount.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        down_amount.setOnAction(actionEvent ->
        {



            Stage stage = (Stage) down_amount.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/filters/sort_down_inputAmount.fxml"));
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
