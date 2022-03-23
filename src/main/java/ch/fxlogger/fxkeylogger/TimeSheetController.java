package ch.fxlogger.fxkeylogger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TimeSheetController implements Initializable {

    @FXML
    private TableView<Info> table;

    @FXML
    private ImageView btnCloseWindow;

    @FXML
    private TableColumn<Info, String> datumCol= new TableColumn<>("Datum");

    @FXML
    private TableColumn<Info,String> einträgeCol=new TableColumn<>("Einträge");

    @FXML
    private TableColumn<Info,String> zeitCol= new TableColumn<>("Zeit");

    private final ObservableList<Info> data = FXCollections.observableArrayList();

    public void addTable(Info info){
        System.out.println("adding");
        datumCol.setCellValueFactory(
                new PropertyValueFactory<Info, String>("datum"));
        einträgeCol.setCellValueFactory(
                new PropertyValueFactory<Info, String>("einträge"));
        zeitCol.setCellValueFactory(
                new PropertyValueFactory<Info, String>("zeit"));
        data.add(info);
        table.getColumns().addAll(datumCol,einträgeCol,zeitCol);
        table.setItems(data);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void closeWindow(MouseEvent event) {
        System.exit(0);
    }

}
