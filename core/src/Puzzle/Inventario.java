package Puzzle;

import Botones.BotonAceptarCombinar;
import Botones.BotonCancelarCombinar;
import Botones.BotonCerrarInventario;
import Botones.BotonCombinarObjeto;
import Botones.BotonInventario;
import Items.Objeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

public class Inventario implements Screen{
	private static MyGdxGame game;
	protected Stage stage;
	private Texture inventario;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantallas
	
	//Botones
	/*private Array<Boton> botonesObjetos;
	private Array<Objeto> objetosInventario;*/
	private BotonCerrarInventario cerrarInventario;
	//private BotonCombinarObjeto combinarObjeto;
	private BotonAceptarCombinar aceptarCombinar;
	private BotonCancelarCombinar cancelarCombinar;
	
	
	private int index = 0;
	private boolean combinando = false;

	public Inventario(MyGdxGame game) {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		this.game = game;
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		//instanciamos la cámara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f ,0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		cerrarInventario = new BotonCerrarInventario(game);
		cerrarInventario.setTouchable(Touchable.enabled);
		
		aceptarCombinar = new BotonAceptarCombinar(game);
		aceptarCombinar.setTouchable(Touchable.enabled);
		
		cancelarCombinar = new BotonCancelarCombinar(game);
		cancelarCombinar.setTouchable(Touchable.enabled);
		
		Gdx.input.setInputProcessor(stage);
		
		//añadimos botones
		stage.addActor(cerrarInventario);
		stage.addActor(aceptarCombinar);
		stage.addActor(cancelarCombinar);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		batch.setProjectionMatrix(camara.combined);
		
		batch.begin();
		batch.draw(inventario, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		batch.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		//Posiciones de los botones
		cerrarInventario.setCoordenadas(100, 80);
		aceptarCombinar.setCoordenadas(200, 80);
		cancelarCombinar.setCoordenadas(300, 80);
		
		cerrarInventario.update();
		cancelarCombinar.update();
		aceptarCombinar.update();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	@Override
	public void show() {
		inventario = new Texture(Gdx.files.internal("Imagenes/inventario.png"));
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
	
	/*public void añadirBotonObjeto(Boton b){
		botonesObjetos.add(b);
		botonesObjetos.get(index).setTouchable(Touchable.enabled);
		stage.addActor(botonesObjetos.get(index));
		index++;
	}
	
	public Array<Boton> getBotones(){
		return botonesObjetos;
	}
	
	public Array<Objeto> getObjetos(){
		return objetosInventario;
	}
	
	public boolean getCombinar(){
		return combinando;
	}
	
	public void setCombinar(boolean estado){
		combinando = estado;
	}*/
}
