package Pantallas;

import Botones.BotonSalirDiccionario;

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

public class Diccionario implements Screen{
	// Juego
	public static TheHouseOfCrimes game = Inicio.game;
	private Stage stage;
	private Texture pantalla;
	private Music musica;
	
	// Camaras
	private OrthographicCamera camara;
	public SpriteBatch batch;
	private FillViewport viewport; // se usa para adaptar la pantalla
	
	private BotonSalirDiccionario salirDiccionario;
	
	public Diccionario(){
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(stage);
		
		pantalla = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_DICCIONARIO)); 
		
		salirDiccionario = new BotonSalirDiccionario(game);
		salirDiccionario.setTouchable(Touchable.enabled);
		
		// instanciamos la cámara
		camara.position.set(TheHouseOfCrimes.WIDTH / 2f, TheHouseOfCrimes.HEIGHT / 2f, 0);
		viewport = new FillViewport(TheHouseOfCrimes.WIDTH, TheHouseOfCrimes.HEIGHT, camara);
		
		// Música
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Inventario.mp3"));
		musica.setLooping(true);
		
		stage.addActor(salirDiccionario);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camara.update();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		batch.draw(pantalla, 0, 0, TheHouseOfCrimes.WIDTH, TheHouseOfCrimes.HEIGHT);
		batch.end();
		
		salirDiccionario.update();
		salirDiccionario.setCoordenadas(20, 10);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		musica.play();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	public void show() {}
	public void hide() {}
	public void pause() {}
	public void resume() {}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		pantalla.dispose();
		musica.dispose();
	}
}
