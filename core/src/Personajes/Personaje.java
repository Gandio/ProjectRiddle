package Personajes;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

/*
 * Al igual que las habitaciones, los personajes tienen que ser únicos, se incluyen todos los textos que se 
 * almacenan en un xml y se muestran dependiendo del estado del juego
 */

/**
 * Clase abstracta que representa a los personajes que participan en una patida
 * @author Francisco Madueño Chulián
 *
 */

public abstract class Personaje extends Actor{
	private MyGdxGame game;
	protected Texture personaje;
	protected Vector2 posicion;
	protected String textoActual;
	
	
	//Estos atributos son necesarios para cargar los dialogos de los personajes
	protected File textosXml;
	protected DocumentBuilderFactory dbfactory;
	protected DocumentBuilder dBuilder;
	protected Document doc;
	
	/**
	 * Constructor de la clase personaje
	 * @param game
	 */
	
	public Personaje(MyGdxGame game){
		this.game = game;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(personaje, posicion.x, posicion.y);
	}
	
	/**
	 * Este método se usa para modificar las coordenadas del personaje
	 * @param x
	 * @param y
	 */
	
	public void setCoordenadas(float x, float y){
		posicion.x = x;
		posicion.y = y;
	}
	
	/**
	 * Devuelve la coordenada x del personaje
	 * @return x
	 */
	
	public float coordenadaX(){
		return posicion.x;
	}
	
	
	/**
	 * Devuelve la coordenada y del personaje
	 * @return y
	 */
	public float coordenadaY(){
		return posicion.y;
	}
}
