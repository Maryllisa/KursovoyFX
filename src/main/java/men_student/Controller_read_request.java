package men_student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller_read_request {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exit;
    @FXML
    private Button find;

    @FXML
    private TextArea f_scholarship;

    @FXML
    private TextArea id;

    @FXML
    private TextArea scholarship;

    @FXML
    private TextArea status;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента

    @FXML
    void initialize() throws IOException {
        exit.setOnAction(actionEvent ->
        {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/men_student/men_for_student.fxml"));
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
                int ifer = input.readInt();
                if(ifer==1) {
                    int size = input.readInt();
                    for (int i = 0; i < size; i++) {
                        int idd = input.readInt();

                        if (idd / 10000000 == 0) {
                            id.setText("0" + String.valueOf(idd));
                        } else {
                            id.setText(String.valueOf(idd));
                        }
                    }
                    scholarship.setText(String.valueOf(input.readFloat()));
                    f_scholarship.setText(String.valueOf(input.readFloat()));
                    int size2 = input.readInt();
                    String st ="";
                    for(int i = 0; i<size2;i++)
                    {
                        st += input.readChar();
                    }
                    status.setText(st);
                    input.close();
                    output.close();
                    socket.close();
                }
                else
                {
                    Stage stage = (Stage) exit.getScene().getWindow();
                    stage.close();
                    Parent root = null;
                    try {
                        input.close();
                        output.close();
                        socket.close();
                        root = FXMLLoader.load(getClass().getResource("/men_student/no.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(root));
                    stage.show();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });


    }

}
