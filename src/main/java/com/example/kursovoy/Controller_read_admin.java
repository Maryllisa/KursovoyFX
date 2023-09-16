package com.example.kursovoy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tables.Table_read_admin;
import tables.Table_read_student;

public class Controller_read_admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Table_read_admin, String> login;

    @FXML
    private TableColumn<Table_read_admin, String> name;

    @FXML
    private TableColumn<Table_read_admin, String> patronymic;

    @FXML
    private TableColumn<Table_read_admin, String> surname;

    @FXML
    private TableColumn<Table_read_admin, String> work;
    @FXML
    private TableView<Table_read_admin> table;
    ObservableList<Table_read_admin> studList = FXCollections.observableArrayList();
    ObservableList<Table_read_admin> list;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    @FXML
    void initialize() {
        TextField tf = new TextField("127.0.0.1");//ip adress клиента
        TextField tf1 = new TextField("1024");// port клиента

        try {
            socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
        }
        catch (Exception ex) {
        }
        exit.setOnAction(actionEvent ->
        {
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
        login.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getLogin()));
        surname.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getSurname()));
        name.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getName()));
        patronymic.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getPatronymic()));
        work.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getWork()));
        try {
            table.setItems(getAdmin());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ObservableList<Table_read_admin> getAdmin() throws IOException
    {
        String str = "";
        String log;
        String sur;
        String nam;
        String patron;
        String workk;
        output.writeInt(4);
        int col = input.readInt();
        for (int j = 0; j < col; j++) {
        int size = input.readInt();
        for(int i =0; i<size; i++)
        {
            str+=input.readChar();
        }
        log =str;
        str ="";
        size = input.readInt();
        for(int i =0; i<size; i++)
        {
            str+=input.readChar();
        }
        sur =str;
        str ="";
        size = input.readInt();
        for(int i =0; i<size; i++)
        {
            str+=input.readChar();
        }
        nam =str;
        str ="";
        size = input.readInt();
        for(int i =0; i<size; i++)
        {
            str+=input.readChar();
        }
        patron =str;
        str ="";
        size = input.readInt();
        for(int i =0; i<size; i++)
        {
            str+=input.readChar();
        }
        workk =str;
        str ="";
        list = FXCollections.observableArrayList(new Table_read_admin(log, sur, nam, patron, workk));
        studList.add(list.get(0));
    }
        input.close();
        output.close();
        socket.close();
        table.setItems(studList);
        return studList;
    }

}
