package Pantallas;

import Objetos.Boton;
import Objetos.BotonPuerta;
import Objetos.Cursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

public class Pasillo implements Screen{
	
	private MyGdxGame game;
	private Cursor cursor;
	private Texture pasillos;
	private SpriteBatch batch;
	private OrthographicCamera camara;
	private Stage stage;
	private Boton botonPuerta; //permite entrar en una habitación
	private FillViewport viewport; //se usa para adaptar la pantalla
	private float altura = Gdx.graphics.getHeight();
	private float anchura = Gdx.graphics.getWidth();
	private float proporcionAlto = altura/800;
	private float proporcionAncho = anchura/480;
	private Array<Rectangle> colisionesParedes = new Array<Rectangle>();
	private Array<Rectangle> colisionesPuertas = new Array<Rectangle>();
	
	public Pasillo(MyGdxGame game) {
		stage = new Stage(new FillViewport(proporcionAncho, proporcionAlto));
		this.game = game;
		
		//instanciamos los actores
		cursor = new Cursor(game);
		botonPuerta = new BotonPuerta(game);
		
		//instanciamos la camara
		camara = new OrthographicCamera();
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
		//añadimos actores
		stage.addActor(cursor);
		stage.addActor(botonPuerta);
	}

	public void render(float delta){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		
		batch.setProjectionMatrix(camara.combined);
		
		//Empezamos a dibujar
		batch.begin();
		batch.draw(pasillos, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		//Hacemos que la camara me siga
		camara.position.x = cursor.getX();
		camara.position.y = cursor.getY();
		
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
		pasillos = new Texture("Imagenes/pasillos.png");
	}
	
	public void hide(){
		dispose();
	}
	
	public void pause() {}
	
	public void resume() {}
	
	public void dispose(){
		pasillos.dispose();
		batch.dispose();
		stage.dispose();
	}
	
	/*----------------------------------------------------------------
	 * -----------------------FUNCIONES AUXILIARES--------------------
	 * ---------------------------------------------------------------
	 */
	
	public Array<Rectangle> getParedes(){
		return colisionesParedes;
	}
	
	public Array<Rectangle> getPuertas(){
		return colisionesPuertas;
	}
	
	public Cursor getCursor(){
		return cursor;
	}
}
