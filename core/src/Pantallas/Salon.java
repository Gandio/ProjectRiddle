package Pantallas;

import java.util.Iterator;

import Items.Bala;
import Items.Bombilla;
import Items.Botella;
import Items.Llave;
import Items.Objeto;
import Objetos.Cursor;
import Personajes.Anciana;
import Personajes.Dummie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class Salon extends Habitacion {
	//private static Objeto botellaVacia = new BotellaVacia(game);
	
	//Objetos con suspense
	private static Objeto bala = new Bala(game);

	
	//Objetos sin suspense
	private static Objeto llave = new Llave(game);
	private static Objeto bombilla = new Bombilla(game);
	
	private static Salon unicaInstancia;
	
	private Salon(MyGdxGame game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitacion
		if(MyGdxGame.SUSPENSE){
			objetos.add(bala);
			
			bala.setCoordenadas(1100, 180);
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
	
	@Override
	public void render(float delta) {
		super.render(delta);

		//si vamos a conversar con el personaje se debe mostrar el cuadro de texto
		if(personaje != null && estado == Estado.CONVERSAR){
			
		}
		
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
			pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/salon.png"));
		}else{
			pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/salonSin.png"));
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
	
	public static Salon getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Salon(game, c);
		}
		
		return unicaInstancia;
	}
}
