package Botones;

import Pantallas.Pasillo;
import Puzzle.Inventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que da acceso al inventario del jugador.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonInventario extends Boton{
	
	/**
	 * Constructor de la clase.
	 * @param game
	 */

	public BotonInventario(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/botonInventario.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Este método actualiza el comportamiento del botón durante toda la partida
	 */
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonInventario)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		/*
		 * Si pulsamos el botón nos muestra el inventario del jugador
		 */
		
		if(pulsado){
			pulsado = false;
			((Pasillo) game.getScreen()).pararMusica();
			game.setScreen(((Pasillo) game.getScreen()).getCursor().inventario.getInstancia());
		}
		
		pulsado = false;
	}
}