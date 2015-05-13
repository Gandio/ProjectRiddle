package Botones;

import Pantallas.Habitacion;
import Pantallas.Habitacion.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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

	public BotonConversacion(MyGdxGame game) {
		super(game);
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonConversacion.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonConversacionDesactivado.png"));
		
		boton = botonActivado;
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonConversacion)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(((Habitacion) game.getScreen()).getEstado() == Estado.NORMAL){
			boton = botonActivado;
			if(pulsado){
				((Habitacion) game.getScreen()).setEstado(Estado.CONVERSAR);
				((Habitacion) game.getScreen()).setConversando(true);
			}
		}else if(((Habitacion) game.getScreen()).getEstado() == Estado.CONVERSAR){
			boton = botonActivado;
			
		}else{
			boton = botonDesactivado;
		}
		
		if(((Habitacion) game.getScreen()).getEstado() == Estado.CONVERSAR && !((Habitacion) game.getScreen()).getConversando()){
			((Habitacion) game.getScreen()).terminarConversacion();
		}
		
		pulsado = false;
	}
}
