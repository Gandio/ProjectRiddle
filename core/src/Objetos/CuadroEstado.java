package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el cuadro de texto donde se muestra el siguiente paso que tiene
 * que dar el jugador. Esta clase sirve como ayuda para el jugador, un pequeño resumen de
 * lo que tiene que hacer si se queda atascado durante la partida.
 * @author Francisco Madueño Chulian
 */

public class CuadroEstado extends CuadroTexto{
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	public CuadroEstado(MyGdxGame game) {
		super(game);
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroObjetivo.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
		
		texto = "";
		font = new BitmapFont();
	}
	
	/**
	 * Dibuja el actor en el stage
	 */
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(cuadroTexto, coordenadas.x, coordenadas.y);
		font.setScale(2.5f);
		String textoConLimites = wrapString(texto, 15); //como máximo 15 caracteres por linea
		
		font.drawMultiLine(batch, textoConLimites, 145, 490);
	}
}
