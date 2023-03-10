package gameObjects;

import graficos.Assets;
import graficos.Sonido;
import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Asteroide extends MovingObject{

    private Size size;

    public Asteroide(Vector2D posicion, Vector2D speed, double maxSpeed, BufferedImage texture, GameState gameState, Size size) {
        super(posicion, speed, maxSpeed, texture, gameState);
        this.size = size;
        this.speed = speed.escalar(maxSpeed);
    }

    @Override
    public void actualizar() {
        posicion = posicion.add(speed);
        if(posicion.getX() > Constantes.WIDTH){
            posicion.setX(-ancho);
        }
        if(posicion.getY() >Constantes.HEIGHT){
            posicion.setY(-alto);
        }
        if (posicion.getX()<-ancho){
            posicion.setX(Constantes.WIDTH);
        }
        if (posicion.getY()<-alto){
            posicion.setY(Constantes.HEIGHT);
        }

        angle += Constantes.DELTAANGLE/2;
    }

    @Override
    public void Destruir(){
        Sonido sonido = new Sonido("res/Sonidos/explosion.wav");
        sonido.play();
        gameState.dividirAsteroide(this);
        gameState.addpuntuacion(Constantes.ASTEROIDE_SCORE, posicion);
        super.Destruir();
    }

    @Override
    public void dibujar(Graphics graphics) {

        Graphics2D g2d = (Graphics2D)graphics;

        at = AffineTransform.getTranslateInstance(posicion.getX(),posicion.getY());

        at.rotate(angle, ancho/2, alto/2);

        g2d.drawImage(texture, at, null);
    }

    public Size getSize() {
        return size;
    }
}
