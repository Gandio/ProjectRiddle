package Pantallas;

import Botones.BotonAbajo;
import Botones.BotonArriba;
import Botones.BotonDerecha;
import Botones.BotonInventario;
import Botones.BotonIzquierda;
import Botones.BotonPuertaPasillo;
import Objetos.Cursor;
import Objetos.Puntuacion;
import Puzzle.Inventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.OrganizadorEstados;

/**
 * Esta clase representa los pasillos de la casa por donde el jugador tendrá que moverse para
 * ir de una habitación a otra.
 * @author Francisco Madueño Chulián
 */
public class Pasillo implements Screen{
	//Juego
	public static MyGdxGame game = Inicio.game;
	protected Stage stage;
	protected static Music musica;
	protected Texture pantalla;
	private boolean debug = false;
	
	//Cámaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantallas
	
	//Personaje
	private static Cursor cursor = new Cursor(game);
	
	//Arrays
	private Array<Rectangle> colisionesParedes = new Array<Rectangle>();
	private Array<Rectangle> colisionesPuertas = new Array<Rectangle>();
	
	//Botones del pasillo
	private BotonPuertaPasillo botonPuerta; //permite entrar en una habitación
	private BotonArriba botonArriba;
	private BotonAbajo botonAbajo;
	private BotonDerecha botonDerecha;
	private BotonIzquierda botonIzquierda;
	private BotonInventario botonInventario;
		
	//Puntuación
	private static Puntuacion puntuacion = Puntuacion.getInstancia();
	
	//Texturas
	private ShapeRenderer sr;
	
	//Habitaciones
	public static Sotano sotano = Sotano.getInstancia();
	public static Dormitorio dormitorio = Dormitorio.getInstancia();
	public static Habitacion salon = Salon.getInstancia();
	public static Biblioteca biblioteca = Biblioteca.getInstancia();
	public static Atico atico = Atico.getInstancia();
	//public static Estudio estudio = Estudio.getInstancia();
	//public static Baño baño = Baño.getInstancia();
	//public static Cocina cocina = Cocina.getInstancia();
	
	//Organizador de estados
	public static OrganizadorEstados organizador = OrganizadorEstados.getInstancia();
	
	/**
	 * Constructor de la clase Pasillo
	 * @param game
	 */
	
	public Pasillo(MyGdxGame game) {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Pasillo.game = game;
		
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		/*
		 * La música depende de esta variable.
		 */
		/*if(MyGdxGame.SUSPENSE_MUSICA)
			musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/pasillo.mp3"));
		else*/
		
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/TemaSinSuspense.mp3"));
		musica.setLooping(true);
		musica.play();
		
		//instanciamos la cámara
		camara.setToOrtho(false, cursor.getX(), cursor.getY());
		camara.position.set(cursor.getX(), cursor.getY(), 0);
		camara.zoom -= 0.4;
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		//Preparamos las colisiones con las paredes
		colisionesParedes.add(new Rectangle(255, 403, 1000, 300)); //pared con dos puertas
		//colisionesParedes.add(new Rectangle(255, 540, 50, 400)); //superior derecha 
		colisionesParedes.add(new Rectangle(20, 690, 250, 50)); //superior
		colisionesParedes.add(new Rectangle(0, 150, 35, 550)); //superior izquierda puerta
		colisionesParedes.add(new Rectangle(0, 100, 760, 150)); //inferior puerta
		//colisionesParedes.add(new Rectangle(715, 0, 45, 250)); //derecha entrada
		colisionesParedes.add(new Rectangle(715, -38, 300, 145)); //inferior entrada
		//colisionesParedes.add(new Rectangle(938, -38, 45, 293)); //izquierda entrada
		colisionesParedes.add(new Rectangle(938, 106, 450, 150)); //inferior
		colisionesParedes.add(new Rectangle(1240, 156, 50, 300)); // derecha puerta
		
		//Preparamos las colisiones con las puertas
		colisionesPuertas.add(new Rectangle(546, 385, 75, 75)); //primera superior
		colisionesPuertas.add(new Rectangle(1060, 385, 65, 65)); //segunda superior
		colisionesPuertas.add(new Rectangle(1230, 290, 65, 65)); //derecha
		colisionesPuertas.add(new Rectangle(255, 200, 75, 75)); //inferior
		colisionesPuertas.add(new Rectangle(-20, 490, 75, 65)); //izquierda
		
		sr = new ShapeRenderer();
		
		//Añadimos los botones y los configuramos como elementos "tocables" dentro del juego
		botonPuerta = new BotonPuertaPasillo(game);
		botonPuerta.setTouchable(Touchable.enabled);
		
		botonAbajo = new BotonAbajo(game);
		botonAbajo.setTouchable(Touchable.enabled);
		
		botonArriba = new BotonArriba(game);
		botonArriba.setTouchable(Touchable.enabled);
		
		botonDerecha = new BotonDerecha(game);
		botonDerecha.setTouchable(Touchable.enabled);
		
		botonIzquierda = new BotonIzquierda(game);
		botonIzquierda.setTouchable(Touchable.enabled);
		
		botonInventario = new BotonInventario(game);
		botonInventario.setTouchable(Touchable.enabled);
		
		Gdx.input.setInputProcessor(stage);
		
		//añadimos actores
		stage.addActor(cursor);
		stage.addActor(botonPuerta);
		stage.addActor(botonAbajo);
		stage.addActor(botonArriba);
		stage.addActor(botonDerecha);
		stage.addActor(botonIzquierda);
		stage.addActor(botonInventario);
		stage.addActor(puntuacion);
	}
	
	/**
	 * Este método se ejecuta constantemente mientras el jugador se encuentre en el pasillo
	 *  y se encargá de actualizarlo.
	 */
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Gdx.input.setInputProcessor(stage);
		
		camara.update();
		batch.setProjectionMatrix(camara.combined);
		
		//Empezamos a dibujar
		batch.begin();
		batch.draw(pantalla, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		//Hacemos que la camara siga al jugador
		camara.position.x = cursor.getX();
		camara.position.y = cursor.getY();
		
		//Estamos constatemente comprobando el estado de los botones
		botonAbajo.esPulsado(delta);
		botonArriba.esPulsado(delta);
		botonDerecha.esPulsado(delta);
		botonIzquierda.esPulsado(delta);
		botonPuerta.update();
		botonInventario.update();
		
		//Coordenadas de los botones
		botonAbajo.setCoordenadas(cursor.getX() - 285, cursor.getY() - 210);
		botonArriba.setCoordenadas(cursor.getX() - 285, cursor.getY() - 40);
		botonDerecha.setCoordenadas(cursor.getX() - 200, cursor.getY() - 125);
		botonIzquierda.setCoordenadas(cursor.getX() - 370, cursor.getY() - 125);
		botonPuerta.setCoordenadas(cursor.getX() + 280, cursor.getY() + 140);
		botonInventario.setCoordenadas(cursor.getX() + 200, cursor.getY() + 140);
		
		//Coordenada del marcador de puntos
		puntuacion.setCoordenadas(cursor.getX() - 350, cursor.getY() + 230);
		
		/*Dibujamos bordes. Esto solo sirve para buscar bugs en el movimiento, no se muestra
		 * en la versión final del juego
		*/
		if(debug) debug();
		
		/*Al no poder usar el dispose porque se crearían nuevos escenarios hay que parar la 
		 * música manualmente
		 */
		Inventario.pararMusica();
		
		//Se actualiza el estado del juego
		organizador.actualizarEstado();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	/**
	 * Se muestra la textura de los pasillos
	 */
	
	public void show(){
		pantalla = new Texture(GestorImagen.URL_PANTALLA_PASILLO);
	}
	
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	public void hide() {}

	public void pause() {}

	public void resume() {}
	
	public void dispose(){
		musica.stop();
		musica.dispose();
		batch.dispose();
		stage.dispose();
		pantalla.dispose();
	}
	
	/*----------------------------------------------------------------
	 * -----------------------FUNCIONES AUXILIARES--------------------
	 * ---------------------------------------------------------------
	 */
	
	/**
	 * Se para la música del escenario.
	 */
	public void pararMusica(){
		musica.stop();
	}
	
	/**
	 * Devuelve el array con todos los rectángulos que envuelven a las paredes
	 * @return colisionesParedes
	 */
	public Array<Rectangle> getParedes(){
		return colisionesParedes;
	}
	
	/**
	 * Devuelve el array con todos los rectángulos que envuelven a las puertas
	 * @return colisionesPuertas
	 */
	public Array<Rectangle> getPuertas(){
		return colisionesPuertas;
	}
	
	/**
	 * Devuelve el cursor
	 * @return cursor
	 */
	public static Cursor getCursor(){
		return cursor;
	}
	
	/**
	 * Este método se usa solo para las pruebas de colisión
	 */
	
	private void debug(){
		sr.setProjectionMatrix(camara.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		sr.rect(cursor.getLimites().getX(), cursor.getLimites().getY(), 
			cursor.getLimites().getWidth(), cursor.getLimites().getHeight());
	
		sr.rect(255, 403, 1000, 300);//pared con dos puertas
		//sr.rect(255, 540, 50, 400);
		//sr.rect(20, 690, 250, 50); //superior
		//sr.rect(0, 150, 35, 550); //superior izquierda puerta
		sr.rect(0, 100, 760, 150); //inferior puerta
		//sr.rect(715, 0, 45, 250); //izquierda entrada
		sr.rect(715, -38, 300, 145); //inferior entrada
		//sr.rect(938, -38, 45, 293); //derecha entrada
		sr.rect(938, 106, 450, 150); //inferior
		//sr.rect(1240, 156, 50, 300); //derecha puerta
		
		sr.setColor(Color.PURPLE);
		sr.rect(546, 385, 75, 75);
		sr.rect(1060, 385, 65, 65);
		sr.rect(1230, 290, 65, 65);
		sr.rect(255, 200, 75, 75);
		sr.rect(-20, 490, 75, 65);
		sr.end();
	}
}