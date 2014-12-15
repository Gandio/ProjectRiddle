package Pantallas;

import Controladores.ColisionCursor;
import Controladores.ControladorBotonPuerta;
import Controladores.ControladorBotonPuertaPasillo;
import Objetos.Boton;
import Objetos.Cursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase representa los pasillos de la casa por donde el jugador tendrá que moverse para
 * ir de una habitación a otra.
 * @author Francisco Madueño Chulián
 */
public class Pasillo extends Pantalla implements Screen{
	
	private static Cursor cursor = new Cursor(game);
	private Array<Rectangle> colisionesParedes = new Array<Rectangle>();
	private Array<Rectangle> colisionesPuertas = new Array<Rectangle>();
	
	//Texturas
	private ShapeRenderer sr;
	
	//Controladores
	private ColisionCursor controladorCursor;
	
	public Pasillo(MyGdxGame game) {
		super(game);
		
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/pasillo.mp3"));
		musica.setLooping(true);
		musica.play();
		
		//instanciamos la camara
		camara = new OrthographicCamera();
		camara.setToOrtho(false, cursor.getX(), cursor.getY());
		camara.position.set(cursor.getX(), cursor.getY(), 0);
		camara.zoom -= 0.4;
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		Gdx.input.setInputProcessor(stage);
		
		//Preparamos las colisiones con las paredes
		colisionesParedes.add(new Rectangle(255, 403, 1000, 120)); //pared con dos puertas
		colisionesParedes.add(new Rectangle(255, 403, 50, 430)); //superior derecha 
		colisionesParedes.add(new Rectangle(20, 690, 250, 50)); //superior
		colisionesParedes.add(new Rectangle(0, 150, 35, 550)); //superior izquierda puerta
		colisionesParedes.add(new Rectangle(0, 150, 765, 105)); //inferior puerta
		colisionesParedes.add(new Rectangle(715, 0, 50, 150)); //derecha entrada
		colisionesParedes.add(new Rectangle(715, -38, 300, 150)); //inferior entrada
		colisionesParedes.add(new Rectangle(938, -38, 50, 293)); //izquierda entrada
		colisionesParedes.add(new Rectangle(938, 156, 350, 100)); //inferior
		colisionesParedes.add(new Rectangle(1240, 156, 50, 300)); // derecha puerta
		
		//Preparamos las colisiones con las puertas
		colisionesPuertas.add(new Rectangle(546, 385, 75, 75)); //primera superior
		colisionesPuertas.add(new Rectangle(1060, 385, 65, 65)); //segunda superior
		colisionesPuertas.add(new Rectangle(1230, 290, 65, 65)); //derecha
		colisionesPuertas.add(new Rectangle(255, 200, 75, 75)); //inferior
		colisionesPuertas.add(new Rectangle(-20, 490, 75, 65)); //izquierda
		
		//Instanciamos los controladores
		controladorCursor = new ColisionCursor(this);
		controladorBotonPuerta = new ControladorBotonPuertaPasillo(this, game);
		
		sr = new ShapeRenderer();
		
		//añadimos actores
		stage.addActor(cursor);
	}
	
	/**
	 * Este método se ejecuta constantemente mientras el juego esté funcionando y se encarga
	 * de actualizarlo.
	 */
	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		
		batch.setProjectionMatrix(camara.combined);
		
		//Empezamos a dibujar
		batch.begin();
		batch.draw(pantalla, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		//Hacemos que la camara me siga
		camara.position.x = cursor.getX();
		camara.position.y = cursor.getY();
		
		//Movimiento del cursor
		controladorCursor.update(delta);
		
		//Boton para abrir puertas
		botonPuerta.setCoordenadas(cursor.getX() + 270, cursor.getY() + 200);
		controladorBotonPuerta.update();
		
		
		//Dibujamos bordes
		/*
		sr.setProjectionMatrix(camara.combined);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		sr.rect(cursor.getLimites().getX(), cursor.getLimites().getY(), 
				cursor.getLimites().getWidth(), cursor.getLimites().getHeight());
		
		sr.rect(255, 403, 1000, 120);
		sr.rect(255, 403, 50, 430);
		sr.rect(20, 690, 250, 50); //superior
		sr.rect(0, 150, 35, 550); //superior izquierda puerta
		sr.rect(0, 150, 765, 105); //inferior puerta
		sr.rect(715, 0, 50, 150); //derecha entrada
		sr.rect(715, -38, 300, 150); //inferior entrada
		sr.rect(938, -38, 50, 293); //izquierda entrada
		sr.rect(938, 156, 350, 100); //inferior
		sr.rect(1240, 156, 50, 300);
		
		sr.setColor(Color.PURPLE);
		sr.rect(546, 385, 75, 75);
		sr.rect(1060, 385, 65, 65);
		sr.rect(1230, 290, 65, 65);
		sr.rect(255, 200, 75, 75);
		sr.rect(-20, 490, 75, 65);
		sr.end();
		*/
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	public void resize(int width, int height){
		viewport.update(width, height);
		stage.setViewport(viewport);
	}
	
	public void show(){
		//instanciamos el batch
		batch = new SpriteBatch();
		pantalla = new Texture("Imagenes/pasillos.png");
	}
	
	public void hide(){
		dispose();
	}
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose(){
		pantalla.dispose();
		batch.dispose();
		musica.dispose();
		stage.dispose();
	}
	
	/*----------------------------------------------------------------
	 * -----------------------FUNCIONES AUXILIARES--------------------
	 * ---------------------------------------------------------------
	 */
	
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
	public Cursor getCursor(){
		return cursor;
	}
	
	/**
	 * Devuelve el botón asociado a las puertas
	 * @return botonPuerta
	 */
	public Boton getBotonPuerta(){
		return botonPuerta;
	}
}