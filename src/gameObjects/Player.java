package gameObjects;

import graficos.Assets;
import graficos.Sonido;
import input.Keyboard;
import math.Vector2D;
import states.GameState;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends MovingObject{

    private Vector2D heading;
    private Vector2D aceleracion;
    private boolean accelerating = false;
    private Crono fireRate;
    private boolean spawning=true, visible;
    private Crono spawntime,flickering;
    public Player(Vector2D posicion, Vector2D speed,double maxSpeed, BufferedImage texture, GameState gameState) {
        super(posicion, speed, maxSpeed, texture, gameState);
        heading = new Vector2D(0,1);
        this.gameState = gameState;
        aceleracion = new Vector2D();
        fireRate = new Crono();
        spawntime = new Crono();
        flickering = new Crono();
    }

    @Override
    public void actualizar() {

        if(!spawntime.isRunning()){
            spawning=false;
            visible = true;
        }
        if(spawning){
            if(!flickering.isRunning()){
                flickering.run(Constantes.Flicker);
                visible = !visible;
            }
        }

        if(Keyboard.DISPARO && !fireRate.isRunning() && !spawning){
            gameState.getMovingObjects().add(new Laser(getCenter().add(heading.escalar(ancho-30)), heading,
                    Constantes.LASER_SPEED, angle, Assets.lazul, gameState));
            fireRate.run(Constantes.FIRERATE);
            Sonido sonido = new Sonido("res/Sonidos/sfx_laser2.wav");
            sonido.play();
        }
        if(Keyboard.DERECHA){
            angle += Constantes.DELTAANGLE;
        }
        if(Keyboard.IZQUIERDA){
            angle -= Constantes.DELTAANGLE;
        }
        if(Keyboard.ARRIBA){
            aceleracion = heading.escalar(Constantes.ACC);
            accelerating = true;
        } else{
            if(speed.getMagnitud() != 0){
                aceleracion = (speed.escalar(-1).normalizar()).escalar(Constantes.ACC/2);
            }
            accelerating = false;
        }

        speed = speed.add(aceleracion);

        speed = speed.limite(maxSpeed);

        heading = heading.setDireccion(angle-Math.PI/2);

        posicion = posicion.add(speed);

        if(posicion.getX() > Constantes.WIDTH){
            posicion.setX(-70);
        }
        if(posicion.getY() >Constantes.HEIGHT){
            posicion.setY(-60);
        }
        if (posicion.getX()<-70){
            posicion.setX(Constantes.WIDTH);
        }
        if (posicion.getY()<-60){
            posicion.setY(Constantes.HEIGHT);
        }
        fireRate.actualizar();
        spawntime.actualizar();
        flickering.actualizar();
        collidesWith();

    }

    @Override
    public void Destruir(){
        Sonido sonido = new Sonido("res/Sonidos/sfx_lose.wav");
        sonido.play();
        spawning = true;
        spawntime.run(Constantes.Spawning);
        resetValues();
    }

    private void resetValues(){
        angle = 0;
        speed = new Vector2D();
        posicion = new Vector2D(Constantes.WIDTH/2 - Assets.jugador.getWidth()/2,
                Constantes.HEIGHT/2 - Assets.jugador.getHeight()/2);
        gameState.substractLife();
    }
    @Override
    public void dibujar(Graphics graphics) {
        if(!visible){
            return;
        }
        Graphics2D g2d = (Graphics2D) graphics;

        AffineTransform at1 = AffineTransform.getTranslateInstance(posicion.getX() + ancho/2 + 32,
                posicion.getY() + alto/2 + 20);
        AffineTransform at2 = AffineTransform.getTranslateInstance(posicion.getX()+2, posicion.getY() + alto/2+20);

        at1.rotate(angle,-32,-20);
        at2.rotate(angle,ancho/2 -2,-20);


        if(accelerating){
            g2d.drawImage(Assets.velocidad,at1,null);
            g2d.drawImage(Assets.velocidad,at2,null);
        }

        at = AffineTransform.getTranslateInstance(posicion.getX(),posicion.getY());

        at.rotate(angle, ancho/2, alto/2);

        g2d.drawImage(texture, at, null);
    }

    public boolean isSpawning(){
        return spawning;
    }

}
