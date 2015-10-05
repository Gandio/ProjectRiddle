package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa a los objetos Anciana
 * @author Francisco Madueño Chulián
 */
public class Anciana extends Personaje{
	
	private static TheHouseOfCrimes game;
	private static Anciana unicaInstancia;
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	private Anciana(TheHouseOfCrimes game) {
		super(game);
		Anciana.game = game;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_ANCIANA_SUSPENSE));
		else
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_ANCIANA));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
	}
	
	/**
	 * Solo habrá un objeto Anciana durante cada partida, este método se encarga de ello.
	 * @return
	 */
	public static Anciana getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new Anciana(game);
		
		return unicaInstancia;
	}
}