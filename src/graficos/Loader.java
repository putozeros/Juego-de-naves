package graficos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {

    private Object font;

    public static BufferedImage imageLoader(String path) {
        try {
            File imgArchivo = new File(path);
            return ImageIO.read(imgArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Font loadFont(String path,int size){
        try {
            return Font.createFont(Font.TYPE1_FONT, Loader.class.getResourceAsStream(path)).deriveFont(Font.PLAIN,size);
        } catch (FontFormatException | IOException a) {
            a.printStackTrace();
            return null;
        }
    }

}
