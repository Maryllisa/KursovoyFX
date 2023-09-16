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
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tables.Table_read_student;
import tables.Table_step;

public class Controller_find_rating {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Table_step, Float> amount;

    @FXML
    private Button change;

    @FXML
    private Button exit;

    @FXML
    private TableColumn<Table_step, String> fio_student;

    @FXML
    private TableColumn<Table_step, String> group;
    @FXML
    private TableColumn<Table_step, String> id_student;

    @FXML
    private TableColumn<Table_step, Integer> missing;

    @FXML
    private TableColumn<Table_step, Float> raiting;

    @FXML
    private TableView<Table_step> table;

    @FXML
    private TextField ratings;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    ObservableList<Table_step> list;
    ObservableList<Table_step> studList = FXCollections.observableArrayList();

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
        change.setOnAction(actionEvent ->
        {
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                output.writeInt(2);
                output.writeFloat(Float.parseFloat(ratings.getText()));
            }
            catch (Exception ex) {
            }
            id_student.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId()));
            fio_student.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFio()));
            group.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getGroup()));
            missing.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCol_pas()));
            raiting.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getRating()));
            amount.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getAmount()));
            try {
                table.setItems(getStudent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

    }
    private ObservableList<Table_step> getStudent() throws IOException {

        String id="";
        String fio ="";
        String group;
        int col_pas;
        float rating;
        float amount;
        int size = input.readInt();
        if(size==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ничего не найдено");
            alert.showAndWait();
        }
        for(int i =0; i<size; i++)
        {
            fio ="";
            int id_int = input.readInt();
            if(id_int/10000000 == 0)
            {
                id = "0"+ String.valueOf(id_int);
            }
            else
            {
                id = String.valueOf(id_int);
            }
            int lenght = input.readInt();
            for(int j = 0; j<lenght; j++)
            {
                fio+=input.readChar();
            }
            fio = fio +" ";
            lenght = input.readInt();
            for(int j = 0; j<lenght; j++)
            {
                fio+=input.readChar();
            }
            fio = fio +" ";
            lenght = input.readInt();
            for(int j = 0; j<lenght; j++)
            {
                fio+=input.readChar();
            }
            int group_int = input.readInt();
            if(id_int/100000 == 0)
            {
                group = "0"+String.valueOf(group_int);
            }
            else
            {
                group = String.valueOf(group_int);
            }
            col_pas = input.readInt();
            rating = input.readFloat();
            amount = input.readFloat();
            list = FXCollections.observableArrayList(
                    new Table_step(id, fio,group, col_pas, rating, amount)
            );
            studList.add(list.get(0));

        }
        input.close();
        output.close();
        socket.close();
        table.setItems(studList);
        return studList;
    }
}
