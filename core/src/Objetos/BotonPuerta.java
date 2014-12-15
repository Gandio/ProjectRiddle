package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase hereda de Boton, representa el botón que nos permite entrar en las habitaciones
 * si estamos cerca de una de las puertas.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonPuerta extends Boton{
	Texture botonActivado, botonDesactivado;
	
	public BotonPuerta(MyGdxGame game) {
		super(game);
		botonActivado = new Texture(Gdx.files.internal("Imagenes/botonPuerta.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/botonPuertaDesactivado.png"));
		boton = botonDesactivado;
	}
	
	/**
	 * Activa el botón, podemos entrar en una habitación
	 */
	public void activar(){
		boton = botonActivado;
	}
	
	/**
	 * Desactiva el botón, no podemos entrar en una habitación
	 */
	public void desactivar(){
		boton = botonDesactivado;
	}
}