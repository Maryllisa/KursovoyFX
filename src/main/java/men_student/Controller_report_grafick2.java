package men_student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_report_grafick2 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private NumberAxis rating = new NumberAxis();

    @FXML
    private CategoryAxis student = new CategoryAxis();
    @FXML
    private LineChart<String, Number> graf = new LineChart<String,Number>(student,rating);;
    @FXML
    private Button exit;

    private static int flagg=0;
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
                root = FXMLLoader.load(getClass().getResource("/men_student/men_for_student.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        });

        XYChart.Series series = new XYChart.Series();
        student.setLabel("Студенты");
        rating.setLabel("Рейтинг");

if(flagg==0) {

    try {
        flagg++;
        socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        DataInputStream input = new DataInputStream(socket.getInputStream());
        output.writeInt(3);
        float ratings;
        String id_student;
        int size = input.readInt();
        for (int i = 0; i < size; i++) {
            int id = input.readInt();
            if (id / 10000000 == 0) {
                id_student = "0" + String.valueOf(id);
            } else {
                id_student = String.valueOf(id);
            }
            ratings = input.readFloat();
            series.getData().add(new XYChart.Data(id_student, ratings));

        }
        graf.getData().addAll(series);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }

}

    }

}