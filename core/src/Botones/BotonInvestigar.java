package Botones;

import Objetos.Puntuacion;
import Pantallas.Habitacion;
import Pantallas.Habitacion.EstadoHabitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.ArchivoLog;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.LineaLog;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón de investigar. Este botón se usará pasa activar o 
 * desactivar el modo investigación mientras te encuentres en una habitación
 * @author Francisco Madueño Chulián
 */

public class BotonInvestigar extends Boton{
	
	private Texture botonActivado, botonDesactivado;
	
	/**
	 * Constructor de la clase.
	 * @param game
	 */
	
	public BotonInvestigar(MyGdxGame game) {
		super(game);
		botonActivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_INVESTIGAR));
		botonDesactivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_INVESTIGAR_DES));
		
		boton = botonActivado;
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Este método actualiza el comportamiento del botón durante toda la partida
	 */
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonInvestigar)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		/*
		 * Si estamos en el modo normal podemos acceder al modo investigar. Si estamos
		 * en el modo investigar podremos volver al modo normal pulsado otra vez el botón.
		 * El botón estará desactivado durante el modo conversación.
		 */
		
		if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.NORMAL){
			boton = botonActivado;
			if(pulsado){
				((Habitacion) game.getScreen()).setEstado(EstadoHabitacion.INVESTIGAR);
				((Habitacion) game.getScreen()).getPersonaje().setCoordenadas(0, -3000);
				
				//Linea de archivo de log uso de lupa
				MyGdxGame.getArchivoLog().escribirLinea(new LineaLog(MyGdxGame.getUsuario() + ";" +  
						MyGdxGame.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" + 
						Puntuacion.getPuntos() + ";" +  "L" + ";" + 
						game.getScreen().getClass().getSimpleName() + ";" + "1"));
			}
		}else if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.INVESTIGAR){
			boton = botonActivado;
			if(pulsado){
				((Habitacion) game.getScreen()).setEstado(EstadoHabitacion.NORMAL);
				((Habitacion) game.getScreen()).getPersonaje().setCoordenadas(0, 0);
				
				//Linea de archivo de log uso de lupa
				MyGdxGame.getArchivoLog().escribirLinea(new LineaLog(MyGdxGame.getUsuario() + ";" +  
						MyGdxGame.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" +
						Puntuacion.getPuntos() + ";" +  "L" + ";" + 
						game.getScreen().getClass().getSimpleName() + ";" + "0"));
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}