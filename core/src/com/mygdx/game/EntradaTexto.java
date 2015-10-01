package com.mygdx.game;

import com.badlogic.gdx.Input.TextInputListener;
/**
 * Esta clase permite al usuario introducir su nombre al principio de la partida, por defecto
 * el usuario se llama Usuario 1
 * @author Francisco Madueño Chulián
 *
 */
public class EntradaTexto implements TextInputListener{

	@Override
	public void input(String usuario) {
		TheCrimeHouse.setUsuario(usuario);
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
}
