package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa a los objetos Mujer
 * @author Francisco Madueño Chulián
 *
 */

public class Mujer extends Personaje{
	
	private static MyGdxGame game;
	private static Mujer unicaInstancia;
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	
	private Mujer(MyGdxGame game) {
		super(game);
		this.game = game;
		
		personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/mujer.png"));
		posicion = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
		
		//textosXml = donde estén los textos 
	}
	
	/**
	 * Solo habrá un objeto Mujer durante cada partida, este método se encarga de ello.
	 * @return
	 */
	
	public static Mujer getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new Mujer(game);
		
		return unicaInstancia;
	}
}
