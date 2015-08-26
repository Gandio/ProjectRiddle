package Pantallas;

import java.util.Iterator;

import Items.Boligrafo;
import Items.Cafe;
import Items.Moneda;
import Items.Objeto;
import Objetos.Cursor;
import Personajes.Niña;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Clase que representa a los objetos Estudio
 * @author Francisco Madueño Chulián
 */

public final class Estudio extends Habitacion {
	
	private static MyGdxGame game;
	
	private static Objeto moneda = new Moneda(game);
	private static Objeto cafe = new Cafe(game);
	private static Objeto boligrafo = new Boligrafo(game);
	
	private static Estudio unicaInstancia;
	
	/**
	 * Contructor de la clase Estudio
	 * @param game
	 * @param c
	 */
	
	private Estudio(MyGdxGame game, Cursor c) {
		super(game, c);
		Estudio.game = game;
		objetos = new Array<Objeto>();
	
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitación
		objetos.add(boligrafo);
		objetos.add(cafe);
		objetos.add(moneda);
		
		boligrafo.setCoordenadas(1150, 140);
		cafe.setCoordenadas(450, 260);
		moneda.setCoordenadas(20, 50);
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		if(!MyGdxGame.SUSPENSE_AMBIENTE){
			//Actores
			personaje = Niña.getInstancia();
			personaje.setCoordenadas(450, 0);
		
			//añadimos los actores
			stage.addActor(personaje);
		}
		
	}
	
	/**
	 * Este método se ejecuta durante todo el tiempo que el jugador permanezca en el 
	 * estudio
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

	public void show() {
		pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/estudio.png"));
	}

	public void pause() {}

	public void resume() {}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, si no se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Estudio getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Estudio(game, c);
		}
		
		return unicaInstancia;
	}
}