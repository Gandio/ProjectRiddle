package Pantallas;

import java.util.Iterator;

import Botones.BotonConversacion;
import Botones.BotonInvestigar;
import Botones.BotonPuertaHabitacion;
import Items.Objeto;
import Objetos.CuadroDialogo;
import Objetos.CuadroTexto;
import Objetos.Cursor;
import Personajes.Personaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase abstracta generaliza todas las habitaciones del juego.
 * @author Francisco Madueño Chulián
 */
public abstract class Habitacion implements Screen{
	
	//Juego
	public static MyGdxGame game = Pasillo.game;
	protected Stage stage;
	protected Music musica;
	protected Texture pantalla;
	protected static Cursor c = Pasillo.getCursor();
	protected Array<Objeto> objetos;
	private boolean conversando = false;
	private boolean ultimoTexto = false;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantalla
	
	//Actores
	protected Personaje personaje;
	protected BotonInvestigar botonInvestigar;
	protected BotonConversacion botonConversacion;
	protected BotonPuertaHabitacion botonPuerta; //permite entrar en una habitación
	protected CuadroDialogo cuadroTexto;
	
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
	
	protected Estado estado;
	
	/**
	 * Constructor de la clase habitación.
	 * @param game
	 */
	
	public Habitacion(MyGdxGame game, Cursor c) {
		estado = Estado.NORMAL;
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		//Musica
		if(MyGdxGame.SUSPENSE)
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/pasillo.mp3"));
		else
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/TemaSinSuspense.mp3"));
		musica.setLooping(true);
		
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
		
		cuadroTexto = new CuadroDialogo(game);
		
		//Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	@Override
	public void render(float delta) {
		musica.play();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		batch.setProjectionMatrix(camara.combined);
		
		batch.begin();
		batch.draw(pantalla, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		//Posicion de botones
		botonPuerta.setCoordenadas(1150, 650);
		botonInvestigar.setCoordenadas(1050, 650);
		botonConversacion.setCoordenadas(950, 650);
		
		//Posicion cuadro texto
		cuadroTexto.setCoordenadas(0, 0);
		
		//------------------------------------------------------------------------
		//---------------------LOGICA DE LOS BOTONES------------------------------
		//------------------------------------------------------------------------
		
		//Este orden se debe mantener si no intenta hacer cast de habitacion a pasillo
		botonInvestigar.update();
		botonConversacion.update();
		botonPuerta.update();
		
		cuadroTexto.update();

		
		//------------------------------------------------------------------------
		//---------------------LOGICA DE LOS ESTADOS------------------------------
		//------------------------------------------------------------------------
		
		Iterator<Objeto> iter = objetos.iterator();
		
		//Conversaciones
		if(estado == Estado.CONVERSAR){
			stage.addActor(cuadroTexto);
			cuadroTexto.iniciarConversacion(stage);
			if(ultimoTexto){
				cuadroTexto.getSigConv().remove();
				stage.addActor(cuadroTexto.getFinConv());
			}
			
		}
		
		//Se empieza a investigar y se puede interactuar con los objetos de la habitacion
		if(estado == Estado.INVESTIGAR){
			while(iter.hasNext()){
				iter.next().seInvestiga(true);
			}
		}else{
			while(iter.hasNext()){
				iter.next().seInvestiga(false);
			}
		}
		
		System.out.println(estado);
		
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
		musica.stop();
		musica.dispose();
	}
	
	/*----------------------------------------------------------------
	 * -----------------------FUNCIONES AUXILIARES--------------------
	 * ---------------------------------------------------------------
	 */
	
	/**
	 * Devuelve el estado actual de la habitación.
	 * @return
	 */
	
	public Estado getEstado(){
		return estado;
	}
	
	/**
	 * Cambia el estado de la habitación.
	 * @param e
	 */
	
	public void setEstado(Estado e){
		estado = e;
	}
	
	public Cursor getCursor(){
		return c;
	}
	
	public Array<Objeto> getObjetos(){
		return objetos;
	}
	
	public void pararMusica(){
		musica.stop();
	}
	
	public boolean getConversando(){
		return conversando;
	}
	
	public void setConversando(boolean b){
		conversando = b;
	}
	
	public void esUltimoTexto(){
		ultimoTexto = true;
	}
	
	public void terminarConversacion(){
		cuadroTexto.finConversacion();
		cuadroTexto.remove();
		ultimoTexto = false;
		estado = Estado.NORMAL;
	}
	
	public CuadroDialogo getCuadroDialogo(){
		return cuadroTexto;
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public Personaje getPersonaje(){
		return personaje;
	}
}