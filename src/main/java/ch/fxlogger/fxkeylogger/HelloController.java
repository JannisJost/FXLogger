package ch.fxlogger.fxkeylogger;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HelloController implements Initializable {

    @FXML
    private Button btnStartOrStopLogging;
    @FXML
    private ChoiceBox<String> choiceLogType;
    @FXML
    private Label lblActivityIndicator;
    @FXML
    private ImageView btnCloseWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceLogType.getItems().add("Database");
        choiceLogType.getItems().add("File");
        choiceLogType.setValue("Database");
    }

    @FXML
    private void startOrStopLogging(ActionEvent event) {
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }
}
