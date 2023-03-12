package graficos;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    //botones
    public static BufferedImage botonGris;
    public static BufferedImage botonRojo;

    //jugador
    public static BufferedImage jugador;

    //Alien
    public static BufferedImage ufo;

    // efectos
    public static BufferedImage velocidad;

    // lasers

    public static BufferedImage lazul,lverde,lrojo;

    // numeros

    public static BufferedImage[] num = new BufferedImage[11];
    public static BufferedImage vidas;

    // explosiones

    public static BufferedImage[] exp = new BufferedImage[9];

    // Asteroides

    public static BufferedImage[] grandes = new BufferedImage[4];
    public static BufferedImage[] medianos = new BufferedImage[2];
    public static BufferedImage[] peques = new BufferedImage[2];
    public static BufferedImage[] enanos = new BufferedImage[2];
    //fuentes
    public static Font fuente;
    public static Font fuentepeque;

    public static void init(){
        jugador = Loader.imageLoader("res/ships/player.png");
        velocidad = Loader.imageLoader("res/effects/fire08.png");
        lazul = Loader.imageLoader("res/lasers/laserBlue01.png");
        lverde = Loader.imageLoader("res/lasers/laserGreen11.png");
        lrojo = Loader.imageLoader("res/lasers/laserRed01.png");
        ufo = Loader.imageLoader("res/ships/ufo.png");
        vidas = Loader.imageLoader("res/otros/vidas.png");
        botonGris = Loader.imageLoader("res/UI/grey_button00.png");
        botonRojo = Loader.imageLoader("res/UI/red_button00.png");
        fuente = new Font("res/Fuentes/kenvector_future.ttf",Font.PLAIN,42);
        fuentepeque = new Font("res/Fuentes/kenvector_future.ttf",Font.PLAIN,22);
        for(int i = 0;i<grandes.length;i++){
            grandes[i] = Loader.imageLoader("res/asteroides/meteorGrey_big3.png");
        }
        for(int i = 0;i<medianos.length;i++){
            medianos[i] = Loader.imageLoader("res/asteroides/meteorGrey_med1.png");
        }
        for(int i = 0;i<peques.length;i++){
            peques[i] = Loader.imageLoader("res/asteroides/meteorGrey_small1.png");
        }
        for(int i = 0;i<enanos.length;i++){
            enanos[i] = Loader.imageLoader("res/asteroides/meteorGrey_tiny1.png");
        }
        for(int i = 0;i < exp.length; i++){
            exp[i] = Loader.imageLoader("res/Explosiones/"+i+".png");
        }
        for(int i = 0;i < num.length; i++) {
            num[i] = Loader.imageLoader("res/numeros/"+ i +".png");
        }
    }
}
