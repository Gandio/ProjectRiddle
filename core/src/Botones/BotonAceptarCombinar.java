package Botones;

import Puzzle.Inventario;
import Puzzle.Inventario.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que realiza la combinación de objetos. Este botón solo se activa cuando 
 * el jugador se encuentra combinando y se han seleccionado dos objetos que pueden combinarse.
 * @author Francisco Madueño Chulián
 */

public class BotonAceptarCombinar extends Boton{

	private Texture botonActivado, botonDesactivado;
	
	public BotonAceptarCombinar(MyGdxGame game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonAceptarCombinar.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonAceptarCombinarDesactivado.png"));
		boton = botonDesactivado;
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));

	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		if(((Inventario) game.getScreen()).getCombinacion().size == 2){ //Esta lleno, se puede combinar
			((Inventario) game.getScreen()).setEstado(Estado.COMBINACION_PREPARADA);
		}
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonAceptarCombinar)event.getTarget()).pulsado = true;
                
                return true;
            }
		});
		
		if(((Inventario) game.getScreen()).getEstado() == Estado.COMBINACION_PREPARADA){
			boton = botonActivado;
			if(pulsado){
				//Compruebo si los objetos se pueden combinar, si lo son se combinan, se eliminan del 
				//inventario, se añade el resultado de la combinación y se vuelve al estado normal, si no 
				//se vuelve al estado normal y se vacia el array de combinacion
				((Inventario) game.getScreen()).getCombinacion().clear();
				((Inventario) game.getScreen()).setEstado(Estado.NORMAL);
				
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}

}