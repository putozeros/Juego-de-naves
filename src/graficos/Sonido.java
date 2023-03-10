package graficos;
import javax.sound.sampled.*;
import java.io.File;

public class Sonido {
    private Clip clip;
    private String filePath;

    public Sonido(String filePath){
        this.filePath = filePath;
        try{
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void play(){
        if(clip != null){
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void stop(){
        if(clip != null){
            clip.stop();
            clip.setFramePosition(0);
        }
    }
    public void loop(){
        if(clip != null){
            clip.stop();
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
