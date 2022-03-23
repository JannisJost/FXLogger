package ch.fxlogger.fxkeylogger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Time;

public class HelloApplication extends Application {
    private final KeyLogger keyLogger = new KeyLogger();
    private final HelloController helloController = new HelloController();
    private TimeSheetController timeSheetController = new TimeSheetController();
    private Stage stage;
    private Stage stage1;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        keyLogger.setHelloApplication(this);

        helloView();
    }

    public void helloView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        helloController.setKeyLogger(keyLogger);
        helloController.setHelloApplication(this);
        fxmlLoader.setController(helloController);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FXLogger");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    public void timeSheet() {
        stage1 = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TimeSheet.fxml"));
        Scene scene = null;
        keyLogger.setTimeSheetController(timeSheetController);
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage1.setTitle("TimeSheet");
        stage1.setScene(scene);
        stage1.show();


    }

    public void closeTimesheet() {
        stage1.close();
    }
}