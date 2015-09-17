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
import Puzzle.CuadroEleccion;

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
import com.mygdx.game.OrganizadorEstados;

/**
 * Esta clase abstracta generaliza todas las habitaciones del juego.
 * 
 * @author Francisco Madueño Chulián
 */
public abstract class Habitacion implements Screen {

	// Juego
	public static MyGdxGame game = Inicio.game;
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
	protected CuadroDialogo cuadroTexto;
	protected Array<CuadroEleccion> cuadrosEleccion;
	
	// Puntuacion
	protected static Puntuacion puntuacion = Puntuacion.getInstancia();
	
	//Organizador de estados
	public static OrganizadorEstados organizador = OrganizadorEstados.getInstancia();

	// Estado
	/*
	 * Esto controla el estado de la habitación: El estado normal es el inicial,
	 * de este estado se puede conversar o investigar Desde el estado conversar
	 * no se puede pasar al estado investigar, solo a normal Desde el estado
	 * investigar no se puede pasar al estado conversar, solo a normal
	 */
	public enum EstadoHabitacion {
		CONVERSAR, INVESTIGAR, NORMAL, DECISION;
	};
	
	//Estado actual de la habitación
	protected EstadoHabitacion estado;

	/**
	 * Constructor de la clase Habitacion.
	 * @param game
	 */

	public Habitacion(MyGdxGame game, Cursor c) {
		estado = EstadoHabitacion.NORMAL;
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();

		// Música
		/*if (MyGdxGame.SUSPENSE_MUSICA)
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/pasillo.mp3"));
		else*/
		
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/TemaSinSuspense.mp3"));
		musica.setLooping(true);

		// instanciamos la cámara
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

		cuadroTexto = new CuadroDialogo(Habitacion.game);
		
		cuadrosEleccion = new Array<CuadroEleccion>(4); //Hay cuatro posibles elecciones
		
		for(int i = 0; i < 4; ++i){
			cuadrosEleccion.add(new CuadroEleccion(game));
			cuadrosEleccion.get(i).setTouchable(Touchable.enabled);
		}

		// Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	/**
	 * Este método se ejecuta todo el tiempo que el jugador permanezca en una habitación
	 */

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

		// Este orden se debe mantener si no intenta hacer cast de habitación a
		// pasillo
		botonInvestigar.update();
		botonConversacion.update();
		botonPuerta.update();

		cuadroTexto.update();
		
		for(int i = 0; i < 4; ++i){
			cuadrosEleccion.get(i).update();
		}

		// ------------------------------------------------------------------------
		// ---------------------LOGICA DE LOS ESTADOS------------------------------
		// ------------------------------------------------------------------------

		// Conversaciones
		if (estado == EstadoHabitacion.CONVERSAR) {
			stage.addActor(cuadroTexto);
			cuadroTexto.iniciarConversacion(stage);
			if (ultimoTexto) {
				cuadroTexto.getSigConv().remove();
				stage.addActor(cuadroTexto.getFinConv());
			}
		}
		
		//Decisiones
		if(estado == EstadoHabitacion.DECISION){
			int tbloque = 75;
			int aux = 0;
			horaDeElegir();
			for(int i = 0; i < 4; ++i){
				cuadrosEleccion.get(i).setCoordenadas(0, aux);
				stage.addActor(cuadrosEleccion.get(i));
				aux+=tbloque;
			}
		}

		// Se empieza a investigar y se puede interactuar con los objetos de la
		// habitacion
		Iterator<Objeto> iter = objetos.iterator();
		
		if (estado == EstadoHabitacion.INVESTIGAR) {
			while (iter.hasNext()) {
				iter.next().seInvestiga(true);
			}
		} else {
			while (iter.hasNext()) {
				iter.next().seInvestiga(false);
			}
		}
		
		//Se actualiza el estado del juego
		organizador.actualizarEstado();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	public void hide() {}

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
	 * Devuelve el array de objetos de la habitación
	 * @return objetos
	 */

	public Array<Objeto> getObjetos() {
		return objetos;
	}
	
	/**
	 * Para la música de la habitación
	 */

	public void pararMusica() {
		musica.stop();
	}
	
	/**
	 * Comprueba si estamos o no conversando con el personaje de la habitación
	 * @return conversando
	 */

	public boolean seEstaConversando() {
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
	 * Este método hace que la habitación pase del estado CONVERSACION al estado DECISION
	 */
	
	public void horaDeElegir() {
		cuadroTexto.finConversacion();
		cuadroTexto.remove();
		ultimoTexto = false;
		estado = EstadoHabitacion.DECISION;
	}
	
	/**
	 * Este método hace que la habitación pase del estado DECISION al estado CONVERSACION
	 */
	
	public void terminarEleccion(){
		for(int i = 0; i < 4; ++i){
			cuadrosEleccion.get(i).remove();
		}
		
		estado = EstadoHabitacion.CONVERSAR;
		setConversando(true);
	}
	
	/**
	 * Devuelve el cuadro de dialogo de la conversaciÓn
	 * @return cuadroTexto
	 */

	public CuadroDialogo getCuadroDialogo() {
		return cuadroTexto;
	}
	
	/**
	 * Devuelve el stage de la habitación
	 * @return
	 */

	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Devuelve el personaje de la habitación
	 * @return personaje
	 */

	public Personaje getPersonaje() {
		return personaje;
	}
	
	/**
	 * Devuelve el vector de cuadros de elección
	 * @return cuadrosEleccion
	 */
	
	public Array<CuadroEleccion> getCuadrosEleccion(){
		return cuadrosEleccion;
	}
}