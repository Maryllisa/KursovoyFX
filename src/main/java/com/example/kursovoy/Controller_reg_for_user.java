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
import autoriz.User;
import data.Applications;
import data.Group;
import data.Student;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller_reg_for_user extends Cheaker {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField curs;

    @FXML
    private Button exit;

    @FXML
    private TextField facult;

    @FXML
    private TextField id_group;

    @FXML
    private TextField id_student;

    @FXML
    private TextField login;

    @FXML
    private TextField name;

    @FXML
    private CheckBox namme;
    @FXML
    private CheckBox VUZ;
    @FXML
    private CheckBox net;

    @FXML
    private TextField password;

    @FXML
    private TextField patronymic;

    @FXML
    private CheckBox prizend;

    @FXML
    private TextField spec;

    @FXML
    private TextField surname;

    @FXML
    private TextField day;

    @FXML
    private TextField monthe;

    @FXML
    private TextField year;

    @FXML
    private Button add;

    private Stage stage;
    private Scene scene;
    private Parent root;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    Socket socket;
    DataOutputStream output;
    DataInputStream input;
    private  static int contoller =1;
    @FXML
    void initialize() throws IOException {


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
            else {
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
                try {
                    socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                    output = new DataOutputStream(socket.getOutputStream());
                    input = new DataInputStream(socket.getInputStream());
                    output.writeInt(2);
                    output.writeInt(Integer.parseInt(id_student.getText()));
                    int choose = input.readInt();
                    if(choose==1) {
                        Student student = new Student();
                        student.setId_student(Integer.parseInt(id_student.getText()));
                        student.setSurname(surname.getText());
                        student.setName(name.getText());
                        student.setPatronymic(patronymic.getText());
                        student.setDay(Integer.parseInt(day.getText()));
                        student.setMonthe(Integer.parseInt(monthe.getText()));
                        student.setYear(Integer.parseInt(year.getText()));
                        student.setId_group(Integer.parseInt(id_group.getText()));
                        Group group = new Group();
                        group.setNumber_group(Integer.parseInt(id_group.getText()));
                        group.setCours(Integer.parseInt(curs.getText()));
                        group.setFaculty(facult.getText());
                        group.setSpeciality(spec.getText());
                        Applications applications = new Applications();
                        if (prizend.isSelected()) {
                            applications.setName_scholarsip("1");
                            student.setId_applications("1");
                        }
                        if (namme.isSelected()) {
                            applications.setName_scholarsip("2");
                            student.setId_applications("2");
                        }
                        if (prizend.isSelected() && namme.isSelected()) {
                            applications.setName_scholarsip("3");
                            student.setId_applications("3");
                        }
                        if (VUZ.isSelected()) {
                            applications.setName_scholarsip("4");
                            student.setId_applications("4");

                        }
                        if (prizend.isSelected() && namme.isSelected() && VUZ.isSelected()) {
                            applications.setName_scholarsip("5");
                            student.setId_applications("5");


                        }
                        if (namme.isSelected() && VUZ.isSelected()) {
                            applications.setName_scholarsip("6");
                            student.setId_applications("6");

                        }
                        if (prizend.isSelected() && VUZ.isSelected()) {
                            applications.setName_scholarsip("7");
                            ;
                            student.setId_applications("7");
                        }
                        if (net.isSelected()) {
                            applications.setName_scholarsip("0");
                            student.setId_applications("0");
                        }
                        User us = new User();
                        us.setId_student(Integer.parseInt(id_student.getText()));
                        us.setLogin(login.getText());
                        us.setPassword(password.getText());

                        output.writeInt(student.getId_student());
                        output.writeInt(student.getSurname().length());
                        for (int i = 0; i < student.getSurname().length(); i++) {
                            output.writeChar(student.getSurname().charAt(i));
                        }
                        output.writeInt(student.getName().length());
                        for (int i = 0; i < student.getName().length(); i++) {
                            output.writeChar(student.getName().charAt(i));
                        }
                        output.writeInt(student.getPatronymic().length());
                        for (int i = 0; i < student.getPatronymic().length(); i++) {
                            output.writeChar(student.getPatronymic().charAt(i));
                        }
                        output.writeInt(student.getId_group());
                        output.writeInt(student.getId_applications().length());
                        for (int i = 0; i < student.getId_applications().length(); i++) {
                            output.writeChar(student.getId_applications().charAt(i));
                        }
                        output.writeInt(student.getDay());
                        output.writeInt(student.getMonthe());
                        output.writeInt(student.getYear());

                        output.writeInt(applications.getName_scholarsip().length());
                        for (int i = 0; i < applications.getName_scholarsip().length(); i++) {
                            output.writeChar(applications.getName_scholarsip().charAt(i));
                        }
                        output.writeInt(group.getCours());

                        output.writeInt(group.getFaculty().length());
                        for (int i = 0; i < group.getFaculty().length(); i++) {
                            output.writeChar(group.getFaculty().charAt(i));
                        }
                        output.writeInt(group.getSpeciality().length());
                        for (int i = 0; i < group.getSpeciality().length(); i++) {
                            output.writeChar(group.getSpeciality().charAt(i));
                        }

                        output.writeInt(login.getText().length());
                        for (int i = 0; i < login.getText().length(); i++) {
                            output.writeChar(login.getText().charAt(i));
                        }
                        output.writeInt(password.getText().length());
                        for (int i = 0; i < password.getText().length(); i++) {
                            output.writeChar(password.getText().charAt(i));
                        }
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Успешно!");
                        alert.setHeaderText(null);
                        alert.setContentText("Запись успешно добавлена");
                        alert.showAndWait();
                        contoller=0;
                    }
                    else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Ошибка!");
                        alert.setHeaderText(null);
                        alert.setContentText("Cтудент под таким id уже есть!");
                        alert.showAndWait();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    socket.close();
                    input.close();
                    output.close();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            }
        });


    }
    public boolean proverka()
    {
        if(curs.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректный курс одной цифрой!!.");
            alert.showAndWait();
            return false;
        }
        else
        {
            if(curs.getText().length()>1 || curs.getText().length()>6 ||curs.getText().length()<0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректный курс одной цифрой!!.");
                alert.showAndWait();
                return false;
            }
        }
        if(facult.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажитк корректный факультет.");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(facult.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректный факультет.");
                alert.showAndWait();
                return false;
            }
        }
        if(id_group.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректный номер группы.");
            alert.showAndWait();
            return false;
        }
        if(cheakNUM(id_student.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректный ID.");
            alert.showAndWait();
            return false;
        }
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
        if(patronymic.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректное отчество!");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(patronymic.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректное отчество!");
                alert.showAndWait();
                return false;
            }
        }
        if(spec.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите корректную cпециальность!");
            alert.showAndWait();
            return false;
        }
        else {
            if (haveNum(spec.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите корректную cпециальность!");
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
        if(day.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите день рождения!");
            alert.showAndWait();
            return false;
        }
        else {
            if (cheakNUM(day.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите день рождения!.");
                alert.showAndWait();
                return false;
            } else {
                int d = Integer.parseInt(day.getText());
                if (d < 1 || d > 31) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Укажите корректный день рождения!.");
                    alert.showAndWait();
                    return false;
                }
            }
        }
        if(monthe.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите месяц рождения!.");
            alert.showAndWait();
            return false;
        }
        else {
            if (cheakNUM(monthe.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите месяц рождения!.");
                alert.showAndWait();
                return false;
            } else {
                int month = Integer.parseInt(monthe.getText());
                if (month < 1 || month > 12) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Укажите корректный месяц рождения!.");
                    alert.showAndWait();
                    return false;
                }
            }
        }
        if(year.getText().length()==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Укажите год рождения!.");
            alert.showAndWait();
            return false;

        }
        else {
            if (cheakNUM(year.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка!");
                alert.setHeaderText(null);
                alert.setContentText("Укажите год рождения!.");
                alert.showAndWait();
                return false;
            } else {
                int years = Integer.parseInt(year.getText());
                if(years<1923 || years>2009)
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ошибка!");
                    alert.setHeaderText(null);
                    alert.setContentText("Укажите корректный год рождения!.");
                    alert.showAndWait();
                    return false;
                }
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
