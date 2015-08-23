package com.mygdx.game;

import java.util.Iterator;

import Items.CafeAzucar;
import Items.LibroPintado;
import Items.Objeto;
import Items.SerpienteEnjaulada;
import Pantallas.Habitacion;
import Pantallas.Inicio;

import com.badlogic.gdx.utils.Array;


public class EstadoCombinarObjeto extends Estado{
	
	private String objetoCombinacion1; // esta variable sirve para el puzzle de combinacion
	private String objetoCombinacion2; // esta variable sirve para el puzzle de combinacion
	private String habitacionDestino2; // esta variable sirve para el puzzle de combinacion

	public EstadoCombinarObjeto(int numEstado, String pista) {
		super(numEstado, pista);
		
		habitacionDestino = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal1");
		habitacionDestino2 = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal2");
		objeto = puzzle.getChildByName("objeto").getAttribute("tipoObjetoFinal");
		objetoCombinacion1 = puzzle.getChildByName("objeto").getAttribute("tipoObjeto1");
		objetoCombinacion2 = puzzle.getChildByName("objeto").getAttribute("tipoObjeto2");
		personaje = puzzle.getChildByName("personaje").getAttribute("tipoPersonaje");
		postPuzzle = puzzle.getChildByName("postPuzzle").getAttribute("texto");
	}
	
	public String getObjetoCombinacion1() {
		return objetoCombinacion1;
	}

	public String getObjetoCombinacion2() {
		return objetoCombinacion2;
	}
	
	public String getHabitacionDestino2() {
		return habitacionDestino2;
	}
	
	public void permitirCogerObjetosCombinacion(Habitacion h1, String objeto1, Habitacion h2, String objeto2) {
		if (!sePermiteCogerObjeto) {
			Array<Objeto> aux = h1.getObjetos();
			Array<Objeto> aux2 = h2.getObjetos();
			Iterator<Objeto> iter = aux.iterator();
			Objeto objetoAux = null;
			Objeto o = null;

			while (iter.hasNext()) {
				objetoAux = iter.next();
				if (objetoAux.getIdentificador().toString().equals(objeto1)) {
					o = objetoAux;
					o.seCoge(true);
				}
			}
			
			objetoAux = null;
			o = null;
			iter = aux2.iterator();
			
			while (iter.hasNext()) {
				objetoAux = iter.next();
				if (objetoAux.getIdentificador().toString().equals(objeto2)) {
					o = objetoAux;
					o.seCoge(true);
				}
			}
			
			if(objeto1.equals("Cafe") || objeto2.equals("Cafe")){
				item = new CafeAzucar(Inicio.game);	
			}else if(objeto1.equals("Libro") || objeto2.equals("Libro")){
				item = new LibroPintado(Inicio.game);
			}else if(objeto1.equals("Serpiente") || objeto2.equals("Serpiente")){
				item = new SerpienteEnjaulada(Inicio.game);
			}
			sePermiteCogerObjeto = true;
		}
	}
}
