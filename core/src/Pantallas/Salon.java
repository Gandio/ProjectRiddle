package Pantallas;

import java.util.Iterator;

import Items.Bombilla;
import Items.Llave;
import Items.Objeto;
import Items.Pistola;
import Items.Rifle;
import Items.Tabaco;
import Objetos.Cursor;
import Personajes.Anciana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

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
	private static Objeto bombilla = new Bombilla(game);
	
	private static Salon unicaInstancia;
	
	/**
	 * Contructor de la clase Salon
	 * @param game
	 * @param c
	 */
	
	private Salon(MyGdxGame game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitación
		if(MyGdxGame.SUSPENSE){
			objetos.add(pistola);
			objetos.add(rifle);
			objetos.add(tabaco);
			
			pistola.setCoordenadas(1120, 15);
			rifle.setCoordenadas(350, 10);
			tabaco.setCoordenadas(500, 240);
			
		}else{
			objetos.add(llave);
			objetos.add(bombilla);
			
			llave.setCoordenadas(500, 220);
			bombilla.setCoordenadas(50, 250);
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
		personaje.setCoordenadas(450, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
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
		if(MyGdxGame.SUSPENSE_AMBIENTE){
			pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/salon.png"));
		}else{
			pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/salonSin.png"));
		}
		
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