package Puzzle;

import Botones.Boton;
import Botones.BotonCerrarInventario;
import Botones.BotonCombinarObjeto;
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
	private MyGdxGame game;
	private Stage stage;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantallas
	
	//Botones
	private Array<Boton> botonesObjetos;
	private Array<Objeto> objetosInventario;
	private BotonCerrarInventario cerrarInventario;
	private BotonCombinarObjeto combinarObjeto;
	
	private Texture inventario;
	
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
		
		Gdx.input.setInputProcessor(stage);
		
		cerrarInventario = new BotonCerrarInventario(game);
		cerrarInventario.setTouchable(Touchable.enabled);
		
		//añadimos botones
		stage.addActor(cerrarInventario);
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
		
		cerrarInventario.update();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	@Override
	public void show() {
		//inventario = textura inventario 
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
	//-----------------------------------------------------------------------------
	//---------------------------------FUNCIONES AUXILIARES------------------------
	//-----------------------------------------------------------------------------
	
	public void añadirBotonObjeto(Boton b){
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
	}
}
