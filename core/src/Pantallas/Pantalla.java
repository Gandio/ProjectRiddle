package Pantallas;

import Controladores.ControladorBotonPuerta;
import Objetos.Boton;
import Objetos.BotonPuerta;
import Objetos.Cursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase se usa para generalizar las pantallas y que no haga falta diferenciar entre
 * pasillo y habitación.
 * @author Francisco Madueño Chulián
 *
 */

public abstract class Pantalla implements Screen{
	//Juego
	protected static MyGdxGame game;
	protected Stage stage;
	protected Music musica;
	protected Boton botonPuerta; //permite entrar en una habitación
	protected Texture pantalla;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantalla
	
	//Controladores
	protected ControladorBotonPuerta controladorBotonPuerta;
	
	public Pantalla(MyGdxGame game) {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		this.game = game;
		
		botonPuerta = new BotonPuerta(game);
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		stage.addActor(botonPuerta);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		batch.setProjectionMatrix(camara.combined);
	}
	
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}
	
	public void hide(){}
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose() {
		batch.dispose();
		stage.dispose();
		pantalla.dispose();
	}
}