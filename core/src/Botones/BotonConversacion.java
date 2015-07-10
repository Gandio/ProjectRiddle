package Botones;

import Pantallas.Habitacion;
import Pantallas.Habitacion.EstadoHabitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón de conversación. Este botón se usa para conversar con los
 * personajes durante el juego.
 * @author Francisco Madueño Chulián
 */

public class BotonConversacion extends Boton{
	
	private Texture botonActivado, botonDesactivado;
	
	/**
	 * Constructor de la clase botonConversacion.
	 * @param game
	 */

	public BotonConversacion(MyGdxGame game) {
		super(game);
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonConversacion.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonConversacionDesactivado.png"));
		
		boton = botonActivado; //inicialmente el botón está activado
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		/*
		 * Comprobamos si se pulsa el botón.
		 */
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonConversacion)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		/*
		 * Si estamos en modo investigación el botón conversación permanece inactivo.
		 * Solo se puede activar el modo conversación desde el modo normal. Si estamos 
		 * en modo conversación solo volveremos al modo normal una vez hayamos terminado
		 * de conversar.
		 */
		
		if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.NORMAL){
			boton = botonActivado;
			if(pulsado){
				((Habitacion) game.getScreen()).setEstado(EstadoHabitacion.CONVERSAR);
				((Habitacion) game.getScreen()).setConversando(true);
			}
		}else if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.CONVERSAR){
			boton = botonActivado;
			
		}else{
			boton = botonDesactivado;
		}
		
		/*
		 * Si detectamos que la conversación se ha acabado lanzamos el método que acaba con
		 * la conversación y nos devuelve al modo normal
		 */
		
		if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.CONVERSAR && !((Habitacion) game.getScreen()).getConversando()){
			((Habitacion) game.getScreen()).terminarConversacion();
		}
		
		pulsado = false;
	}
}
