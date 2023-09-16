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

public class Controller_delete {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button delete;

    @FXML
    private TextField id_student;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    @FXML
    void initialize() {
       delete.setOnAction(actionEvent ->
       {
           try {
               socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
               output = new DataOutputStream(socket.getOutputStream());
               input = new DataInputStream(socket.getInputStream());
               output.writeInt(1);
               output.writeInt(Integer.parseInt(id_student.getText()));
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
