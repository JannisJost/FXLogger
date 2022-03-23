package ch.fxlogger.fxkeylogger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TimeSheetController implements Initializable {
    @FXML
    private ImageView btnCloseWindow;

    @FXML
    private TableView<?> table;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }
}
