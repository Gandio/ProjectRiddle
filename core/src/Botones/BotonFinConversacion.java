package Botones;

import Pantallas.Habitacion;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.OrganizadorEstados;
import com.mygdx.game.Tools;

/**
 * Este botón finaliza la conversación y devuelve la habitación a su estado original
 * @author Francisco Madueño Chulián
 *
 */

public class BotonFinConversacion extends Boton{
	private MyGdxGame game = Inicio.game;
	/**
	 * Constructor de la clase.
	 * @param game
	 */

	public BotonFinConversacion(MyGdxGame game) {
		super(game);
		//this.game = game;
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/finConv.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	
	/**
	 * Cuando el botón sea pulsado se borran el cuadro te texto y el texto y la habitación vuelve al 
	 * estado NORMAL, esto quiere decir que vuelven a estar activas todas las opciones.
	 */
	public void esPulsado(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		/*
		 * Detectamos si el botón ha sido pulsado.
		 */
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonFinConversacion)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			//termina la conversacion
			((Habitacion) game.getScreen()).setConversando(false);
			OrganizadorEstados.getEstadoActual().seIniciaMision(true);
		}
		
		pulsado = false;
	}
}
