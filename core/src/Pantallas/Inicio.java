package Pantallas;

import Botones.BotonInicio;
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
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase representa la pantalla de título del juego.
 * @author Francisco Madueño Chulián
 *
 */

public class Inicio implements Screen{
	//Juego
	public static MyGdxGame game;
	protected Stage stage;
	protected Music musica;
	protected Texture pantalla;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantalla
	
	//Botón que permite iniciar una nueva partida
	private BotonInicio inicio;
	//Boton que permite salir del juego
	private BotonSalir salir;
	
	/**
	 * Constructor de la clase Inicio.
	 * @param game
	 */
	
	public Inicio(MyGdxGame game){
		Inicio.game = game;
		
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		//musica
		if(MyGdxGame.SUSPENSE_MUSICA)
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/titulo.mp3"));
		else
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/TituloSinSuspense.mp3"));
		
		musica.play();
		
		//instanciamos la cámara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f ,0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		//añadimos botones y hacemos que sean tocables
		inicio = new BotonInicio(game);
		inicio.setTouchable(Touchable.enabled);
		
		salir = new BotonSalir(game);
		salir.setTouchable(Touchable.enabled);
		
		Gdx.input.setInputProcessor(stage);
		
		//se añaden los botones
		stage.addActor(inicio);
		stage.addActor(salir);
	}
	
	/**
	 * Muestra la textura de la pantalla.
	 */

	public void show(){
		//Dependiendo de la variable cargamos una pantalla u otra
		if(MyGdxGame.SUSPENSE_AMBIENTE)
			pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/Titulo.png"));
		else
			pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/tituloSin.png"));
	}
	
	/**
	 * Este método se ejecuta constantemente, dibuja la pantalla con todos sus actores y comprueba
	 * si alguno de los botones ha sido pulsado.
	 */
	
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		batch.setProjectionMatrix(camara.combined);
		
		batch.begin();
		batch.draw(pantalla, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		//Estamos constantemente comprobando el estado de los botones
		inicio.esPulsado();
		salir.esPulsado();
		
		/*
		 * La situación de los botones también dependen de esta variable
		 */
		if(MyGdxGame.SUSPENSE_AMBIENTE){
			inicio.setCoordenadas(460, 170);
			salir.setCoordenadas(580, 100);
		}else{
			inicio.setCoordenadas(420, 180);
			salir.setCoordenadas(540, 100);
		}
	}
	
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	public void dispose() {
		batch.dispose();
		stage.dispose();
		pantalla.dispose();
		musica.dispose();
	}

	public void hide() {}

	public void pause() {}

	public void resume() {}
}