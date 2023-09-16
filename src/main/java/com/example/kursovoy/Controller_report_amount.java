package com.example.kursovoy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller_report_amount {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea amount;

    @FXML
    private TextArea col_passes;

    @FXML
    private TextArea day;

    @FXML
    private Button exit;

    @FXML
    private TextArea fio;

    @FXML
    private TextArea group;

    @FXML
    private TextArea id_student;

    @FXML
    private TextArea month;

    @FXML
    private TextArea rating;

    @FXML
    private Button send;

    @FXML
    private TextArea status;

    @FXML
    private TextField id;
    @FXML
    private TextArea year;

    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента

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
        send.setOnAction(actionEvent -> {
            try {
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                output.writeInt(13);
                socket.close();
                output.close();
                input.close();
                socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
                output = new DataOutputStream(socket.getOutputStream());
                input = new DataInputStream(socket.getInputStream());
                output.writeInt(2);
                output.writeInt(Integer.parseInt(id.getText()));
                float amountt;
                int col_passess;
                int dayy;
                String fioo = "";
                String groupp;
                String id_studentt;
                int monthh;
                float ratingg;
                int yearr;
                amountt = input.readFloat();
                col_passess = input.readInt();
                dayy = input.readInt();
                monthh = input.readInt();
                yearr = input.readInt();
                ratingg = input.readFloat();
                int size = input.readInt();
                for(int i = 0; i<size; i++)
                {
                    fioo+=input.readChar();
                }
                fioo+=" ";
                size = input.readInt();
                for(int i = 0; i<size; i++)
                {
                    fioo+=input.readChar();
                }
                fioo+=" ";
                size = input.readInt();
                for(int i = 0; i<size; i++)
                {
                    fioo+=input.readChar();
                }
                int id_int = input.readInt();
                if(id_int/10000000 == 0)
                {
                    id_studentt = "0"+ String.valueOf(id_int);
                }
                else
                {
                    id_studentt = String.valueOf(id_int);
                }
                int group_int = input.readInt();
                if(id_int/100000 == 0)
                {
                    groupp = "0"+String.valueOf(group_int);
                }
                else
                {
                    groupp = String.valueOf(group_int);
                }
                 amount.setText(String.valueOf(amountt));
                 col_passes.setText(String.valueOf(col_passess));
                 day.setText(String.valueOf(dayy));
                 fio.setText(fioo);
                 group.setText(groupp);
                 id_student.setText(id_studentt);
                 month.setText(String.valueOf(monthh));
                 rating.setText(String.valueOf(ratingg));
                 if(col_passess<11) status.setText("Одобрена");
                 else status.setText("Не одобрена");
                 year.setText(String.valueOf(yearr));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

}
