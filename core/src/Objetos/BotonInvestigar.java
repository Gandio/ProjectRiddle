package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase representa el botón de investigar. Este botón se usará pasa activar o 
 * desactivar el modo investigación mientras te encuentres en una habitación
 * @author Francisco Madueño Chulián
 */

public class BotonInvestigar extends Boton{
	
	public BotonInvestigar(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/botonInvestigar.png"));
	}
}
