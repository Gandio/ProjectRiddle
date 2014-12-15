package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase representa el botón de conversación. Este botón se usa para conversar con los
 * personajes durante el juego.
 * @author Francisco Madueño Chulián
 */

public class BotonConversacion extends Boton{

	public BotonConversacion(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/botonConversacion.png"));
	}
}
