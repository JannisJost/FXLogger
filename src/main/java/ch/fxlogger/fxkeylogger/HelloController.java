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
    KeyLogger keyLogger;

    public void HelloController(KeyLogger keyLogger){
        this.keyLogger=keyLogger;
    }

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

    @FXML
    private void BtnStartOrStopLogging(ActionEvent event){
        int logInWhat;
        switch (choiceLogType.getValue()){
            case "File": logInWhat=0;
                        break;
            case  "Datebase": logInWhat=1;
                        break;
        }
        keyLogger.setLogInWhat(inSafeWhat);

    }
}
