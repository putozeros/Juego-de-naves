package states;

import UI.Buttones;
import gameObjects.Constantes;
import graficos.Assets;

import java.awt.*;
import java.util.ArrayList;

public class EstadoMenu extends State{
    private ArrayList<Buttones> boton;

    public EstadoMenu(){
        boton = new ArrayList<>();

        boton.add(new Buttones(
                Assets.botonGris,
                Assets.botonRojo,
                Constantes.WIDTH/2 - Assets.botonGris.getWidth()/2,
                Constantes.HEIGHT/2 - Assets.botonGris.getHeight(),
                Constantes.PLAY,
                () -> State.cambiarEstado(new GameState())));

        boton.add(new Buttones(
                Assets.botonGris,
                Assets.botonRojo,
                Constantes.WIDTH/2 - Assets.botonGris.getWidth()/2,
                Constantes.HEIGHT/2 + Assets.botonGris.getHeight(),
                Constantes.EXIT,
                () -> System.exit(0)
        ));
    }

    @Override
    public void updatear() {
        for(Buttones b: boton){
            b.actualizar();
        }
    }

    @Override
    public void dibujar(Graphics g) {
        for(Buttones b: boton){
            b.dibujar(g);
        }
    }
}
