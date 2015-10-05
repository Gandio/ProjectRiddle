package Pantallas;

import java.util.Iterator;

import Items.Azucar;
import Items.Bombilla;
import Items.Cafe;
import Items.Daga;
import Items.Jaula;
import Items.Objeto;
import Items.Veneno;
import Objetos.Cursor;
import Personajes.Joven;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Clase que representa al objeto Sotano
 * @author Francisco Madueño Chulián
 */

public final class  Sotano extends Habitacion {
	
	private static TheHouseOfCrimes game;
	private static Sotano unicaInstancia;
	
	//Estos son los objetos que se encuentran en el sótano
	private static Objeto daga = new Daga(game);
	private static Objeto veneno = new Veneno(game);
	private static Objeto jaula = new Jaula(game);
	
	private static Objeto cafe = new Cafe(game);
	private static Objeto azucar = new Azucar(game);
	private static Objeto bombilla = new Bombilla(game);
	
	/**
	 * Contructor de la clase Sotano
	 * @param game
	 * @param c
	 */
	
	private Sotano(TheHouseOfCrimes game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();

		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		if(TheHouseOfCrimes.SUSPENSE_OBJETOS){
			objetos.add(daga);
			objetos.add(veneno);
			objetos.add(jaula);
		
			daga.setCoordenadas(400, 260);
			veneno.setCoordenadas(175, 310);
			jaula.setCoordenadas(1150, 280);
		}else{
			objetos.add(azucar);
			objetos.add(bombilla);
			objetos.add(cafe);
			
			azucar.setCoordenadas(410, 260);
			
			if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
				cafe.setCoordenadas(175, 310);
			else
				cafe.setCoordenadas(160, 300);
				
			bombilla.setCoordenadas(1150, 280);
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
		personaje = Joven.getInstancia();
		
		//añadimos los actores
		stage.addActor(personaje);
		personaje.setCoordenadas(0, 0);
		//}
		
		// Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	/**
	 * Este método se ejecuta todo el tiempo que el jugador permanece en el sótano, comprueba
	 * si los objetos de la habitación han sido seleccionados por el jugador
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
	 * Muestra la textura de la habitación
	 */

	public void show() {
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_SOTANO_SUSPENSE));
		else
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_SOTANO));
		
	}

	public void pause() {}

	public void resume() {}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, si no se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Sotano getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Sotano(game, c);
		}
		
		return unicaInstancia;
	}
}