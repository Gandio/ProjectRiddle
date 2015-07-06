package Pantallas;

import java.util.Iterator;

import Botones.BotonConversacion;
import Botones.BotonInvestigar;
import Botones.BotonPuertaHabitacion;
import Items.Objeto;
import Objetos.CuadroDialogo;
import Objetos.Cursor;
import Objetos.Puntuacion;
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
 * 
 * @author Francisco Madueño Chulián
 */
public abstract class Habitacion implements Screen {

	// Juego
	public static MyGdxGame game = Pasillo.game;
	protected Stage stage;
	protected Music musica;
	protected Texture pantalla;
	protected static Cursor c = Pasillo.getCursor();
	protected Array<Objeto> objetos;
	private boolean conversando = false;
	private boolean ultimoTexto = false;

	// Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; // se usa para adaptar la pantalla

	// Actores
	protected Personaje personaje;
	protected BotonInvestigar botonInvestigar;
	protected BotonConversacion botonConversacion;
	protected BotonPuertaHabitacion botonPuerta; // permite entrar en una habitación
	protected static CuadroDialogo cuadroTexto;

	// Puntuacion
	protected static Puntuacion puntuacion = Puntuacion.getInstancia();

	// Estado
	/*
	 * Esto controla el estado de la habitación: El estado normal es el inicial,
	 * de este estado se puede conversar o investigar Desde el estado conversar
	 * no se puede pasar al estado investigar, solo a normal Desde el estado
	 * investigar no se puede pasar al estado conversar, solo a normal
	 */
	public enum EstadoHabitacion {
		CONVERSAR, INVESTIGAR, NORMAL;
	};

	protected EstadoHabitacion estado;

	/**
	 * Constructor de la clase habitación.
	 * 
	 * @param game
	 */

	public Habitacion(MyGdxGame game, Cursor c) {
		estado = EstadoHabitacion.NORMAL;
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();

		// Musica
		if (MyGdxGame.SUSPENSE_MUSICA)
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/pasillo.mp3"));
		else
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/TemaSinSuspense.mp3"));
		musica.setLooping(true);

		// instanciamos la camara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f, 0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);

		Gdx.input.setInputProcessor(stage);

		// Actores
		botonConversacion = new BotonConversacion(game);
		botonConversacion.setTouchable(Touchable.enabled);

		botonInvestigar = new BotonInvestigar(game);
		botonInvestigar.setTouchable(Touchable.enabled);

		botonPuerta = new BotonPuertaHabitacion(game);
		botonPuerta.setTouchable(Touchable.enabled);

		cuadroTexto = new CuadroDialogo(game);

		// Añadimos actores
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

		// Posicion de botones
		botonPuerta.setCoordenadas(1150, 650);
		botonInvestigar.setCoordenadas(1050, 650);
		botonConversacion.setCoordenadas(950, 650);

		// Posicion cuadro texto
		cuadroTexto.setCoordenadas(0, 0);

		// Posicion de la puntuacion
		stage.addActor(puntuacion);
		puntuacion.setCoordenadas(30, 750);

		// ------------------------------------------------------------------------
		// ---------------------LOGICA DE LOS BOTONES------------------------------
		// ------------------------------------------------------------------------

		// Este orden se debe mantener si no intenta hacer cast de habitacion a
		// pasillo
		botonInvestigar.update();
		botonConversacion.update();
		botonPuerta.update();

		cuadroTexto.update();

		// ------------------------------------------------------------------------
		// ---------------------LOGICA DE LOS ESTADOS------------------------------
		// ------------------------------------------------------------------------

		Iterator<Objeto> iter = objetos.iterator();

		// Conversaciones
		if (estado == EstadoHabitacion.CONVERSAR) {
			stage.addActor(cuadroTexto);
			cuadroTexto.iniciarConversacion(stage);
			if (ultimoTexto) {
				cuadroTexto.getSigConv().remove();
				stage.addActor(cuadroTexto.getFinConv());
			}

		}

		// Se empieza a investigar y se puede interactuar con los objetos de la
		// habitacion
		if (estado == EstadoHabitacion.INVESTIGAR) {
			while (iter.hasNext()) {
				iter.next().seInvestiga(true);
			}
		} else {
			while (iter.hasNext()) {
				iter.next().seInvestiga(false);
			}
		}

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

	public void dispose() {
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
	 * 
	 * @return
	 */

	public EstadoHabitacion getEstado() {
		return estado;
	}

	/**
	 * Cambia el estado de la habitación.
	 * 
	 * @param e
	 */

	public void setEstado(EstadoHabitacion e) {
		estado = e;
	}
	
	/**
	 * Devuelve el cursor de la habitación.
	 * @return c
	 */

	public Cursor getCursor() {
		return c;
	}
	
	/**
	 * Devuelve el array de objetos de la habitacion
	 * @return objetos
	 */

	public Array<Objeto> getObjetos() {
		return objetos;
	}
	
	/**
	 * Para la música de la habitacion
	 */

	public void pararMusica() {
		musica.stop();
	}
	
	/**
	 * Comprueba si estamos o no conversando con el personaje de la habitacion
	 * @return conversando
	 */

	public boolean getConversando() {
		return conversando;
	}
	
	/**
	 * Cambia el atributo conversando a true o a false
	 * @param b
	 */

	public void setConversando(boolean b) {
		conversando = b;
	}
	
	/**
	 * Cambia el atributo ultimoTexto a true
	 */

	public void esUltimoTexto() {
		ultimoTexto = true;
	}
	
	/**
	 * Termina la conversación devolviendo los valores a sus estados iniciales
	 */

	public void terminarConversacion() {
		cuadroTexto.finConversacion();
		cuadroTexto.remove();
		ultimoTexto = false;
		estado = EstadoHabitacion.NORMAL;
	}
	
	/**
	 * Devuelve el cuadro de dialogo de la conversacion
	 * @return cuadroTexto
	 */

	public static CuadroDialogo getCuadroDialogo() {
		return cuadroTexto;
	}
	
	/**
	 * Devuelve el stage de la habitacion
	 * @return
	 */

	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Devuelve el personaje de la habitacion
	 * @return personaje
	 */

	public Personaje getPersonaje() {
		return personaje;
	}
}