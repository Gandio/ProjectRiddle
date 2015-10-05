package Botones;

import Pantallas.Creditos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón Créditos. Da acceso a la pantalla de títulos de crédito del 
 * juego desde la pantalla Incio.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonCreditos extends Boton{
	
	/**
	 * Constructor de la clase.
	 * @param game
	 */

	public BotonCreditos(TheHouseOfCrimes game) {
		super(game);
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_CREDITOS));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Comprueba si se pulsa el botón, si es así muestra la pantalla de títulos de crédito.
	 */
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		/*
		 * Comprobamos si se pulsa el botón
		 */
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonCreditos)event.getTarget()).pulsado = true;
                return true;
            }
		});
	
		if(pulsado){
			pulsado = false;
			game.getScreen().dispose();
			game.setScreen(new Creditos());
		}
		
		pulsado = false;
	}

}
