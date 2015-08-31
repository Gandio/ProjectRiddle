package com.mygdx.game;

import Pantallas.Inicio;

import com.badlogic.gdx.Game;

/**
 * Clase principal del juego.
 * @author Francisco Madueño Chulián
 *
 */

public class MyGdxGame extends Game{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	public static final boolean SUSPENSE = true;
	
	public static final boolean SUSPENSE_AMBIENTE = true;
	public static final boolean SUSPENSE_MUSICA = true;
	public static final boolean SUSPENSE_OBJETOS = true;
	
	/**
	 * Se crea la pantalla de Inicio
	 */
	public void create() {
		setScreen(new Inicio(this));
	}
}