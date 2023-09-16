package com.example.kursovoy;

import Cheak_ALL.Cheaker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import java.util.Vector;

public class Controller_change_work_admin extends Cheaker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button change;

    @FXML
    private TextField work;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    @FXML
    void initialize() {

        change.setOnAction(actionEvent ->
        {
            try {
                if (proverka()) {
                    socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                    output = new DataOutputStream(socket.getOutputStream());
                    input = new DataInputStream(socket.getInputStream());
                    output.writeInt(work.getText().length());
                    for (int i = 0; i < work.getText().length(); i++) {
                        output.writeChar(work.getText().charAt(i));
                    }
                    int result = input.readInt();
                    if (result == 1) {
                        Stage stage = (Stage) change.getScene().getWindow();
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
                    } else {
                        Stage stage = (Stage) change.getScene().getWindow();
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
                }
            } catch(IOException e){
                    throw new RuntimeException(e);
                }

        });

    }
    public boolean proverka()
    {
        if(work.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректное имя!");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(work.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректное имя!");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean haveNum(String str) {
        boolean hasDigits = false;
        for(int i = 0; i < str.length() && !hasDigits; i++) {
            if(Character.isDigit(str.charAt(i))) {
                hasDigits = true;
            }
        }
        return hasDigits;
    }

    @Override
    public int col_simvol(String str) {
        int col = 0;
        for(int i = 0; i < str.length(); i++) {
            col++;
        }
        return col;
    }

    @Override
    public boolean cheakRepit(Vector<Integer> k, int id) {
        for(int i=0; i<k.size(); i++)
        {
            if(id==k.get(i)) return true;
        }
        return false;
    }

    @Override
    public int col_simvol(int id) {
        String str= String.valueOf(id);
        int col = 0;
        for(int i = 0; i < str.length(); i++) {
            col++;
        }
        return col;
    }

    @Override
    public boolean cheakNUM(String num) {
        try
        {
            if(Float.parseFloat(num)==Float.parseFloat(num));
            return false;
        }
        catch (Exception exception)
        {
            System.out.println("Вы ввели не целое число!");
            return true;
        }
    }
}
