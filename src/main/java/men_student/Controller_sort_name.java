package men_student;

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
import tables.Table_read_student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller_sort_name {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableColumn<Table_read_student, String> age;

    @FXML
    private TableColumn<Table_read_student, String> fio_student;

    @FXML
    private TableColumn<Table_read_student, String> group;

    @FXML
    private TableColumn<Table_read_student, String> id_student;

    @FXML
    private TableColumn<Table_read_student, Integer> missing;

    @FXML
    private TableColumn<Table_read_student, Float> raiting;

    @FXML
    private TableView<Table_read_student> table;
    @FXML
    private Button exit;

    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;
    TextField tf = new TextField("127.0.0.1");//ip adress клиента
    TextField tf1 = new TextField("1024");// port клиента
    ObservableList<Table_read_student> list;
    ObservableList<Table_read_student> studList = FXCollections.observableArrayList();

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

        try {
            socket = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
            output.writeInt(3);
        }
        catch (Exception ex) {
        }
        id_student.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId_student()));
        fio_student.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getName()));
        age.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getAge()));
        group.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId_group()));

        missing.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCol_passes()));
        raiting.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getRating()));
        try {
            table.setItems(getStudent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private ObservableList<Table_read_student> getStudent() throws IOException {

        String ss;

        String id_student;
        String name;
        String id_group;
        int col_passes;
        float rating;
        int size = input.readInt();
        int col =0;
        for (int i = 0; i < size; i++) {
            ss ="";
            int id_int = input.readInt();
            if(id_int/10000000 == 0)
            {
                id_student = "0"+ String.valueOf(id_int);
            }
            else
            {
                id_student = String.valueOf(id_int);
            }
            int length = input.readInt();
            String surname = "";
            for (int j = 0; j < length; j++) {
                surname += input.readChar();
            }
            ss += surname + " ";
            length = input.readInt();
            String names = "";
            for (int j = 0; j < length; j++) {
                names += input.readChar();
            }
            ss += names + " ";
            length = input.readInt();
            String patronymic = "";
            for (int j = 0; j < length; j++) {
                patronymic += input.readChar();
            }
            ss += patronymic;
            name = ss;
            int group_int = input.readInt();
            if(group_int /100000 == 0)
            {
                id_group = "0"+String.valueOf(group_int);
            }
            else
            {
                id_group = String.valueOf(group_int);
            }
            col_passes = input.readInt();
            rating = input.readFloat();
            int day = input.readInt();
            int monthe = input.readInt();
            int year = input.readInt();
            list = FXCollections.observableArrayList(
                    new Table_read_student(id_student, name,day, monthe, year, id_group, col_passes, rating)
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
