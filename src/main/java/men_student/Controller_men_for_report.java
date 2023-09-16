package men_student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller_men_for_report {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_report;

    @FXML
    private Button delete_report;

    @FXML
    private Button exit;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента

    @FXML
    private Button read_report;

    private int id;

    @FXML
    void initialize() {
        exit.setOnAction(actionEvent ->{
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                id = input.readInt();
                output.writeInt(4);
                root = FXMLLoader.load(getClass().getResource("/men_student/men_for_student.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
            try {
                socket.close();
                output.close();
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        add_report.setOnAction(actionEvent ->{
            Stage stage = (Stage) add_report.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                id = input.readInt();
                output.writeInt(1);
                root = FXMLLoader.load(getClass().getResource("/men_student/reg_request.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
            try {
                socket.close();
                output.close();
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        read_report.setOnAction(actionEvent ->{
            Stage stage = (Stage) read_report.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                id = input.readInt();

                output.writeInt(2);
                socket.close();
                output.close();
                input.close();
                root = FXMLLoader.load(getClass().getResource("/men_student/read_resonal_request.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        delete_report.setOnAction(actionEvent ->{
            Stage stage = (Stage) delete_report.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                id = input.readInt();
                output.writeInt(3);
                socket.close();
                input.close();
                output.close();
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
               int ifer = input.readInt();

               if(ifer == 1)
               {
                   root = FXMLLoader.load(getClass().getResource("/men_student/yes2.fxml"));
               }
               else
               {
                   root = FXMLLoader.load(getClass().getResource("/men_student/no1.fxml"));
               }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
            try {
                socket.close();
                output.close();
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
