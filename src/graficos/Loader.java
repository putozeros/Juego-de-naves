package graficos;

import org.jetbrains.annotations.Nullable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class Loader {

    public static BufferedImage imageLoader(String path) {
        try {
            File imgArchivo = new File(path);
            return ImageIO.read(imgArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
