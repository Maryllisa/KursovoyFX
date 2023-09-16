package com.example.kursovoy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import tables.Table_read_student;

public class Controller_men_for_ad {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button do_step;

    @FXML
    private Button exit;

    @FXML
    private Button filter_step;

    @FXML
    private Button filter_student;

    @FXML
    private Button find_res;

    @FXML
    private Button find_student;

    @FXML
    private Button read_admin;

    @FXML
    private Button read_user;

    @FXML
    private Button reg_for_admin;

    @FXML
    private Button reg_for_user;

    @FXML
    private Button report_res;

    @FXML
    private Button work_reg_admin;

    @FXML
    private Button work_reg_student;

    @FXML
    private Button work_with_report;
    static Stage logoutStage = new Stage();
    @FXML
    private TableColumn<Table_read_student, String> fio_student;

    @FXML
    private TableColumn<Table_read_student, Integer> group;

    @FXML
    private TableColumn<Table_read_student, Integer> id_student;

    @FXML
    private TableColumn<Table_read_student, Integer> missing;

    @FXML
    private TableColumn<Table_read_student, Float> raiting;

    @FXML
    private TableView<Table_read_student> table;
    ObservableList<Table_read_student> list;
    private Stage stage;
    private Scene scene;
    private Parent root;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    @FXML
    void initialize() {
        exit.setOnAction(actionEvent ->
        {
            Socket socket = null;
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                DataInputStream input = new DataInputStream(socket.getInputStream());
                output.writeInt(15);
                socket.close();
                output.close();
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            Stage stage = (Stage) exit.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        read_user.setOnAction(actionEvent -> {
            try {
                root = FXMLLoader.load(getClass().getResource("read_user.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        });
        reg_for_user.setOnAction(actionEvent ->
        {
            try {
                root = FXMLLoader.load(getClass().getResource("reg_for_user.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
        reg_for_admin.setOnAction(actionEvent ->
        {
            try {
                root = FXMLLoader.load(getClass().getResource("reg_for_admin.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });

        read_admin.setOnAction(actionEvent ->
        {
            try {
                root = FXMLLoader.load(getClass().getResource("read_admin.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        });
        work_reg_student.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) exit.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/example/kursovoy/work_reg_student.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        work_reg_admin.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) exit.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("work_reg_admin.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        work_with_report.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) exit.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/com/example/kursovoy/work_with_requst.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        do_step.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) do_step.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("do_request.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        find_student.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) do_step.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/find/men_for_find_student.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        find_res.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) find_res.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/find/men_for_find_step.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        filter_student.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) filter_student.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/filters/sort_student_men.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        filter_step.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) filter_step.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/filters/sort_for_step_men.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        report_res.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) filter_step.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/reports/report_men.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    @FXML
    public void logoutWindow() throws IOException {
        logoutStage.showAndWait();
    }



}
