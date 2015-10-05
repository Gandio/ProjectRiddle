package com.mygdx.game;


import Items.CafeAzucar;
import Items.LibroPintado;
import Items.Objeto;
import Items.SerpienteEnjaulada;
import Pantallas.Inicio;
import Pantallas.Pasillo;

import com.badlogic.gdx.graphics.Texture;

/**
 * Esta clase contiene funciones que van a usarse a nivel global en todo el código del juego.
 * @author Francisco Madueño Chulián
 */

public class Tools {
	
	private static Objeto serpienteEnjaulada = new SerpienteEnjaulada(Pasillo.game);
	private static Objeto cafeAzucar = new CafeAzucar(Inicio.game);
	private static Objeto libroPintado = new LibroPintado(Inicio.game);
	
	
	/**
	 * Modifica el alto de una textura para que aparezca centrada en cualquier tamaño de 
	 * pantalla.
	 * @param game
	 * @param textura
	 * @return
	 */
	public static float centrarAlto(TheHouseOfCrimes game, Texture textura){
		return TheHouseOfCrimes.HEIGHT / 2f - textura.getHeight() / 2f;
	}
	
	/**
	 * Modifica el ancho de una textura para que aparezca centrada en cualquier tamaño de 
	 * pantalla.
	 * @param game
	 * @param textura
	 * @return
	 */
	public static float centrarAncho(TheHouseOfCrimes game, Texture textura){
		return TheHouseOfCrimes.WIDTH / 2f - textura.getWidth() / 2f;
	}
	
	/**
	 * Dependiendo del id de los objetos que se les pasa devuelve la combinación entre esos
	 * objetos o nada si no se pueden combinar.
	 * @param game
	 * @param i
	 * @param j
	 * @return
	 */
	public static Objeto devolverCombinacion(TheHouseOfCrimes game, int i, int j){
		if((i == 0 && j == 2) || (i == 2 && j == 0)) return cafeAzucar;
		else if((i == 1 && j == 4) || (i == 4 && j == 1)) return libroPintado;
		else if((i == 3 && j == 5) || (i == 5 && j == 3)) return serpienteEnjaulada;
		else return null;
	}
}