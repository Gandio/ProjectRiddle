package Pantallas;

import java.util.Iterator;

import Items.Ataud;
import Items.Boligrafo;
import Items.Cuadro;
import Items.Espejo;
import Items.Navaja;
import Items.Objeto;
import Items.Reloj;
import Objetos.Cursor;
import Personajes.Niña;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Clase que representa al objeto ático
 * @author Francisco Madueño Chulián
 */

public final class Atico extends Habitacion {
	
	private static TheHouseOfCrimes game;
	private static Atico unicaInstancia;
	
	private static Objeto ataud = new Ataud(game);
	private static Objeto cuadro = new Cuadro(game);
	private static Objeto navaja = new Navaja(game);
	
	private static Objeto reloj = new Reloj(game);
	private static Objeto espejo = new Espejo(game);
	private static Objeto boligrafo = new Boligrafo(game);
	
	/**
	 * Contructor de la clase Atico
	 * @param game
	 * @param c
	 */
	
	private Atico(TheHouseOfCrimes game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		if(TheHouseOfCrimes.SUSPENSE_OBJETOS){
			objetos.add(ataud);
			objetos.add(cuadro);
			objetos.add(navaja);
		
			cuadro.setCoordenadas(450, 225);
			
			if(TheHouseOfCrimes.SUSPENSE_AMBIENTE){
				navaja.setCoordenadas(600, 120);
				ataud.setCoordenadas(755, 0);
			}else{
				navaja.setCoordenadas(600, 140);
				ataud.setCoordenadas(770, -10);
			}
			
		}else{
			objetos.add(boligrafo);
			objetos.add(espejo);
			objetos.add(reloj);
			
			boligrafo.setCoordenadas(600, 140);
			espejo.setCoordenadas(450, 225);
			reloj.setCoordenadas(850, 0);
		}
		
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		//if(MyGdxGame.SUSPENSE_AMBIENTE){
		//Actores
		personaje = Niña.getInstancia();
		personaje.setCoordenadas(0, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
		//}
		
		// Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	/**
	 * Este método se ejecuta durante todo el tiempo que el jugador permanzca en el ático
	 */
	
	public void render(float delta) {
		super.render(delta);
		
		Iterator<Objeto> iterObjetos = objetos.iterator();
		while(iterObjetos.hasNext()){
			iterObjetos.next().seSelecciona();
		}
		
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}
	
	/**
	 * Muestra la textura del ático
	 */

	public void show() {
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_ATICO_SUSPENSE));
		else
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_ATICO));
	}

	public void pause() {}

	public void resume() {}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, si no se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Atico getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Atico(game, c);
		}
		
		return unicaInstancia;
	}
}
