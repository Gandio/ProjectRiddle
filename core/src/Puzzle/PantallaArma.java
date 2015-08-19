package Puzzle;

import Objetos.Puntuacion;

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

public class PantallaArma implements Screen{
	
	private Array<Arma> armas;
	private Stage stage;
	private Texture textura;
	private Music musica;
	
	// Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; // se usa para adaptar la pantalla
	
	// Puntuacion
	protected static Puntuacion puntuacion = Puntuacion.getInstancia();
	
	public PantallaArma() {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		// instanciamos la camara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f, 0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		Gdx.input.setInputProcessor(stage);
		
		armas = new Array<Arma>();
		
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonDaga.png"))));
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonPistola.png"))));
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonRifle.png"))));
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonSerpiente.png"))));
		
		if(OrganizadorEstados.getArma() == 0) //se usa la daga
			armas.get(0).setUsada(true);
		else if(OrganizadorEstados.getArma() == 1) //se usa la pistola
			armas.get(1).setUsada(true);
		else if(OrganizadorEstados.getArma() == 2) //se usa el rifle
			armas.get(2).setUsada(true);
		else if(OrganizadorEstados.getArma() == 3) //se usa la serpiente
			armas.get(3).setUsada(true);
		
		textura = new Texture(Gdx.files.internal("Imagenes/pruebaFondoPasillo.png"));
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Tension.mp3"));
		musica.play();
		
		for(int i = 0; i < armas.size; ++i){
			stage.addActor(armas.get(i));
			armas.get(i).setTouchable(Touchable.enabled);
		}
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
		
		int aux = 100;
		for(int i = 0; i < armas.size; ++i){
			armas.get(i).setCoordenadas(aux, 0);
			aux += 100;
		}
		
		for(int i = 0; i < armas.size; ++i){
			armas.get(i).update();
		}
		
		// Posicion de la puntuacion
		stage.addActor(puntuacion);
		puntuacion.setCoordenadas(30, 750);
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		batch.dispose();
		stage.dispose();
		textura.dispose();
		musica.dispose();
	}
}
