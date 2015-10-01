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

public class TheCrimeHouse extends Game{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	//public static final boolean SUSPENSE = true;
	
	public static final boolean SUSPENSE_AMBIENTE = true;
	//public static final boolean SUSPENSE_MUSICA = false;
	public static final boolean SUSPENSE_OBJETOS = true;
	public static final boolean TEST = false;
	//public static final boolean SUSPENSE_OBJETOS = false;
	
	private static String usuarioActual = "Usuario 1";
	private static ArchivoLog archivoLog = new ArchivoLog();
	
	/**
	 * Se crea la pantalla de Inicio
	 */
	public void create() {
		setScreen(new Inicio(this));
	}
	
	/**
	 * Devuelve el nombre de usuario del jugador
	 * @return
	 */
	public static String getUsuario(){
		return usuarioActual;
	}
	
	/**
	 * Modifica el nombre de usuario del jugador
	 * @param usuario
	 */
	public static void setUsuario(String usuario){
		usuarioActual = usuario;
	}
	
	/**
	 * Devuelve la fecha convertida en cadena
	 * @return
	 */
	public static String getFecha(){
		Date d = new Date(TimeUtils.millis());
		
		return d.toString();
	}
	
	/**
	 * Devuelve el fichero de log de la partida
	 * @return
	 */
	public static ArchivoLog getArchivoLog(){
		return archivoLog;
	}
}