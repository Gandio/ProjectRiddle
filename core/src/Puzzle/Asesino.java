package Puzzle;

import Objetos.Puntuacion;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.LineaLog;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa a los candidatos para que sean el asesino en cada partida.
 * Representa a cada uno de los personajes con los que el jugador se ha encontrado a lo 
 * largo de la partida.
 * @author Francisco Madueño Chulián
 */

public class Asesino extends Actor{
	private boolean culpable = false;
	private Texture textura;
	private boolean pulsado = false;
	public Vector2 coordenadas;
	private static TheHouseOfCrimes game = Inicio.game;
	private Sound error;
	
	public enum NombreAsesino{
		NIÑA, JOVEN, MUJER, HOMBRE, ANCIANA
	}
	
	private NombreAsesino nombre;
	
	/**
	 * Constructor de la clase.
	 */
	public Asesino(Texture t, NombreAsesino n){
		textura = t;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/Error.wav"));
		
		nombre = n;
	}
	
	/**
	 * Lógica de la clase, si el jugador escoge al culpable se pasará a la pantalla de 
	 * selección de arma. Si no se le restarán puntos y se añadirá otro fallo al contador
	 * de errores.
	 */
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Asesino)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			if(culpable){ //se pasa a la pantalla de seleccion de arma
				
				//Linea de archivo de log hipotesis
				TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" + 
						TheHouseOfCrimes.getFecha() + ";" + 
						((PantallaAsesino) game.getScreen()).getNFallos() * (-100) + ";" +
						Puntuacion.getPuntos() + ";" +  "H" + ";" + "asesino" + ";" + 
						this.nombre + ";" + ((PantallaAsesino) game.getScreen()).getAsesino() 
						+ ";" + "1"));
				
				game.getScreen().dispose();
				game.setScreen(new PantallaArma());
			}else{
				Puntuacion.sumarError();
				((PantallaAsesino) game.getScreen()).sumaFallo();
				error.play();
				
				//Linea de archivo de log hipotesis
				TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" + 
						TheHouseOfCrimes.getFecha() + ";" + 
						((PantallaAsesino) game.getScreen()).getNFallos() * (-100) + ";" +
						Puntuacion.getPuntos() + ";" +  "H" + ";" + "asesino" + ";" + 
						this.nombre + ";" + ((PantallaAsesino) game.getScreen()).getAsesino() 
						+ ";" + "0"));
			}
		}
		
		pulsado = false;
	}
	
	/**
	 * Se dibuja al actor en el stage
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y, getWidth() / 1.6f, getHeight() / 1.6f);
	}
	
	/**
	 * Selecciona a uno de los asesinos como culpable
	 * @param b
	 */
	
	public void setCulpable(boolean b){
		culpable = b;
	}
	
	/**
	 * Este método se usa para cambiar las coordenadas del actor
	 * @param x
	 * @param y
	 */
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
}