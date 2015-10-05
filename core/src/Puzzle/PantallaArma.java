package Puzzle;

import Objetos.Puntuacion;
import Puzzle.Arma.NombreArma;

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
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.OrganizadorEstados;

/**
 * Esta clase representa la pantalla de selección del arma. En esta pantalla el jugador
 * debe elegir el arma que ha sido utilizada por el asesino.
 * @author Francisco Madueño Chulian
 */

public class PantallaArma implements Screen{
	
	private Array<Arma> armas;
	private Stage stage;
	private Texture textura;
	private Music musica;
	private int nFallos = 0;
	private NombreArma arma;
	
	// Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; // se usa para adaptar la pantalla
	
	// Puntuacion
	protected static Puntuacion puntuacion = Puntuacion.getInstancia();
	
	/**
	 * Contructor de la clase
	 */
	
	public PantallaArma() {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		// instanciamos la camara
		camara.position.set(TheHouseOfCrimes.WIDTH / 2f, TheHouseOfCrimes.HEIGHT / 2f, 0);
		viewport = new FillViewport(TheHouseOfCrimes.WIDTH, TheHouseOfCrimes.HEIGHT, camara);
		
		Gdx.input.setInputProcessor(stage);
		
		armas = new Array<Arma>();
		
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/armaDaga.png")), NombreArma.DAGA));
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/armaPistola.png")), NombreArma.PISTOLA));
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/armaRifle.png")), NombreArma.RIFLE));
		armas.add(new Arma(new Texture(Gdx.files.internal("Imagenes/armaSerpiente.png")), NombreArma.SERPIENTE));
		
		if(OrganizadorEstados.getArma().equals(NombreArma.DAGA)){ //se usa la daga
			armas.get(0).setUsada(true);
			arma = NombreArma.DAGA;
		}else if(OrganizadorEstados.getArma().equals(NombreArma.PISTOLA)){ //se usa la pistola
			armas.get(1).setUsada(true);
			arma = NombreArma.PISTOLA;
		}else if(OrganizadorEstados.getArma().equals(NombreArma.RIFLE)){ //se usa el rifle
			armas.get(2).setUsada(true);
			arma = NombreArma.RIFLE;
		}else if(OrganizadorEstados.getArma().equals(NombreArma.SERPIENTE)){ //se usa la serpiente
			armas.get(3).setUsada(true);
			arma = NombreArma.SERPIENTE;
		}
		
		textura = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_ARMA));
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Tension.mp3"));
		
		musica.setLooping(true);
		musica.play();
		
		for(int i = 0; i < armas.size; ++i){
			stage.addActor(armas.get(i));
			armas.get(i).setTouchable(Touchable.enabled);
		}
	}
	
	/**
	 * Este método se ejecuta en bucle durante todo el tiempo que el jugador pase en la 
	 * pantalla. Comprueba continuamente si el jugador a pulsado sobre un arma.
	 */

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camara.update();
		batch.setProjectionMatrix(camara.combined);

		batch.begin();
		batch.draw(textura, 0, 0, TheHouseOfCrimes.WIDTH, TheHouseOfCrimes.HEIGHT);
		batch.end();
		
		int coordenadaAux = 100; //los actores se empiezan a dibujar a patir de esta posición
		for(int i = 0; i < armas.size; ++i){
			armas.get(i).setCoordenadas(coordenadaAux, 100);
			coordenadaAux += 250;
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

	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
		
	}
	
	/**
	 * Aumenta el número de fallos
	 */
	
	public void sumaFallo(){
		nFallos++;
	}
	
	/**
	 * Devuelve el número de fallos
	 * @return
	 */
	public int getNFallos(){
		return nFallos;
	}
	
	/**
	 * Devuelve el nombre del arma
	 * @return
	 */
	public NombreArma getArma(){
		return arma;
	}

	public void show() {}

	public void hide() {}

	public void pause() {}

	public void resume() {}

	public void dispose() {
		batch.dispose();
		stage.dispose();
		textura.dispose();
		musica.dispose();
	}
}
