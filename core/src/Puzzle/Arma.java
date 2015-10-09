package Puzzle;

import java.io.IOException;

import Objetos.Puntuacion;
import Pantallas.Final;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.dropbox.core.DbxException;
import com.mygdx.game.LineaLog;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las posibles armas que se han usado durante la partida. El 
 * jugador debe escoger, en base a las pistas conseguidas, cual de las armas que se ofrece
 * es la correcta.
 * @author Francisco Madueño Chulian
 */

public class Arma extends Actor{
	private boolean armaUsada = false;
	private Texture textura;
	private boolean pulsado = false;
	private Vector2 coordenadas;
	private static TheHouseOfCrimes game = Inicio.game;
	private Sound error;
	
	public enum NombreArma{
		DAGA, PISTOLA, RIFLE, SERPIENTE
	}
	
	private NombreArma nombre;
	/**
	 * Constructor de la clase
	 * @param t
	 */
	public Arma(Texture t, NombreArma n){
		textura = t;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/Error.wav"));
		
		nombre = n;
	}
	
	/**
	 * Lógica del actor. Si se escoge el arma correcta se termina el juego y se añade la
	 * puntuación conseguida a un fichero txt. Si no se añade un fallo al contador de 
	 * errores y se restan puntos.
	 */
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Arma)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			if(armaUsada){ //Se acaba el juego
				
				TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" + 
						TheHouseOfCrimes.getFecha() + ";" + 
						((PantallaArma) game.getScreen()).getNFallos() * (-100) + ";" +
						Puntuacion.getPuntos() + ";" +  "H" + ";" + "arma" + ";" + 
						this.nombre + ";" + ((PantallaArma) game.getScreen()).getArma() 
						+ ";" + "1"));
				
				try {
					TheHouseOfCrimes.getArchivoLog().subirArchivo();
				} catch (DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				game.getScreen().dispose();
				game.setScreen(new Final());
			}else{
				Puntuacion.sumarError();
				((PantallaArma) game.getScreen()).sumaFallo();
				error.play();
				
				Puntuacion.setPuntuacion(-1 * Puntuacion.getPuntos() / 2);
				
				
				TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" + 
						TheHouseOfCrimes.getFecha() + ";" + 
						((PantallaArma) game.getScreen()).getNFallos() * (-100) + ";" +
						Puntuacion.getPuntos() + ";" +  "H" + ";" + "arma" + ";" + 
						this.nombre + ";" + ((PantallaArma) game.getScreen()).getArma() 
						+ ";" + "0"));
			}
		}
		
		pulsado = false;
	}
	
	/**
	 * Dibuja el actor en el stage
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y);
	}
	
	/**
	 * Determina el arma que se ha usado
	 * @param b
	 */
	
	public void setUsada(boolean b){
		armaUsada = b;
	}
	
	/**
	 * Modifica las coordenadas de cada actor
	 * @param x
	 * @param y
	 */
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
}