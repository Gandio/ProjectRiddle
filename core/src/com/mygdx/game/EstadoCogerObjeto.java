package com.mygdx.game;

import java.util.Iterator;

import Items.Objeto;
import Pantallas.Habitacion;

import com.badlogic.gdx.utils.Array;

/**
 * Esta clase representa el estado en el que el puzzle consiste en recoger un objeto y
 * entregarlo al personaje adecuado.
 * @author Francisco Madueño Chulián
 */

public class EstadoCogerObjeto extends Estado{
	
	/**
	 * Constructor de la clase
	 * @param numEstado
	 * @param pista
	 */

	public EstadoCogerObjeto(int numEstado, String pista) {
		super(numEstado, pista);
		
		habitacionDestino = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal");
		objeto = puzzle.getChildByName("objeto").getAttribute("tipoObjeto");
		personaje = puzzle.getChildByName("personaje").getAttribute("tipoPersonaje");
		postPuzzle = puzzle.getChildByName("postPuzzle").getAttribute("texto");
	}
	
	/**
	 * Hace que el objeto implicado en el puzzle se pueda coger, todos los objetos, en un 
	 * principio no se pueden coger.
	 * @param h
	 * @param objeto
	 */
	
	public void permitirCogerObjeto(Habitacion h, String objeto) {
		if (!sePermiteCogerObjeto) {
			Array<Objeto> aux = h.getObjetos();
			Iterator<Objeto> iter = aux.iterator();
			Objeto objetoAux = null;
			Objeto o = null;
			
			/*
			 * Se busca en el vector de objetos de la habitación el objeto que hay que entregar
			 * y se hace que se pueda coger.
			 */

			while (iter.hasNext()) {
				objetoAux = iter.next();
				if (objetoAux.getIdentificador().toString().equals(objeto)) {
					o = objetoAux;
					o.seCoge(true);
				}
			}
			
			item = o;
			sePermiteCogerObjeto = true;
		}
	}
}
