package Pantallas;

import java.util.Iterator;

import Items.Basura;
import Items.Caramelo;
import Items.Espejo;
import Items.Libro;
import Items.Mascara;
import Items.Objeto;
import Items.Reloj;
import Objetos.Cursor;
import Personajes.Dummie;
import Personajes.Hombre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Clase que representa a los objetos Dormitorio
 * @author Francisco Madueño Chulián
 */

public final class Dormitorio extends Habitacion {
	
	private static MyGdxGame game;
	private static Dormitorio unicaInstancia;
	
	//Objetos con suspense
	private static Objeto mascara = new Mascara(game);
	private static Objeto basura = new Basura(game);
	
	//Objetos sin suspense
	private static Objeto reloj = new Reloj(game);
	private static Objeto espejo = new Espejo(game);
	private static Objeto libro = new Libro(game);
	
	/**
	 * Contructor de la clase Dormitorio
	 * @param game
	 * @param c
	 */
	
	private Dormitorio(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		if(MyGdxGame.SUSPENSE){
			objetos.add(mascara);
			objetos.add(basura);
			
			mascara.setCoordenadas(300, 170);
			basura.setCoordenadas(800, 100);
		}else{
			objetos.add(espejo);
			objetos.add(libro);
			objetos.add(reloj);
			
			espejo.setCoordenadas(400, 430);
			libro.setCoordenadas(1000, 100);
			reloj.setCoordenadas(100, 270);
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
		personaje.setCoordenadas(450, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
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
		if(MyGdxGame.SUSPENSE){
			pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/dormitorio.png"));
		}else{
			pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/dormitorioSin.png"));
		}
		
		
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
	
	public static Dormitorio getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Dormitorio(game, c);
		}
		
		return unicaInstancia;
	}
}
