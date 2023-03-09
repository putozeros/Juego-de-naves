package gameObjects;

import graficos.Text;
import math.Vector2D;
import states.GameState;

import java.awt.*;

public class Mensaje {
    private GameState gameState;
    private float alpha;
    private String texto;
    private Vector2D posicion;
    private Color color;
    private boolean centro;
    private boolean aparecer;
    private Font font;
    private final float deltaAlfa = 0.01f;

    public Mensaje(Vector2D posicion, boolean aparecer, String texto, Color color, boolean centro, Font font, GameState gameState) {
        this.gameState = gameState;
        this.texto = texto;
        this.posicion = posicion;
        this.color = color;
        this.centro = centro;
        this.aparecer = aparecer;
        this.font = font;

        if(aparecer){
            alpha =1;
        }else{
            alpha =0;
        }
    }

    public void dibujar(Graphics2D g2d){

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha));

        Text.drawText(g2d,texto,posicion,centro,color,font);

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1));

        posicion.setY(posicion.getY()-1);

        if(aparecer){
            alpha -= deltaAlfa;
        }else{
            alpha += deltaAlfa;
        }

        if(aparecer && alpha <0 || !aparecer && alpha > 1){
            gameState.getMensajes().remove(this);
        }

    }
}
