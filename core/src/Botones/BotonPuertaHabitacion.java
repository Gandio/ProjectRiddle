package Botones;

import Objetos.Puntuacion;
import Pantallas.Habitacion;
import Pantallas.Pasillo;
import Pantallas.Habitacion.EstadoHabitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.LineaLog;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que permite salir de una habitacion.
 * @author Francisco Madueño Chulian
 *
 */

public class BotonPuertaHabitacion extends Boton{
	private Sound sonido;
	private Texture botonActivado, botonDesactivado;
	
	/**
	 * Constructor de la clase.
	 * @param game
	 */
	
	public BotonPuertaHabitacion(TheCrimeHouse game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_PUERTA_HABITACION));
		botonDesactivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_PUERTA_HABITACION_DES));
		
		boton = botonActivado;
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/botonPuerta.wav"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Este método comprueba si hemos pulsado el botón para salir de una habitación, si 
	 * lo hemos hecho y no estamos realizando ninguna otra acción el jugador sale al 
	 * pasillo.
	 */
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	((BotonPuertaHabitacion)event.getTarget()).pulsado = true;
                return false;
            }
		});
		
		/*
		 * El botón solo se activa cuando nos encontramos en el modo normal, ni estamos
		 * investigando ni conversando.
		 */
		
		if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.NORMAL){
			boton = botonActivado;
			if(pulsado){	
				sonido.play();
				pulsado = false;
				((Habitacion) game.getScreen()).pararMusica();
				
				//Linea de archivo de log transición
				TheCrimeHouse.getArchivoLog().escribirLinea(new LineaLog(TheCrimeHouse.getUsuario() + ";" +  
						TheCrimeHouse.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" + 
						Puntuacion.getPuntos() + ";" +  "T" + ";" + "Pasillo."));
				
				game.setScreen(new Pasillo(game));
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}