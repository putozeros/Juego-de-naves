package gameObjects;

import math.Vector2D;
import states.GameState;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class MovingObject extends GameObject{

    protected Vector2D speed;
    protected AffineTransform at;
    protected double angle;
    protected double maxSpeed;
    protected int ancho;
    protected int alto;
    protected GameState gameState;

    public MovingObject(Vector2D posicion, Vector2D speed, double maxSpeed, BufferedImage texture, GameState gameState) {
        super(posicion, texture);
        this.speed = speed;
        this.maxSpeed= maxSpeed;
        this.gameState = gameState;
        ancho = texture.getWidth();
        alto = texture.getHeight();
        angle = 0;
    }

    protected void collidesWith(){

        ArrayList<MovingObject> movingObjects = gameState.getMovingObjects();

        for(int i = 0; i<movingObjects.size();i++){

            MovingObject m = movingObjects.get(i);

            if(m.equals(this)){
                continue;
            }

            double distancia = m.getCenter().substract(getCenter()).getMagnitud();

            if(distancia < m.ancho/2 + ancho/2 && movingObjects.contains(this)){
                objectColision(m,this);
            }
        }
    }


    private void objectColision(MovingObject a, MovingObject b){

        if (a instanceof Player && ((Player)a).isSpawning()){
            return;
        }
        if (b instanceof Player && ((Player)b).isSpawning()){
            return;
        }
        if(!(a instanceof Asteroide && b instanceof Asteroide)){
            gameState.playExplosion(getCenter());
            a.Destruir();
            b.Destruir();
        }
    }


    protected void Destruir(){
        gameState.getMovingObjects().remove(this);
    }

    protected Vector2D getCenter(){
        return new Vector2D(posicion.getX() + ancho/2,posicion.getY() + alto/2);
    }


}