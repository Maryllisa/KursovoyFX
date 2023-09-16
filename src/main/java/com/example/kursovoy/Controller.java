package com.example.kursovoy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Controller {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button choose_role_admin;

    @FXML
    private Button choose_role_user;

    @FXML
    void initialize() {
        choose_role_user.setOnAction(actionEvent -> {
            Stage stage = (Stage) choose_role_user.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("auto_for_user.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
        choose_role_admin.setOnAction(actionEvent -> {
            Stage stage = (Stage) choose_role_admin.getScene().getWindow();
            // do what you have to do
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("auto_for_admin.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
