package com.example.kursovoy;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import java.util.Vector;

import Cheak_ALL.Cheaker;
import animat.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
        import javafx.scene.control.PasswordField;
        import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller_auto_admin extends Cheaker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button input_auto;

    @FXML
    private PasswordField key_admin;

    @FXML
    private TextField login_admin;

    @FXML
    private PasswordField password_admin;


    @FXML
    void initialize() {


        input_auto.setOnAction(actionEvent ->
        {
            String login =login_admin.getText();
            String password =password_admin.getText();
            String key = key_admin.getText();
            int flag=0;
            if(login.trim().length() != 0 && password.trim().length() != 0 && key.trim().length() != 0)
            {
                int key_int = Integer.valueOf(key);
                TextField tf = new TextField("127.0.0.1");//ip adress клиента
                TextField tf1 = new TextField("1024");// port клиента

                Socket socket = null;
                try {
                    if(col_simvol(login) <4)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("В логине должно быть больше 4 символов!.");
                        alert.showAndWait();
                        flag++;
                    }
                    if(col_simvol(password) <4)
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("В пароле должно быть больше 4 символов!.");
                        alert.showAndWait();
                        flag++;
                    }

                    if (flag == 0) {
                        socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                        DataInputStream input = new DataInputStream(socket.getInputStream());
                        output.writeInt(1);
                        output.writeInt(login.length());
                        for (int i = 0; i < login.length(); i++) {
                            output.writeChar(login.charAt(i));
                        }
                        output.writeInt(password.length());
                        for (int i = 0; i < password.length(); i++) {
                            output.writeChar(password.charAt(i));
                        }
                        output.writeInt(key_int);
                        int report = input.readInt();
                        if (report == 1) {
                            Stage stage = (Stage) input_auto.getScene().getWindow();
                            // do what you have to do
                            stage.close();
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("men_for_ad.fxml"));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setScene(new Scene(root));
                            stage.show();

                        } else {
                            Shake animl = new Shake(login_admin);
                            Shake animp = new Shake(password_admin);
                            Shake animk = new Shake(key_admin);
                            animl.playAnim();
                            animp.playAnim();
                            animk.playAnim();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Ошибка!");
                            alert.setHeaderText(null);
                            alert.setContentText("Неправильный логин или пароль!.");
                            alert.showAndWait();
                        }
                        socket.close();
                        output.close();
                        input.close();


                    }
                }
                    catch (Exception e) {
                        throw new RuntimeException(e);
            }
            }
            else
            {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Надо заполнить все поля.");
                alert.showAndWait();

            }
        });

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





