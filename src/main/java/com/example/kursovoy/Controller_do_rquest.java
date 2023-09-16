package com.example.kursovoy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import Cheak_ALL.Cheaker;
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

public class Controller_do_rquest extends Cheaker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private TextField coef_1;

    @FXML
    private TextField coef_2;

    @FXML
    private TextField col_passes;

    @FXML
    private Button exit;

    @FXML
    private TextField id_student;

    @FXML
    private TextField rating;
    @FXML
    private TextArea summa;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    @FXML
    void initialize() {

        add.setOnAction(actionEvent ->
        {

            Socket socket = null;
            try {
                if(proverka()) {
                    int id = Integer.parseInt(id_student.getText());
                    int col_pas = Integer.parseInt(col_passes.getText());
                    float rat = Float.parseFloat(rating.getText());
                    float coef = Float.parseFloat(coef_1.getText())+Float.parseFloat(coef_2.getText());
                    socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    output.writeInt(8);
                    output.writeInt(id);
                    output.writeInt(col_pas);
                    output.writeFloat(rat);
                    output.writeFloat(coef);
                    float summal = input.readFloat();
                    summa.setText(String.valueOf(summal));
                    socket.close();
                    output.close();
                    input.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        exit.setOnAction(actionEvent ->{

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

        });

    }

    public boolean proverka()
    {
        if(id_student.getText() == "" && coef_1.getText() == "" && coef_2.getText()=="" && col_passes.getText() =="")
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Все поля должны быть заполнены.");
            alert.showAndWait();
            return false;
        }
        if(id_student.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректный ID.");
            alert.showAndWait();
            return false;
        }
        if(coef_1.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректный коэффицент университета.");
            alert.showAndWait();
            return false;
        }
        else
        {
            if(cheakNUM(coef_1.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректный коэффицент университета.");
                alert.showAndWait();
                return false;
            }
            if(Float.parseFloat(coef_1.getText())<0 && Float.parseFloat(coef_1.getText())>10)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректный коэффицент университета.");
                alert.showAndWait();
                return false;
            }
        }
        if(coef_2.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажитк корректный коэффицент специальности.");
            alert.showAndWait();
            return false;
        }
        else
        {
            if(cheakNUM(coef_2.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажитк корректный коэффицент специальности.");
                alert.showAndWait();
                return false;
            }
            if(Float.parseFloat(coef_2.getText())<0 && Float.parseFloat(coef_2.getText())>10)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажитк корректный коэффицент специальности.");
                alert.showAndWait();
                return false;
            }
        }
        if(col_passes.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажитк корректное количество пропусков");
            alert.showAndWait();
            return false;
        }
        else
        {
            if(cheakNUM(col_passes.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажитк корректное количество пропусков");
                alert.showAndWait();
                return false;
            }
            if(Float.parseFloat(col_passes.getText())<0 && Float.parseFloat(col_passes.getText())>1000)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажитк корректное количество пропусков");
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
