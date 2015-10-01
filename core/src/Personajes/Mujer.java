package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa al personaje de la mujer
 * @author Francisco Madueño Chulián
 */
public class Mujer extends Personaje{
	
	private static TheCrimeHouse game;
	private static Mujer unicaInstancia;
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	private Mujer(TheCrimeHouse game) {
		super(game);
		Mujer.game = game;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_MUJER_SUSPENSE));
		else
			personaje = new Texture(Gdx.files.internal(GestorImagen.URL_MUJER));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
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