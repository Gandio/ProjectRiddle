package Personajes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.TheCrimeHouse;

/**
 * Clase abstracta que representa a los personajes que participan en una patida
 * @author Francisco Madueño Chulián
 */

public abstract class Personaje extends Actor{
	protected Texture personaje;
	protected Vector2 coordenadas;
	
	/**
	 * Constructor de la clase personaje
	 * @param game
	 */
	public Personaje(TheCrimeHouse game){}
	
	/**
	 * Dibuja al actor en el stage
	 */
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(personaje, coordenadas.x, coordenadas.y);
	}
	
	/**
	 * Este método se usa para modificar las coordenadas del personaje
	 * @param x
	 * @param y
	 */
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
}