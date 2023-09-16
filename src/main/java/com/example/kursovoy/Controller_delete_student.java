package com.example.kursovoy;

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

public class Controller_delete_student {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delete;
    @FXML
    private Button exit;

    @FXML
    private TextField id_student;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента

    @FXML
    void initialize() {
        exit.setOnAction(actionEvent ->{

            Stage stage = (Stage) exit.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
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
        delete.setOnAction(actionEvent ->
        {
            try {
                //socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                //output = new DataOutputStream(socket.getOutputStream());
                //input = new DataInputStream(socket.getInputStream());
                //output.writeInt(Integer.parseInt(id_student.getText()));
                //output.writeInt(3);
                int result = input.readInt();
                if(result==1)
                {
                    Stage stage = (Stage) delete.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/work/yes.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.show();
                }
                else
                {
                    Stage stage = (Stage) delete.getScene().getWindow();
                    // do what you have to do
                    stage.close();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/work/no.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.show();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }

}
