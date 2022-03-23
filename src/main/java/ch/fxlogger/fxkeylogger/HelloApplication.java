package ch.fxlogger.fxkeylogger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    private Stage stage;
    private Stage stage1;
    private final KeyLogger keyLogger= new KeyLogger();
    private final HelloController helloController = new HelloController();
    private TimeSheetController timeSheetController = new TimeSheetController();
    private int x=1;
    @Override
    public void start(Stage stage) throws IOException {
    this.stage= stage;
    keyLogger.setHelloApplication(this);

    helloView();
    }

    public static void main(String[] args) {
        launch();
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
        if(x==1){
            stage1 = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("TimeSheet.fxml"));
            keyLogger.setTimeSheetController(timeSheetController);
            fxmlLoader.setController(timeSheetController);
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
                stage1.setTitle("TimeSheet");
                stage1.setScene(scene);
                stage1.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            x=0;
        }else{
            stage1.close();
            x=1;
        }
    }


}