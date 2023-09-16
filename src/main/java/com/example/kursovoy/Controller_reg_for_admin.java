package com.example.kursovoy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Vector;

import Cheak_ALL.Cheaker;
import autoriz.Admin;
import data.Info_Admin;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller_reg_for_admin extends Cheaker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea Key;

    @FXML
    private Button add;

    @FXML
    private Button exit;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField patronimyc;

    @FXML
    private TextField surname;

    @FXML
    private TextField work;
    Socket socket;
    DataOutputStream output;
    DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    private static int contoller=1;

    @FXML
    void initialize() throws IOException {
        socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
        output.writeInt(3);
        int flag=1;
        int key =0;
        do {
            key = (int) (Math.random()*1000);
            output.writeInt(key);
            flag=input.readInt();
        }while(flag!=1);
        Key.setText(String.valueOf(key));
        exit.setOnAction(actionEvent ->
        {
            if(contoller==0) {
                Stage stage = (Stage) exit.getScene().getWindow();
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
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Для начала завершите операцию!");
                alert.showAndWait();
            }
        });
        add.setOnAction(actionEvent ->
        {
            if(proverka()) {
                Admin ad = new Admin();
                Info_Admin info_admin = new Info_Admin();
                ad.setKey(Integer.parseInt(Key.getText()));
                ad.setLogin(login.getText());
                ad.setPassword(password.getText());
                info_admin.setKey(Integer.parseInt(Key.getText()));
                info_admin.setSurname(surname.getText());
                info_admin.setName(name.getText());
                info_admin.setPatronymic(patronimyc.getText());
                info_admin.setWork(work.getText());
                try {
                    output.writeInt(ad.getKey());
                    output.writeInt(ad.getLogin().length());
                    for (int i = 0; i < ad.getLogin().length(); i++) {
                        output.writeChar(ad.getLogin().charAt(i));
                    }
                    output.writeInt(ad.getPassword().length());
                    for (int i = 0; i < ad.getPassword().length(); i++) {
                        output.writeChar(ad.getPassword().charAt(i));
                    }
                    output.writeInt(info_admin.getSurname().length());
                    for (int i = 0; i < info_admin.getSurname().length(); i++) {
                        output.writeChar(info_admin.getSurname().charAt(i));
                    }
                    output.writeInt(info_admin.getName().length());
                    for (int i = 0; i < info_admin.getName().length(); i++) {
                        output.writeChar(info_admin.getName().charAt(i));
                    }
                    output.writeInt(info_admin.getPatronymic().length());
                    for (int i = 0; i < info_admin.getPatronymic().length(); i++) {
                        output.writeChar(info_admin.getPatronymic().charAt(i));
                    }
                    output.writeInt(info_admin.getWork().length());
                    for (int i = 0; i < info_admin.getWork().length(); i++) {
                        output.writeChar(info_admin.getWork().charAt(i));
                    }

                        socket.close();
                        output.close();
                        input.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно!");
                    alert.setHeaderText(null);
                    alert.setContentText("Запись успешно добавлена");
                    alert.showAndWait();
                    contoller=0;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }


        });
    }
    public boolean proverka()
    {

        if(login.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("В логине должно быть больше 4 символов!.");
            alert.showAndWait();
            return false;
        }
        if(name.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректное имя!");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(name.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректное имя!");
                alert.showAndWait();
                return false;
            }
        }
        if(password.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("В пароле должно быть больше 4 символов!.");
            alert.showAndWait();
            return false;
        }
        if(patronimyc.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректное отчество!");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(patronimyc.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректное отчество!");
                alert.showAndWait();
                return false;
            }
        }
        if(surname.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректную фамилию!.");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(surname.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректную фамилию!.");
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
