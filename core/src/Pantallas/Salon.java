package Pantallas;

import java.util.Iterator;

import Items.Bala;
import Items.BotellaVacia;
import Items.BotellaWhisky;
import Items.Llave;
import Items.Objeto;
import Objetos.Cursor;
import Personajes.Dummie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class Salon extends Habitacion {
	//private static Objeto botellaVacia = new BotellaVacia(game);
	
	//Objetos con suspense
	private static Objeto botellaWhisky = new BotellaWhisky(game);
	private static Objeto bala = new Bala(game);
	private static Objeto llave = new Llave(game);
	
	//Objetos sin suspense
	
	
	private static Salon unicaInstancia;
	
	private Salon(MyGdxGame game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();

		//Actores
		personaje = new Dummie(game);
		personaje.setCoordenadas(300, 0);
		
		
		//añadimos los actores
		stage.addActor(personaje);
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitacion
		if(MyGdxGame.SUSPENSE){
			objetos.add(bala);
			objetos.add(botellaWhisky);
			objetos.add(llave);
			
			bala.setCoordenadas(1100, 180);
			botellaWhisky.setCoordenadas(970, 400);
			llave.setCoordenadas(500, 220);
		}else{}
		
		//botellaVacia.setCoordenadas(300, 300);
		//objetos.add(botellaVacia);
		
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
