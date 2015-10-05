package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa a los objetos Joven
 * @author Francisco Madueño Chulián
 */
public class Joven extends Personaje{
	
	private static TheHouseOfCrimes game;
	private static Joven unicaInstancia;
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	private Joven(TheHouseOfCrimes game) {
		super(game);
		Joven.game = game;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_JOVEN_SUSPENSE));
		else
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_JOVEN));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
	}
	
	/**
	 * Solo habrá un objeto Joven durante cada partida, este método se encarga de ello.
	 * @return
	 */
	public static Joven getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new Joven(game);
		
		return unicaInstancia;
	}
}