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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

public class Pasillo implements Screen{
	
	MyGdxGame game;
	Cursor cursor;
	Texture pasillos;
	SpriteBatch batch;
	OrthographicCamera camara;
	Stage stage;
	Boton botonPuerta; //permite entrar en una habitación
	FillViewport viewport; //se usa para adaptar la pantalla
	float altura = Gdx.graphics.getHeight();
	float anchura = Gdx.graphics.getWidth();
	float proporcionAlto = altura/1280;
	float proporcionAncho = anchura/800;
	
	public Pasillo(MyGdxGame game) {
		stage = new Stage(new FillViewport(proporcionAncho, proporcionAlto));
		this.game = game;
		
		//instanciamos los actores
		cursor = new Cursor(game);
		botonPuerta = new BotonPuerta(game);
		
		//instanciamos la camara
		camara = new OrthographicCamera();
		camara.position.set(cursor.getPosicion().x, cursor.getPosicion().y, 0);
		camara.zoom -= 0.65;
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		Gdx.input.setInputProcessor(stage);
		
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
		
		camara.position.x = cursor.getPosicion().x;
		camara.position.y = cursor.getPosicion().y;
		
		
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
}
