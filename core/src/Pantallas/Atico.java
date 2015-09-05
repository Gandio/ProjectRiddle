package Pantallas;

import java.util.Iterator;

import Items.Ataud;
import Items.Cuadro;
import Items.Navaja;
import Items.Objeto;
import Objetos.Cursor;
import Personajes.Niña;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Clase que representa al objeto ático
 * @author Francisco Madueño Chulián
 */

public final class Atico extends Habitacion {
	
	private static MyGdxGame game;
	private static Atico unicaInstancia;
	
	private static Objeto ataud = new Ataud(game);
	private static Objeto cuadro = new Cuadro(game);
	private static Objeto navaja = new Navaja(game);
	
	/**
	 * Contructor de la clase Atico
	 * @param game
	 * @param c
	 */
	
	private Atico(MyGdxGame game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		objetos.add(ataud);
		objetos.add(cuadro);
		objetos.add(navaja);
		
		ataud.setCoordenadas(900, 40);
		cuadro.setCoordenadas(450, 225);
		navaja.setCoordenadas(600, 150);
		
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		//Actores
		if(MyGdxGame.SUSPENSE_AMBIENTE){
			personaje = Niña.getInstancia();
			personaje.setCoordenadas(450, 0);
		
			//añadimos los actores
			stage.addActor(personaje);
		}
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
		pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/atico.png"));
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
