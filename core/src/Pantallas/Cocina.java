package Pantallas;

import java.util.Iterator;

import Items.Azucar;
import Items.Botella;
import Items.Caramelo;
import Items.Objeto;
import Objetos.Cursor;
import Personajes.Mujer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class Cocina extends Habitacion {
	
	private static MyGdxGame game;
	
	private static Objeto botella = new Botella(game);
	private static Objeto azucar = new Azucar(game);
	private static Objeto caramelos = new Caramelo(game);
	
	private static Cocina unicaInstancia;
	
	private Cocina(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitacion
		
		objetos.add(azucar);
		objetos.add(botella);
		objetos.add(caramelos);
		
		azucar.setCoordenadas(850, 350);
		botella.setCoordenadas(140, 290);
		caramelos.setCoordenadas(1000, 260);
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		if(!MyGdxGame.SUSPENSE_AMBIENTE){
			//Actores
			personaje = Mujer.getInstancia();
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
		pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/cocina.png"));
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static Cocina getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Cocina(game, c);
		}
		
		return unicaInstancia;
	}
}