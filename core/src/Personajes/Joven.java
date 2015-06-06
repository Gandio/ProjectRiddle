package Personajes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class Joven extends Personaje{
	
	private static MyGdxGame game;
	private static Joven unicaInstancia;
	
	private Joven(MyGdxGame game) {
		super(game);
		this.game = game;
		
		personaje = new Texture(Gdx.files.internal("Imagenes/Personajes/joven.png"));
		posicion = new Vector2(Tools.centrarAncho(game, personaje), Tools.centrarAlto(game, personaje));
		
		//textosXml = donde estén los textos 
	}
	
	public static Joven getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new Joven(game);
		
		return unicaInstancia;
	}
}
