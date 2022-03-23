package ch.fxlogger.fxkeylogger;

import java.io.IOException;
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
import org.jnativehook.keyboard.NativeKeyEvent;

public class HelloController implements Initializable {
    KeyLogger keyLogger;
    private int loggingState=0;

    @FXML
    private Button btnStartOrStopLogging;
    @FXML
    private ChoiceBox<String> choiceLogType;
    @FXML
    private Label lblActivityIndicator;
    @FXML
    private ImageView btnCloseWindow;
    private HelloApplication helloApplication;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceLogType.getItems().add("Database");
        choiceLogType.getItems().add("File");
        choiceLogType.setValue("Database");

    }

    @FXML
    private void startOrStopLogging(ActionEvent event) throws IOException {

        int logInWhat=0;
        switch (choiceLogType.getValue()){
            case "File": logInWhat=0;
                break;
            case  "Datebase": logInWhat=1;
                break;
        }
        keyLogger.setLogInWhat(logInWhat);
        switch (loggingState) {
            case 0:
                lblActivityIndicator.setText("Active");
                keyLogger.run();
                keyLogger.write();
                loggingState = 1;
                break;
            case 1:
                lblActivityIndicator.setText("Inactive");
                keyLogger.stop();
                loggingState = 0;
                break;
        }
        helloApplication.timeSheet();
    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }


    public void setKeyLogger(KeyLogger keyLogger) {
        this.keyLogger = keyLogger;
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }
}
