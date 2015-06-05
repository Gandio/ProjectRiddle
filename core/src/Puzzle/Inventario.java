package Puzzle;

import java.util.Iterator;

import Botones.BotonAceptarCombinar;
import Botones.BotonCancelarCombinar;
import Botones.BotonCerrarInventario;
import Botones.BotonCombinarObjeto;
import Botones.BotonInventario;
import Items.Botella;
import Items.Objeto;
import Objetos.CuadroDescripcion;
import Objetos.CuadroEstado;
import Pantallas.Salon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase representa el inventario del personaje. En esta pantalla se muestran los objetos que el 
 * jugador ha recogido durante toda la partida, una descripción del objeto y el objetivo más inmedianto en 
 * la partida.
 * @author Francisco Madueño Chulián
 */

public final class Inventario implements Screen{
	private static MyGdxGame game;
	private static Inventario unicaInstancia;
	
	protected Stage stage;
	private Texture textura;
	private Array<Objeto> inventario;
	private Array<Objeto> combinacion;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantallas
	
	//Botones
	private BotonCerrarInventario cerrarInventario;
	private BotonCombinarObjeto combinarObjeto;
	private BotonAceptarCombinar aceptarCombinar;
	private BotonCancelarCombinar cancelarCombinar;
	
	//Cuadros de texto
	private CuadroDescripcion cuadroDescripcion;
	private CuadroEstado cuadroEstado;
	
	public enum Estado{
		COMBINANDO, NORMAL, COMBINACION_PREPARADA;
	};
	
	private Estado estado;

	private Inventario(MyGdxGame game) {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		this.game = game;
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		estado = Estado.NORMAL;
		inventario = new Array<Objeto>();
		combinacion = new Array<Objeto>(2);
		
		//instanciamos la cámara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f ,0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		cerrarInventario = new BotonCerrarInventario(game);
		cerrarInventario.setTouchable(Touchable.enabled);
		
		aceptarCombinar = new BotonAceptarCombinar(game);
		aceptarCombinar.setTouchable(Touchable.enabled);
		
		cancelarCombinar = new BotonCancelarCombinar(game);
		cancelarCombinar.setTouchable(Touchable.enabled);
		
		combinarObjeto = new BotonCombinarObjeto(game);
		combinarObjeto.setTouchable(Touchable.enabled);
		
		Gdx.input.setInputProcessor(stage);
		
		//añadimos botones
		stage.addActor(cerrarInventario);
		stage.addActor(aceptarCombinar);
		stage.addActor(cancelarCombinar);
		stage.addActor(combinarObjeto);
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
		
		stage.act(Gdx.graphics.getDeltaTime());
		Gdx.input.setInputProcessor(stage);
		stage.draw();
		
		//Posiciones de los botones
		cerrarInventario.setCoordenadas(100, 80);
		aceptarCombinar.setCoordenadas(200, 80);
		cancelarCombinar.setCoordenadas(300, 80);
		combinarObjeto.setCoordenadas(400, 80);
		
		cancelarCombinar.update();
		aceptarCombinar.update();
		combinarObjeto.update();
		cerrarInventario.update();
		
		Iterator<Objeto> iter = inventario.iterator();
		float x = 600;
		float y = 400;
		int i = 0;
		while(iter.hasNext()){
			if(i%3 == 0){ //nueva linea de objetos
				x = 600;
				y -= 70;
			}
			
			Objeto o = iter.next();
			stage.addActor(o);
			o.setTouchable(Touchable.enabled);
			o.setCoordenadas(x, y);
			
			x+=70;
			++i;
		}
		
		if(estado == Estado.COMBINANDO){
			iter = inventario.iterator();
			while(iter.hasNext()){
				iter.next().seSeleccionaBoton();
			}
		}
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	@Override
	public void show() {
		textura = new Texture(Gdx.files.internal("Imagenes/Escenarios/inventario.png"));
	}

	@Override
	public void hide() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose() {}
	
	//-----------------------------------------------------------------------------
	//---------------------------------FUNCIONES AUXILIARES------------------------
	//-----------------------------------------------------------------------------
	
	/*public void añadirObjeto(Objeto b){
		inventario.add(b);
		inventario.get(index).setTouchable(Touchable.enabled);
		stage.addActor(inventario.get(index));
		//System.out.println(inventario.size);
		index++;
	}*/
	
	public void añadirObjeto(Objeto b){
		inventario.add(b);
	}
	/*
	public Array<Boton> getBotones(){
		return botonesObjetos;
	}
	
	public Array<Objeto> getObjetos(){
		return objetosInventario;
	}*/
	
	public Estado getEstado(){
		return estado;
	}
	
	public void setEstado(Estado estado){
		this.estado = estado;
	}
	
	public Array<Objeto> getContenido(){
		return inventario;
	}
	
	public Array<Objeto> getCombinacion(){
		return combinacion;
	}
	
	public static Inventario getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Inventario(game);
		}
		
		return unicaInstancia;
	}
}
