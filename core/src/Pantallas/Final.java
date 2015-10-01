package Pantallas;

import Botones.BotonSalir;

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
import com.mygdx.game.TheCrimeHouse;

/**
 * Esta clase representa la última pantalla del juego. Desde esta pantalla se puede salir del 
 * juego.
 * @author Francisco Madueño Chulián
 *
 */

public class Final implements Screen{
	private Stage stage;
	private Texture textura;
	private TheCrimeHouse game = Inicio.game;
	private Music musica;
	
	// Camaras
	private OrthographicCamera camara;
	public SpriteBatch batch;
	private FillViewport viewport; // se usa para adaptar la pantalla
	
	private BotonSalir salir;
	
	/**
	 * Contructor de la clase
	 */
	
	public Final(){
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(stage);
		
		textura = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_FINAL)); 
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Tension.mp3"));
		musica.play();
		
		// instanciamos la cámara
		camara.position.set(TheCrimeHouse.WIDTH / 2f, TheCrimeHouse.HEIGHT / 2f, 0);
		viewport = new FillViewport(TheCrimeHouse.WIDTH, TheCrimeHouse.HEIGHT, camara);
		
		salir = new BotonSalir(game, true);
		salir.setTouchable(Touchable.enabled);
		
		stage.addActor(salir);
	}
	
	/**
	 * Este método se ejecuta todo el tiempo que el jugador permanezca en la pantalla. La dibuja
	 * y actualiza los botones
	 */

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camara.update();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		batch.draw(textura, 0, 0, TheCrimeHouse.WIDTH, TheCrimeHouse.HEIGHT);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		salir.setCoordenadas(1000, 25);
		salir.esPulsado();
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
