package com.mygdx.game;

import java.util.Iterator;

import Items.Objeto;
import Pantallas.Habitacion;

import com.badlogic.gdx.utils.Array;

public class EstadoCogerObjeto extends Estado{

	public EstadoCogerObjeto(int numEstado, String pista) {
		super(numEstado, pista);
		
		habitacionDestino = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal");
		objeto = puzzle.getChildByName("objeto").getAttribute("tipoObjeto");
		personaje = puzzle.getChildByName("personaje").getAttribute("tipoPersonaje");
		postPuzzle = puzzle.getChildByName("postPuzzle").getAttribute("texto");
	}
	
	public void permitirCogerObjeto(Habitacion h, String objeto) {
		if (!sePermiteCogerObjeto) {
			Array<Objeto> aux = h.getObjetos();
			Iterator<Objeto> iter = aux.iterator();
			Objeto objetoAux = null;
			Objeto o = null;

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
