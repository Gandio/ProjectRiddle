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
	//public static final boolean SUSPENSE = true;
	
	public static final boolean SUSPENSE_AMBIENTE = false;
	//public static final boolean SUSPENSE_MUSICA = false;
	public static final boolean SUSPENSE_OBJETOS = false;
	public static final boolean TEST = false;
	//public static final boolean SUSPENSE_OBJETOS = false;
	
	/**
	 * Se crea la pantalla de Inicio
	 */
	public void create() {
		setScreen(new Inicio(this));
	}
}