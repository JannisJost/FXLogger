module ch.fxlogger.fxkeylogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires jnativehook;


    opens ch.fxlogger.fxkeylogger to javafx.fxml;
    exports ch.fxlogger.fxkeylogger;
}