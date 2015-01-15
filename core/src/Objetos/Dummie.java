package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class Dummie extends Personaje{
	
	public Dummie(MyGdxGame game) {
		super(game);
		personaje = new Texture(Gdx.files.internal("Imagenes/plantillaMuñequil.png"));
	}
}
