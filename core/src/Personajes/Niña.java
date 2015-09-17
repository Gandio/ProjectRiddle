package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa al personaje de la niña.
 * @author Francisco Madueño Chulián
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
		Niña.game = game;
		
		if(MyGdxGame.SUSPENSE_AMBIENTE)
			personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/Chica-suspense.png"));
		else
			personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/Chica-neutro.png"));

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