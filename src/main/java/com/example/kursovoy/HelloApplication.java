package com.example.kursovoy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

public class HelloApplication extends Application {

    static private ServerSocket connection;
    static private OutputStream output;
    static private InputStream input;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setTitle("Стистема расчета студенчиских стипендий");
        stage.setScene(new Scene(root, 900, 700));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}