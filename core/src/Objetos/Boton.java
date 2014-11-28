package Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

/** Esta clase abstracta representa las funcionalidades básicas de los botones de todo el juego.
*   @author Francisco Madueño Chulián 
*/

public abstract class Boton extends Actor{
	private MyGdxGame game;
	protected Texture boton;
	protected boolean pulsado;
	private Vector2 coordenadas;
	
	/**
	 * Recibe una objeto MyGdxGame, este objeto hereda de la clase Game
	 * @param game
	 */
	public Boton(MyGdxGame game){
		this.game = game;
		pulsado = false;
		coordenadas = new Vector2();
	}
	
	/**
	 * Este método se sobreescribe con la lógica de cada tipo de botón
	 */
	public void update(){}
	
	/**
	 * Devuelve el estado del botón
	 * @return True si el botón está pulsado, false si no.
	 */
	public boolean estado(){
		if(pulsado) return true;
		else return false;
	}
	
	/**
	 * Se encarga de desactivar el botón
	 */
	public void terminarAccion(){
		pulsado = false;
	}
	
	/**
	 * Modifica las coordenadas del botón
	 * @param x
	 * @param y
	 */
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	/**
	 * Devuelve la coordenada X del botón
	 * @return 
	 */
	public float coordenadaX(){
		return coordenadas.x;
	}
	
	/**
	 * Devuelve la coordenada Y del botón
	 * @return
	 */
	public float coordenadaY(){
		return coordenadas.y;
	}
}
