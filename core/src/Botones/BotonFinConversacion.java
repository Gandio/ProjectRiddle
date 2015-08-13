package Botones;

import Pantallas.Habitacion;
import Pantallas.Inicio;
import Pantallas.Habitacion.EstadoHabitacion;
import Puzzle.Inventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.Estado;
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
			Estado estadoActual = OrganizadorEstados.getEstadoActual();
			//termina la conversacion
			((Habitacion) game.getScreen()).setConversando(false);
			
			//Vamos a hacer un poco de trampa, como salón no aparece como "Salón", esta es la
			//forma de saber si estamos en la misma habitación de la lógica del puzzle.
			String cadenaClase = "class Pantallas." + estadoActual.getHabitacionInicio().toString();
			
			if(estadoActual.getNumEstado() == 1 || estadoActual.getNumEstado() == 3 || estadoActual.getNumEstado() == 4){
				//Se inicia la misión, solo si estamos en la habitación del inicio del puzzle
				if(cadenaClase.equals(game.getScreen().getClass().toString()))
					estadoActual.seIniciaMision(true);
					
				//Se entrega el objeto, es decir, lo borramos del inventario
				if(cadenaClase.equals(game.getScreen().getClass().toString()) && estadoActual.objetoConseguido()){
					Inventario.borrarObjeto(estadoActual.getItem());
					estadoActual.seSuperaPuzzle(true);
				}
			}else{
				//Estamos en la habitacion de inicio y además ya se ha hablado con la persona, nos muestran las elecciones
				if(cadenaClase.equals(game.getScreen().getClass().toString()) && estadoActual.estadoMision()){
					((Habitacion) game.getScreen()).horaDeElegir();
				}
				
				estadoActual.seIniciaMision(true);
				
				if(estadoActual.getEleccionCorrecta() == 0){
					((Habitacion) game.getScreen()).terminarConversacion();
					estadoActual.eleccionCorrecta(-1);
				}else if(estadoActual.getEleccionCorrecta() == 1){
					estadoActual.seSuperaPuzzle(true);
					((Habitacion) game.getScreen()).terminarConversacion();
					((Habitacion) game.getScreen()).terminarEleccion();
				}
			}
		
			pulsado = false;
		}
	}
}
