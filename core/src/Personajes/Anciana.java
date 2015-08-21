package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa a los objetos Anciana
 * @author Francisco Madueño Chulián
 */
public class Anciana extends Personaje{
	
	private static MyGdxGame game;
	private static Anciana unicaInstancia;
	
	/**
	 * Contructor de la clase
	 * @param game
	 */
	private Anciana(MyGdxGame game) {
		super(game);
		Anciana.game = game;
		
		personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/Mujer-mayor.png"));
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