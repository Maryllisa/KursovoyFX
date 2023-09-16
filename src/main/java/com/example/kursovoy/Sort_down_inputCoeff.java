package com.example.kursovoy;

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
import tables.Table_filters_step;
import tables.Table_read_student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Sort_down_inputCoeff {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;

    @FXML
    private Button find;

    @FXML
    private TextField find_rating;
    @FXML
    private TableColumn<Table_filters_step, Float> coef;

    @FXML
    private TableColumn<Table_filters_step, String> fio_student;

    @FXML
    private TableColumn<Table_filters_step, String> group;

    @FXML
    private TableColumn<Table_filters_step, String> id_student;

    @FXML
    private TableColumn<Table_filters_step, Integer> missing;

    @FXML
    private TableColumn<Table_filters_step, Float> raiting;

    @FXML
    private TableColumn<Table_filters_step, Float> step;
    @FXML
    private TableView<Table_filters_step> table;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    ObservableList<Table_filters_step> list;
    ObservableList<Table_filters_step> studList = FXCollections.observableArrayList();

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

        find.setOnAction(actionEvent ->
        {
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                output.writeInt(4);
                output.writeInt(Integer.parseInt(find_rating.getText()));
            }
            catch (Exception ex) {
            }
            id_student.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId_student()));
            fio_student.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFio_student()));
            coef.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCoef()));
            group.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getGroup()));
            step.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getStep()));
            missing.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getMissing()));
            raiting.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getRaiting()));
            try {
                table.setItems(getStudent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


    }
    private ObservableList<Table_filters_step> getStudent() throws IOException {

        float coef;
        String fio_student="";
        String group;
        String id_student;
        int missing;
        float raiting;
        float step;
        int size = input.readInt();
        if(size==0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ошибка!");
            alert.setHeaderText(null);
            alert.setContentText("Ничего не найдено");
            alert.showAndWait();
        }
        int col =0;
        for (int i = 0; i < size; i++) {
            fio_student="";
            int id_int = input.readInt();
            if(id_int/10000000 == 0)
            {
                id_student = "0"+ String.valueOf(id_int);
            }
            else
            {
                id_student = String.valueOf(id_int);
            }
            int lenght = input.readInt();
            for(int j = 0; j<lenght; j++)
            {
                fio_student+=input.readChar();
            }
            fio_student = fio_student +" ";
            lenght = input.readInt();
            for(int j = 0; j<lenght; j++)
            {
                fio_student+=input.readChar();
            }
            fio_student = fio_student +" ";
            lenght = input.readInt();
            for(int j = 0; j<lenght; j++)
            {
                fio_student+=input.readChar();
            }
            int group_int = input.readInt();
            if(id_int/10000000 == 0)
            {
                group = "0"+String.valueOf(group_int);
            }
            else
            {
                group = String.valueOf(group_int);
            }
            coef = input.readFloat();
            missing = input.readInt();
            raiting = input.readFloat();
            step = input.readFloat();
            list = FXCollections.observableArrayList(
                    new Table_filters_step(coef, fio_student, group, id_student, missing, raiting, step )
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

