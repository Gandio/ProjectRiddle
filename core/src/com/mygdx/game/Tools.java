package com.mygdx.game;


import Items.CafeAzucar;
import Items.Identificador;
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
	public static Objeto devolverCombinacion(TheHouseOfCrimes game, Identificador i, Identificador j){
		if((i == Identificador.Azucar && j == Identificador.Cafe) || 
		   (i == Identificador.Cafe && j == Identificador.Azucar)) return cafeAzucar;
		else if((i == Identificador.Libro && j == Identificador.Boligrafo) || 
				(i == Identificador.Boligrafo && j == Identificador.Libro)) return libroPintado;
		else if((i == Identificador.Jaula && j == Identificador.Serpiente) || 
				(i == Identificador.Serpiente && j == Identificador.Jaula)) return serpienteEnjaulada;
		else return null;
	}
}