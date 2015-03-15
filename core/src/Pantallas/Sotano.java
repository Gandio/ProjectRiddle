package Pantallas;

import Personajes.Dummie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class Sotano extends Habitacion {
	
	private MyGdxGame game;
	
	public Sotano(MyGdxGame game) {
		super(game);
		this.game = game;
		
		//Actores
		personaje = new Dummie(game);
		personaje.setCoordenadas(300, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		//si vamos a conversar con el personaje se debe mostrar el cuadro de texto
		if(personaje != null && estado == Estado.CONVERSAR){
			
		}
		
		stage.draw();
	}

	@Override
	public void show() {
		pantalla = new Texture(Gdx.files.internal("Imagenes/sotano.png"));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}
