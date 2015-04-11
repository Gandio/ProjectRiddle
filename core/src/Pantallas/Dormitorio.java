package Pantallas;

import java.util.Iterator;

import Items.Objeto;
import Objetos.Cursor;
import Personajes.Dummie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class Dormitorio extends Habitacion {
	
	private static MyGdxGame game;
	private static Dormitorio unicaInstancia;
	
	private Dormitorio(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
		objetos = new Array<Objeto>();
		
		//Actores
		personaje = new Dummie(game);
		personaje.setCoordenadas(300, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		//si vamos a conversar con el personaje se debe mostrar el cuadro de texto
		if(personaje != null && estado == Estado.CONVERSAR){
			
		}
		
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}

	@Override
	public void show() {
		pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/dormitorio.png"));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static Dormitorio getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Dormitorio(game, c);
		}
		
		return unicaInstancia;
	}
}
