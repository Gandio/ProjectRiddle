package Controladores;

import com.mygdx.game.MyGdxGame;

import Objetos.BotonPuerta;
import Pantallas.Habitacion;
import Pantallas.Pantalla;

/**
 * Esta clase se encarga de la lógica en general del botón puerta.
 * @author Francisco Madueño Chulián
 *
 */

public abstract class ControladorBotonPuerta {
	protected MyGdxGame game;
	private Pantalla pantalla;
	protected BotonPuerta boton;
	
	public ControladorBotonPuerta(Pantalla p, MyGdxGame game) {
		this.game = game;
		pantalla = p;
	}
	
	public ControladorBotonPuerta(Habitacion h, MyGdxGame game){
		this.game = game;
		pantalla = h;
	}
	
	public void update() {}
}