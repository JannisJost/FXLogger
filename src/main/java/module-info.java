module ch.fxlogger.fxkeylogger {
    requires javafx.controls;
    requires javafx.fxml;


    opens ch.fxlogger.fxkeylogger to javafx.fxml;
    exports ch.fxlogger.fxkeylogger;
}