package Pantallas;

import Botones.BotonContinuar;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Esta clase representa la pantalla de introducción al juego. Aquí se explicará en que 
 * consiste el juego y cual es el objetivo del mismo. Tiene un botón que permite entrar en 
 * la partida y comenzar a jugar.
 * @author Francisco Madueño Chulian
 */

public class Introduccion implements Screen {
	private Stage stage;
	private Texture textura;
	private TheHouseOfCrimes game = Inicio.game;
	private Music musica;
	
	// Camaras
	private OrthographicCamera camara;
	public SpriteBatch batch;
	private FillViewport viewport; // se usa para adaptar la pantalla
	
	//Actores
	private BotonContinuar botonContinuar; //este botón se usa para entrar en la partida
	
	/**
	 * Constructor de la clase
	 */
	
	public Introduccion() {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(stage);
		
		textura = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_INTRO)); 
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Tension.mp3"));
		musica.play();
		
		// instanciamos la cámara
		camara.position.set(TheHouseOfCrimes.WIDTH / 2f, TheHouseOfCrimes.HEIGHT / 2f, 0);
		viewport = new FillViewport(TheHouseOfCrimes.WIDTH, TheHouseOfCrimes.HEIGHT, camara);
		
		//Actores
		botonContinuar = new BotonContinuar(game);
		botonContinuar.setTouchable(Touchable.enabled);
		
		stage.addActor(botonContinuar);
	}
	
	/**
	 * Este bucle se ejecuta durante todo el tiempo que el jugador permanezca en la 
	 * pantalla. Comprueba continuamente si se ha pulsado el botón continuar.
	 */

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camara.update();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		batch.draw(textura, 0, 0, TheHouseOfCrimes.WIDTH, TheHouseOfCrimes.HEIGHT);
		batch.end();
		
		botonContinuar.esPulsado();
		botonContinuar.setCoordenadas(900, 0);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	public void show() {}

	public void hide() {}
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose() {
		batch.dispose();
		stage.dispose();
		textura.dispose();
		musica.dispose();
		
	}
}