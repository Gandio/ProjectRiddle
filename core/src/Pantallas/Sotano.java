package Pantallas;

import java.util.Iterator;

import Items.BotellaVacia;
import Items.Daga;
import Items.Objeto;
import Items.Veneno;
import Objetos.Cursor;
import Personajes.Dummie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class  Sotano extends Habitacion {
	
	private static MyGdxGame game;
	private static Sotano unicaInstancia;
	private static Objeto botella = new BotellaVacia(game);
	private static Objeto daga = new Daga(game);
	private static Objeto veneno = new Veneno(game);
	
	private Sotano(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
		objetos = new Array<Objeto>();
		/*
		//Actores
		personaje = new Dummie(game);
		personaje.setCoordenadas(300, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
		*/
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		objetos.add(botella);
		objetos.add(daga);
		objetos.add(veneno);
		
		botella.setCoordenadas(150, 300);
		daga.setCoordenadas(400, 270);
		veneno.setCoordenadas(1150, 10);
		
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
		pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/sotano.png"));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static Sotano getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Sotano(game, c);
		}
		
		return unicaInstancia;
	}
}
