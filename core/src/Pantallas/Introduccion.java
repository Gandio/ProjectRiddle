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
import com.mygdx.game.MyGdxGame;

public class Introduccion implements Screen {
	
	private Stage stage;
	private Texture textura;
	private MyGdxGame game = Inicio.game;
	private Music musica;
	
	// Camaras
	private OrthographicCamera camara;
	public SpriteBatch batch;
	private FillViewport viewport; // se usa para adaptar la pantalla
	
	//Actores
	private BotonContinuar botonContinuar;
	
	public Introduccion() {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		Gdx.input.setInputProcessor(stage);
		
		textura = new Texture(Gdx.files.internal("Imagenes/pruebaFondoPasillo.png")); 
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Tension.mp3"));
		musica.play();
		
		// instanciamos la camara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f, 0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		//Actores
		botonContinuar = new BotonContinuar(game);
		botonContinuar.setTouchable(Touchable.enabled);
		
		stage.addActor(botonContinuar);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camara.update();
		batch.setProjectionMatrix(camara.combined);
		batch.begin();
		batch.draw(textura, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		botonContinuar.esPulsado();
		botonContinuar.setCoordenadas(300, 300);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
		textura.dispose();
		musica.dispose();
		
	}
}
