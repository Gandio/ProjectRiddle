package com.mygdx.game;


import Items.CafeAzucar;
import Items.LibroPintado;
import Items.Objeto;
import Items.SerpienteEnjaulada;

import com.badlogic.gdx.graphics.Texture;

/**
 * Esta clase contiene funciones que van a usarse a nivel global en todo el código del juego.
 * @author Francisco Madueño Chulián
 */

public class Tools {
	
	/**
	 * Modifica el alto de una textura para que aparezca centrada en cualquier tamaño de 
	 * pantalla.
	 * @param game
	 * @param textura
	 * @return
	 */
	public static float centrarAlto(MyGdxGame game, Texture textura){
		return MyGdxGame.HEIGHT / 2f - textura.getHeight() / 2f;
	}
	
	/**
	 * Modifica el ancho de una textura para que aparezca centrada en cualquier tamaño de 
	 * pantalla.
	 * @param game
	 * @param textura
	 * @return
	 */
	public static float centrarAncho(MyGdxGame game, Texture textura){
		return MyGdxGame.WIDTH / 2f - textura.getWidth() / 2f;
	}
	
	/**
	 * Dependiendo del id de los objetos que se les pasa devuelve la combinación entre esos
	 * objetos o nada si no se pueden combinar.
	 * @param game
	 * @param i
	 * @param j
	 * @return
	 */
	public static Objeto devolverCombinacion(MyGdxGame game, int i, int j){
		if((i == 0 && j == 2) || (i == 2 && j == 0)) return new CafeAzucar(game);
		else if((i == 1 && j == 4) || (i == 4 && j == 1)) return new LibroPintado(game);
		else if((i == 3 && j == 5) || (i == 5 && j == 3)) return new SerpienteEnjaulada(game);
		else return null;
	}
}