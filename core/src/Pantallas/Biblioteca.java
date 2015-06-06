package Pantallas;

import java.util.Iterator;

import Items.Libro;
import Items.Objeto;
import Objetos.Cursor;
import Pantallas.Habitacion.Estado;
import Personajes.Dummie;
import Personajes.Mujer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class Biblioteca extends Habitacion {
	
	private static MyGdxGame game;
	private static Biblioteca unicaInstancia;
	
	//private static Objeto flor = new Flor(game);
	
	private Biblioteca(MyGdxGame game, Cursor c) {
		super(game, c);
		this.game = game;
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitacion
		
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		//Actores
		personaje = Mujer.getInstancia();
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
		pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/Biblioteca.png"));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static Biblioteca getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Biblioteca(game, c);
		}
		
		return unicaInstancia;
	}
}
