package Pantallas;

import Botones.BotonSalirCreditos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheCrimeHouse;

/**
 * Esta pantalla representa la pantalla de créditos del juego.
 * @author Francisco Madueño Chulián
 */

public class Creditos implements Screen{
	
	// Juego
	public static TheCrimeHouse game = Inicio.game;
	private Stage stage;
	private Texture pantalla;
	
	// Camaras
	private OrthographicCamera camara;
	public SpriteBatch batch;
	private FillViewport viewport; // se usa para adaptar la pantalla
	
	private BotonSalirCreditos botonSalir;
	
	/**
	 * Contructor de la clase
	 */
	
	public Creditos(){
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(stage);
		
		pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_FINAL)); 
		
		// instanciamos la cámara
		camara.position.set(TheCrimeHouse.WIDTH / 2f, TheCrimeHouse.HEIGHT / 2f, 0);
		viewport = new FillViewport(TheCrimeHouse.WIDTH, TheCrimeHouse.HEIGHT, camara);
		
		botonSalir = new BotonSalirCreditos(game);
		botonSalir.setTouchable(Touchable.enabled);
		
		stage.addActor(botonSalir);
	}
	
	/**
	 * Se dibuja la pantalla y se comprueban los botones durante el tiempo que el jugador
	 * permanezca en esta.
	 */

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camara.update();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		batch.draw(pantalla, 0, 0, TheCrimeHouse.WIDTH, TheCrimeHouse.HEIGHT);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		botonSalir.setCoordenadas(50, 50);
		botonSalir.update();
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
		pantalla.dispose();
	}
}
