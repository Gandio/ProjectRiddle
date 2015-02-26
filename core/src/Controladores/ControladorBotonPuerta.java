package Controladores;

import com.mygdx.game.MyGdxGame;

import Objetos.BotonPuertaPasillo;
import Pantallas.Habitacion;
import Pantallas.Pantalla;
import Pantallas.Pasillo;

/**
 * Esta clase se encarga de la lógica en general del botón puerta.
 * @author Francisco Madueño Chulián
 *
 */

public abstract class ControladorBotonPuerta {
	protected MyGdxGame game;
	private Pasillo pasillo;
	private Habitacion habitacion;
	protected BotonPuertaPasillo boton;
	
	public ControladorBotonPuerta(Pasillo pasillo, MyGdxGame game) {
		this.game = game;
		this.pasillo = pasillo;
		this.habitacion = null;
	}
	
	public ControladorBotonPuerta(Habitacion h, MyGdxGame game){
		this.game = game;
		habitacion = h;
		pasillo = null;
	}
	
	public void update() {}
}