package Pantallas;

import java.util.Iterator;

import Items.Anillo;
import Items.Joya;
import Items.Objeto;
import Items.Zapato;
import Objetos.Cursor;
import Personajes.Joven;
import Personajes.Niña;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Clase que representa a los objetos Baño
 * @author Francisco Madueño Chulián
 */

public final class Baño extends Habitacion {
	
	private static MyGdxGame game;
	
	private static Objeto zapato = new Zapato(game);
	private static Objeto anillo = new Anillo(game);
	private static Objeto joya = new Joya(game);
	
	private static Baño unicaInstancia;
	
	/**
	 * Contructor de la clase Baño
	 * @param game
	 * @param c
	 */
	
	private Baño(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitacion
		objetos.add(anillo);
		objetos.add(joya);
		objetos.add(zapato);
		
		anillo.setCoordenadas(1000, 50);
		joya.setCoordenadas(75, 50);
		zapato.setCoordenadas(800, 130);
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		if(!MyGdxGame.SUSPENSE_AMBIENTE){
			//Actores
			personaje = Joven.getInstancia();
			personaje.setCoordenadas(450, 0);
		
			//añadimos los actores
			stage.addActor(personaje);
		}
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		//si vamos a conversar con el personaje se debe mostrar el cuadro de texto
		/*if(personaje != null && estado == Estado.CONVERSAR){
			
		}*/
		
		Iterator<Objeto> iterObjetos = objetos.iterator();
		while(iterObjetos.hasNext()){
			iterObjetos.next().seSelecciona();
		}
		
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}

	@Override
	public void show() {
		pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/bathroom.png"));
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Para que solo haya un único objeto en el juego se debe hacer que el contructor sea privado.
	 * Si la habitación está creada no hace nada, sino se llama al contructor.
	 * @return unicaInstancia
	 */
	
	public static Baño getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Baño(game, c);
		}
		
		return unicaInstancia;
	}
}
