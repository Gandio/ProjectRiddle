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

public final class Atico extends Habitacion {
	
	private static MyGdxGame game;
	private static Atico unicaInstancia;
	
	private static Objeto ataud = new Ataud(game);
	private static Objeto cuadro = new Cuadro(game);
	private static Objeto navaja = new Navaja(game);
	
	private Atico(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
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
		pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/atico.png"));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static Atico getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Atico(game, c);
		}
		
		return unicaInstancia;
	}
}
