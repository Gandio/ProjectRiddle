package Pantallas;

import java.util.Iterator;

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
import com.mygdx.game.MyGdxGame;

/**
 * Clase que representa a los objetos Sotano
 * @author Francisco Madueño Chulián
 */

public final class  Sotano extends Habitacion {
	
	private static MyGdxGame game;
	private static Sotano unicaInstancia;
	
	private static Objeto daga = new Daga(game);
	private static Objeto veneno = new Veneno(game);
	private static Objeto jaula = new Jaula(game);
	
	/**
	 * Contructor de la clase Sotano
	 * @param game
	 * @param c
	 */
	
	private Sotano(MyGdxGame game, Cursor c) {
		super(game, c);
		Sotano.game = game;
		objetos = new Array<Objeto>();

		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		objetos.add(daga);
		objetos.add(veneno);
		objetos.add(jaula);
		
		daga.setCoordenadas(400, 240);
		veneno.setCoordenadas(175, 290);
		jaula.setCoordenadas(1150, 280);
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		if(MyGdxGame.SUSPENSE_AMBIENTE){
			//Actores
			personaje = Joven.getInstancia();
		
			//añadimos los actores
			stage.addActor(personaje);
			personaje.setCoordenadas(450, 0);
		}
	}
	
	/**
	 * Este método se ejecuta todo el tiempo que el jugador permanece en el sotano, comprueba
	 * si los objetos de la habitacion han sido seleccionados por el jugador
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
		pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/sotano.png"));
		
	}

	public void pause() {}

	public void resume() {}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, sino se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Sotano getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Sotano(game, c);
		}
		
		return unicaInstancia;
	}
}