package Pantallas;

import java.util.Iterator;

import Items.Anillo;
import Items.Basura;
import Items.Libro;
import Items.Mascara;
import Items.Objeto;
import Objetos.Cursor;
import Personajes.Hombre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Clase que representa al objeto dormitorio 
 * @author Francisco Madueño Chulián
 */

public final class Dormitorio extends Habitacion {
	
	private static TheHouseOfCrimes game;
	private static Dormitorio unicaInstancia;
	
	//Objetos con suspense
	private static Objeto mascara = new Mascara(game);
	private static Objeto basura = new Basura(game);
	
	//Objetos sin suspense
	private static Objeto libro = new Libro(game);
	private static Objeto anillo = new Anillo(game);
	
	/**
	 * Contructor de la clase Dormitorio
	 * @param game
	 * @param c
	 */
	
	private Dormitorio(TheHouseOfCrimes game, Cursor c) {
		super(Inicio.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		if(TheHouseOfCrimes.SUSPENSE_OBJETOS){
			objetos.add(mascara);
			objetos.add(basura);
			
			mascara.setCoordenadas(300, 170);
			basura.setCoordenadas(1070, 20);
		}else{
			objetos.add(libro);
			objetos.add(anillo);
			
			libro.setCoordenadas(300, 170);
			anillo.setCoordenadas(1070, 20);
		}
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		//Actores
		personaje = Hombre.getInstancia();
		personaje.setCoordenadas(0, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
		
		// Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	/**
	 * Este método se ejecuta todo el tiempo que el jugador permanezca en el dormitorio
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
	 * Muestra la textura del dormitorio, depende si el suspense está activado o no
	 */
	public void show() {
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_DORMITORIO_SUSPENSE));
		else
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_DORMITORIO));
	}

	public void pause() {}
	
	public void resume() {}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, si no se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Dormitorio getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Dormitorio(game, c);
		}
		
		return unicaInstancia;
	}
}