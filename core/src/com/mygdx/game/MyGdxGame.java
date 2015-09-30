package com.mygdx.game;


import java.util.Date;

import Pantallas.Inicio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Clase principal del juego.
 * @author Francisco Madueño Chulián
 *
 */

public class MyGdxGame extends Game{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	//public static final boolean SUSPENSE = true;
	
	public static final boolean SUSPENSE_AMBIENTE = true;
	//public static final boolean SUSPENSE_MUSICA = false;
	public static final boolean SUSPENSE_OBJETOS = true;
	public static final boolean TEST = false;
	//public static final boolean SUSPENSE_OBJETOS = false;
	
	private static String usuarioActual;
	private static ArchivoLog archivoLog = new ArchivoLog();
	
	/**
	 * Se crea la pantalla de Inicio
	 */
	public void create() {
		setScreen(new Inicio(this));
	}
	
	public static String getUsuario(){
		return usuarioActual;
	}
	
	public static void setUsuario(String usuario){
		usuarioActual = usuario;
	}
	
	public static String getFecha(){
		Date d = new Date(TimeUtils.millis());
		
		return d.toString();
	}
	
	public static ArchivoLog getArchivoLog(){
		return archivoLog;
	}
}