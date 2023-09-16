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
import tables.Table_Request;
import tables.Table_read_admin;

public class Controller_read_request {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Table_Request, String> id_report;

    @FXML
    private TableColumn<Table_Request, Float> scholarship;

    @FXML
    private TableColumn<Table_Request, Float> scholarship_f;

    @FXML
    private TableColumn<Table_Request, String> status;

    ObservableList<Table_Request> studList = FXCollections.observableArrayList();
    ObservableList<Table_Request> list;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    @FXML
    private TableView<Table_Request> table;

    @FXML
    void initialize() {
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
        id_report.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getId_request()));
        scholarship.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getScholarship()));
        scholarship_f.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getF_scholarship()));
        status.setCellValueFactory(field->new SimpleObjectProperty<>(field.getValue().getStatus()));

        try {
            table.setItems(getRequest());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private ObservableList<Table_Request> getRequest() throws IOException
    {
        int col = input.readInt();
        for (int j = 0; j < col; j++) {
            String id;
            float scholarship_p;
            float scholarship_p_f;
            String status_p= "";
            int id_int = input.readInt();
            if(id_int/10000000 == 0)
            {
                id = "0"+ String.valueOf(id_int);
            }
            else
            {
                id = String.valueOf(id_int);
            }
           scholarship_p = input.readFloat();
           scholarship_p_f = input.readFloat();
           int size = input.readInt();
           for(int i =0; i<size;i++) {
               status_p+= input.readChar();
           }
            list = FXCollections.observableArrayList(new Table_Request(id, scholarship_p, scholarship_p_f, status_p));
            studList.add(list.get(0));
        }
        input.close();
        output.close();
        socket.close();
        table.setItems(studList);
        return studList;
    }
}




