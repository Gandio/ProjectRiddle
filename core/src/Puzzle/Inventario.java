package Puzzle;

import java.util.Iterator;

import Botones.BotonAceptarCombinar;
import Botones.BotonCancelarCombinar;
import Botones.BotonCerrarInventario;
import Botones.BotonCombinarObjeto;
import Items.Identificador;
import Items.Objeto;
import Objetos.CuadroDescripcion;
import Objetos.CuadroEstado;

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
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.OrganizadorEstados;

/**
 * Esta clase representa el inventario del personaje. En esta pantalla se muestran los objetos que el 
 * jugador ha recogido durante toda la partida, una descripción del objeto y el objetivo más inmedianto en 
 * la partida.
 * @author Francisco Madueño Chulián
 */

public final class Inventario implements Screen{
	private static MyGdxGame game;
	private static Inventario unicaInstancia;
	
	protected static Stage stage;
	protected static Music musica;
	private Texture textura;
	private static Array<Objeto> inventario;
	private static Array<Objeto> combinacion;
	
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
	private static CuadroDescripcion cuadroDescripcion;
	private static CuadroEstado cuadroObjetivo;
	
	//Diferentes estados por los que pasa el inventario a lo largo de la partida
	public enum EstadoInventario{
		COMBINANDO, NORMAL, COMBINACION_PREPARADA;
	};
	
	//Estado en el que se encuentra actualmente el inventario
	private EstadoInventario estado;
	
	//Organizador de estados
	public static OrganizadorEstados organizador = OrganizadorEstados.getInstancia();
	
	/**
	 * Constructor de la clase
	 * @param game
	 */

	private Inventario(MyGdxGame game) {
		stage = new Stage(new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Inventario.game = game;
		camara = new OrthographicCamera();
		batch = new SpriteBatch();
		
		// Música
		musica = Gdx.audio.newMusic(Gdx.files.internal("Musica/Inventario.mp3"));
		musica.setLooping(true);
		
		estado = EstadoInventario.NORMAL;
		inventario = new Array<Objeto>();
		
		//Usamos un array especial, solo de dos elementos, para las combinaciones
		combinacion = new Array<Objeto>(2);
		
		//instanciamos la cámara
		camara.position.set(MyGdxGame.WIDTH / 2f, MyGdxGame.HEIGHT / 2f ,0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		
		//Inicializamos los botones y hacemos que se puedan pulsar
		cerrarInventario = new BotonCerrarInventario(game);
		cerrarInventario.setTouchable(Touchable.enabled);
		
		aceptarCombinar = new BotonAceptarCombinar(game);
		aceptarCombinar.setTouchable(Touchable.enabled);
		
		cancelarCombinar = new BotonCancelarCombinar(game);
		cancelarCombinar.setTouchable(Touchable.enabled);
		
		combinarObjeto = new BotonCombinarObjeto(game);
		combinarObjeto.setTouchable(Touchable.enabled);
		
		//Instanciamos los cuadros de texto
		cuadroDescripcion = new CuadroDescripcion(game);
		cuadroObjetivo = new CuadroEstado(game);
		
		Gdx.input.setInputProcessor(stage);
		
		//añadimos botones
		stage.addActor(cerrarInventario);
		stage.addActor(aceptarCombinar);
		stage.addActor(cancelarCombinar);
		stage.addActor(combinarObjeto);
		
		//añadimos los cuadros de texto
		stage.addActor(cuadroDescripcion);
		stage.addActor(cuadroObjetivo);
	}
	
	/**
	 * Este método se ejecuta en bucle durante el tiempo que el jugador permanezca en el 
	 * inventario
	 */
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
		
		//Posiciones de los cuadros de texto
		cuadroDescripcion.setCoordenadas(135, 250);
		cuadroObjetivo.setCoordenadas(850, 40);
		//Se comprueban si los botones han sido pulsados
		cancelarCombinar.update();
		aceptarCombinar.update();
		combinarObjeto.update();
		cerrarInventario.update();
		
		musica.play();
		
		/*
		 * Cuadramos los botones objeto en el inventario
		 */
		Iterator<Objeto> iter = inventario.iterator();
		float x = 450;
		float y = 400;
		int i = 0;
		while(iter.hasNext()){
			if(i%3 == 0){ //nueva linea de objetos
				x = 450;
				y -= 100;
			}
			
			Objeto o = iter.next();
			stage.addActor(o);
			o.setTouchable(Touchable.enabled);
			o.setCoordenadas(x, y);
			
			x+=100;
			++i;
		}
		
		iter = inventario.iterator();
		while(iter.hasNext()){
			iter.next().seSeleccionaBoton();
		}
		
		//Se actualiza el estado del juego
		organizador.actualizarEstado();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}
	
	/**
	 * Este método dibuja la textura de la pantalla
	 */

	public void show() {
		textura = new Texture(Gdx.files.internal(GestorImagen.URL_PANTALLA_INVENTARIO));
	}

	public void hide() {}

	public void pause() {}

	public void resume() {}

	public void dispose() {}
	
	//-----------------------------------------------------------------------------
	//---------------------------------FUNCIONES AUXILIARES------------------------
	//-----------------------------------------------------------------------------
	
	/**
	 * Este método añade un objeto al inventario
	 * @param b
	 */
	public static void añadirObjeto(Objeto b){
		inventario.add(b);
	}
	
	/**
	 * Este método borra el objeto que se le pase del inventario
	 * @param b
	 */
	public static void borrarObjeto(Objeto b){
		Iterator<Objeto> iter;
		
		iter = inventario.iterator();
		
		while(iter.hasNext()){
			//Comparamos los identificadores
			if(iter.next().getIdentificador() == b.getIdentificador()){
				iter.remove();
				b.remove();
			}
		}
	}
	
	/**
	 * Comprueba si los objetos que hay en el array de combinación se pueden combinar
	 * @return
	 */
	public boolean sePuedeCombinar(){
		Iterator<Identificador> iter1;
		iter1 = combinacion.get(0).getCombinables().iterator();
		
		while(iter1.hasNext()){
			if(iter1.next() == combinacion.get(1).getIdentificador()){
				return true;
					
			}
		}
		
		return false;
	}

	/**
	 * Para la música del inventario
	 */

	public static void pararMusica() {
		musica.dispose();
	}
	
	/**
	 * Devuelve el estado del inventario
	 * @return estado
	 */
	
	public EstadoInventario getEstado(){
		return estado;
	}
	
	/**
	 * Cambia el estado actual del inventario
	 * @param estado
	 */
	public void setEstado(EstadoInventario estado){
		this.estado = estado;
	}
	
	/**
	 * Devuelve el contenido del inventario del jugador
	 * @return inventario
	 */
	public static Array<Objeto> getContenido(){
		return inventario;
	}
	
	/**
	 * Devuelve el contenido del array de combinacion
	 * @return combinacion
	 */
	public Array<Objeto> getCombinacion(){
		return combinacion;
	}
	
	/**
	 * Si ya existe una instancia del inventario se devuelve si no se crea y se devuelve
	 * @return unicaInstancia
	 */
	
	public static Inventario getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Inventario(game);
		}
		
		return unicaInstancia;
	}
	
	/**
	 * Devuelve el cuadro de descripción de los objetos
	 * @return cuadroDescripcion
	 */
	
	public static CuadroDescripcion getCuadroDescripcion(){
		return cuadroDescripcion;
	}
	
	/**
	 * Devuelve el cuadro de objetivos
	 * @return cuadroObjetivo
	 */
	public static CuadroEstado getCuadroEstado(){
		return cuadroObjetivo;
	}
	
	/**
	 * Este método quita el resplandor exterior de los botones objeto
	 */
	public static void restaurarBotonesObjetos(){
		Iterator<Objeto> iter = inventario.iterator();
		
		iter = inventario.iterator();
		while(iter.hasNext()){
			iter.next().devolverTexturaOriginal();
		}
	}
	
	public static Objeto getCombinacion(int i){
		return combinacion.get(i);
	}
}