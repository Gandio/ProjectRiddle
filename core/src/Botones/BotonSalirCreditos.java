package Botones;

import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que permite salir de la pantalla de los títulos de crédito a
 * la pantalla Inicio
 * @author Francisco Madueño Chulián
 *
 */

public class BotonSalirCreditos extends Boton{
	
	/**
	 * Constructor de la clase
	 * @param game
	 */

	public BotonSalirCreditos(TheCrimeHouse game) {
		super(game);
		
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_CERRAR_INVENTARIO));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Comprueba si se ha pulsado el botón, si es así, el jugador vuelve a la pantalla Inicio
	 */
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		/*
		 * Comprobamos si se pulsa el botón
		 */
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonSalirCreditos)event.getTarget()).pulsado = true;
                return true;
            }
		});
	
		if(pulsado){
			pulsado = false;
			game.getScreen().dispose();
			game.setScreen(new Inicio(game));
		}
		
		pulsado = false;
	}
}
