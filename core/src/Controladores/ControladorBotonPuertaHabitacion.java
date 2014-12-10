package Controladores;

import Pantallas.Habitacion;
import Pantallas.Pasillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase se encarga de la lógica del botón puerta, el botón solo estará activo cuando no 
 * se está conversando con el personaje que se encuentra en la habitación.
 * @author Francisco Madueño Chulián
 */

public class ControladorBotonPuertaHabitacion extends ControladorBotonPuerta{
	private ControladorBotonConversacion controladorConversacion;
	private ControladorBotonInvestigar controladorInvestigar;

	public ControladorBotonPuertaHabitacion(Habitacion h, MyGdxGame game) {
		super(h, game);
	}
	
	public void update(){
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			game.setScreen(new Pasillo(game));
		}
	}
}
