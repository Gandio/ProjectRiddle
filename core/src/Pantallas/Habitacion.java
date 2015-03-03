package Pantallas;

import Objetos.Boton;
import Objetos.BotonConversacion;
import Objetos.BotonInvestigar;
import Objetos.BotonPuertaHabitacion;
import Objetos.Personaje;

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
 * Esta clase abstracta generaliza todas las habitaciones del juego.
 * @author Francisco Madueño Chulián
 */
public abstract class Habitacion implements Screen{
	
	//Juego
	protected static MyGdxGame game;
	protected Stage stage;
	protected Music musica;
	protected Texture pantalla;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantalla
	
	//Actores
	protected Personaje personaje;
	protected BotonInvestigar botonInvestigar;
	protected BotonConversacion botonConversacion;
	private BotonPuertaHabitacion botonPuerta; //permite entrar en una habitación
	
	//Controladores
	//protected ControladorBotonConversacion controladorConversacion;
	//protected ControladorBotonInvestigar controladorInvestigar;
	
	//Estado
	/*
	 * Esto controla el estado de la habitación:
	 * El estado normal es el inicial, de este estado se puede conversar o investigar
	 * Desde el estado conversar no se puede pasar al estado investigar, solo a normal
	 * Desde el estado investigar no se puede pasar al estado conversar, solo a normal
	 */
	public enum Estado{
		CONVERSAR, INVESTIGAR, NORMAL;
	};
	
	private Estado estado;
	
	public Habitacion(MyGdxGame game) {
		estado = Estado.NORMAL;
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		this.game = game;
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		//Musica
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/pasillo.mp3"));
		musica.setLooping(true);
		musica.play();
		
		//instanciamos la camara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f ,0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		Gdx.input.setInputProcessor(stage);
		
		//Actores
		botonConversacion = new BotonConversacion(game);
		botonConversacion.setTouchable(Touchable.enabled);
		
		botonInvestigar = new BotonInvestigar(game);
		botonInvestigar.setTouchable(Touchable.enabled);
		
		botonPuerta = new BotonPuertaHabitacion(game);
		botonPuerta.setTouchable(Touchable.enabled);
		
		//Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		batch.setProjectionMatrix(camara.combined);
		
		batch.begin();
		batch.draw(pantalla, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		//Posicion de botones
		botonPuerta.setCoordenadas(200, 200);
		botonInvestigar.setCoordenadas(250, 200);
		botonConversacion.setCoordenadas(300, 200);
		
		//controladorConversacion.update();
		//controladorInvestigar.update();
		
		botonPuerta.update();
		botonInvestigar.update();
		botonConversacion.update();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	public void dispose(){
		batch.dispose();
		stage.dispose();
		pantalla.dispose();
		musica.dispose();
	}
	
	/*----------------------------------------------------------------
	 * -----------------------FUNCIONES AUXILIARES--------------------
	 * ---------------------------------------------------------------
	 */
	
	public Estado getEstado(){
		return estado;
	}
	
	public void setEstado(Estado e){
		estado = e;
	}
	
	//---------------------------SOLO VALE HASTA AQUÍ--------------------------
	
	public Boton getBotonConversacion(){
		return botonConversacion;
	}
	
	public Boton getBotonInvestigar(){
		return botonInvestigar;
	}
	
	/*public ControladorBotonInvestigar getControladorInvestigar(){
		return controladorInvestigar;
	}*/
	
	/*public ControladorBotonConversacion getControladorConversacion(){
		return controladorConversacion;
	}*/
	
	public Personaje getPersonaje(){
		return personaje;
	}
}