package Botones;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

/** Esta clase abstracta representa las funcionalidades básicas de los botones de todo el juego.
*   @author Francisco Madueño Chulián 
*/

public abstract class Boton extends Actor{
	protected MyGdxGame game;
	protected Texture boton;
	protected Vector2 coordenadas;
	protected boolean pulsado = false;
	
	/**
	 * Constructor de la clase botón
	 * @param game
	 */
	
	public Boton(MyGdxGame game){
		this.game = game;
	}
	
	/**
	 * Este método modifica las coordenadas del botón
	 * @param x
	 * @param y
	 */

	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	/**
	 * Este método dibuja la textura del botón en el batch del stage.
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(boton, coordenadas.x, coordenadas.y);
	}
}
