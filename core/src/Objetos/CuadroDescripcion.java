package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el cuadro donde se muestra la descripción de los objetos que se
 * recogen.
 * @author Francisco Madueño Chulián
 */

public class CuadroDescripcion extends CuadroTexto{
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	public CuadroDescripcion(TheHouseOfCrimes game) {
		super(game);
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroDescripcion.png"));
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
		font.setColor(Color.BLACK);
		String textoConLimites = wrapString(texto, 14); //máximo 14 caracteres por linea
		font.drawMultiLine(batch, textoConLimites, 145, 490);
		
	}
}
