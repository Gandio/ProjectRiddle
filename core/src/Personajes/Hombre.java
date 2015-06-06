package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class Hombre extends Personaje{
	
	private static MyGdxGame game;
	private static Hombre unicaInstancia;
	
	private Hombre(MyGdxGame game) {
		super(game);
		this.game = game;
		
		personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/hombre.png"));
		posicion = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
		
		//textosXml = donde estén los textos 
	}
	
	public static Hombre getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new Hombre(game);
		
		return unicaInstancia;
	}
}