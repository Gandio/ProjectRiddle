package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa a los objetos Niña
 * @author Francisco Madueño Chulián
 *
 */

public class Niña extends Personaje{
	
	private static MyGdxGame game;
	private static Niña unicaInstancia;
	
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	private Niña(MyGdxGame game) {
		super(game);
		this.game = game;
		
		personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/chica.png"));
		posicion = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
		
		//textosXml = donde estén los textos 
	}
	
	/**
	 * Solo habrá un objeto Niña durante cada partida, este método se encarga de ello.
	 * @return
	 */
	
	public static Niña getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new Niña(game);
		
		return unicaInstancia;
	}
}
