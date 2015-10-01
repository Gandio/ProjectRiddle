package Pantallas;

import java.util.Iterator;

import Items.Bombilla;
import Items.Joya;
import Items.Llave;
import Items.Objeto;
import Items.Pistola;
import Items.Rifle;
import Items.Tabaco;
import Items.Zapato;
import Objetos.Cursor;
import Personajes.Anciana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheCrimeHouse;

/**
 * Clase que representa al objeto Salon
 * @author Francisco Madueño Chulián
 */

public final class Salon extends Habitacion {
	//Objetos con suspense
	private static Objeto pistola = new Pistola(game);
	private static Objeto rifle = new Rifle(game);
	private static Objeto tabaco = new Tabaco(game);
	
	//Objetos sin suspense
	private static Objeto llave = new Llave(game);
	private static Objeto joya = new Joya(game);
	private static Objeto zapato = new Zapato(game);
	
	private static Salon unicaInstancia;
	
	/**
	 * Contructor de la clase Salon
	 * @param game
	 * @param c
	 */
	
	private Salon(TheCrimeHouse game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitación
		if(TheCrimeHouse.SUSPENSE_OBJETOS){
			objetos.add(pistola);
			objetos.add(rifle);
			objetos.add(tabaco);
			
			pistola.setCoordenadas(600, 25);
			rifle.setCoordenadas(50, 100);
			tabaco.setCoordenadas(500, 240);
			
		}else{
			objetos.add(llave);
			objetos.add(joya);
			objetos.add(zapato);
			
			llave.setCoordenadas(500, 220);
			joya.setCoordenadas(50, 120);
			zapato.setCoordenadas(600, 25);
		}
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		//Actores
		personaje = Anciana.getInstancia();
		personaje.setCoordenadas(0, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
		
		// Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	/**
	 * Se ejecuta todo el tiempo que el jugador permanezca en el salón
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
	 * Muestra la textura del salón, varía si el suspense está activado o no
	 */

	public void show() {
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_SALON_SUSPENSE));
		else
			pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_SALON));
		
	}

	public void pause() {}

	public void resume() {}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, si no se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Salon getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Salon(game, c);
		}
		
		return unicaInstancia;
	}
}