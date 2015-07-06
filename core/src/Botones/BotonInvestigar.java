package Botones;

import Pantallas.Habitacion;
import Pantallas.Habitacion.EstadoHabitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
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
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonInvestigar.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonInvestigarDesactivado.png"));
		
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
				((Habitacion) game.getScreen()).getPersonaje().setCoordenadas(450, -3000);
			}
		}else if(((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.INVESTIGAR){
			boton = botonActivado;
			if(pulsado){
				((Habitacion) game.getScreen()).setEstado(EstadoHabitacion.NORMAL);
				((Habitacion) game.getScreen()).getPersonaje().setCoordenadas(450, 0);
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}