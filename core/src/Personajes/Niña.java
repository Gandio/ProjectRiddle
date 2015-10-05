package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa al personaje de la niña.
 * @author Francisco Madueño Chulián
 */
public class Niña extends Personaje{
	
	private static TheHouseOfCrimes game;
	private static Niña unicaInstancia;
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	private Niña(TheHouseOfCrimes game) {
		super(game);
		Niña.game = game;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_CHICA_SUSPENSE));
		else
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_CHICA));

		coordenadas = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
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