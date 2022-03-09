module ch.fxlogger.fxkeylogger {
    requires javafx.controls;
    requires javafx.fxml;
    requires jnativehook;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens ch.fxlogger.fxkeylogger to javafx.fxml;
    exports ch.fxlogger.fxkeylogger;
}