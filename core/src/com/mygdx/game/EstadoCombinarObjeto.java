package com.mygdx.game;

import java.util.Iterator;

import Items.CafeAzucar;
import Items.LibroPintado;
import Items.Objeto;
import Items.SerpienteEnjaulada;
import Pantallas.Habitacion;
import Pantallas.Inicio;

import com.badlogic.gdx.utils.Array;

/**
 * Esta clase representa al estado donde el puzzle consiste en combinar dos objetos y 
 * entregar el objeto resultante a un personaje.
 * @author Francisco Madueño Chulián
 */

public class EstadoCombinarObjeto extends Estado{
	
	private String objetoCombinacion1; // esta variable sirve para el puzzle de combinacion
	private String objetoCombinacion2; // esta variable sirve para el puzzle de combinacion
	private String habitacionDestino2; // esta variable sirve para el puzzle de combinacion
	
	/**
	 * Constructor de la clase
	 * @param numEstado
	 * @param pista
	 */

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
	
	/**
	 * Devuelve el primer objeto de la combinación
	 * @return objetoCombinacion1
	 */
	
	public String getObjetoCombinacion1() {
		return objetoCombinacion1;
	}
	
	/**
	 * Devuelve el segundo objeto de la combinación
	 * @return objetoCombinacion2
	 */

	public String getObjetoCombinacion2() {
		return objetoCombinacion2;
	}
	
	/**
	 * Devuelve la habitación donde se encuentra el segundo objeto de la combinación
	 * @return habitacionDEstino2
	 */
	public String getHabitacionDestino2() {
		return habitacionDestino2;
	}
	
	
	/**
	 * Permite coger los dos objetos que sirven para realizar la combinación.
	 * @param h1
	 * @param objeto1
	 * @param h2
	 * @param objeto2
	 */
	public void permitirCogerObjetosCombinacion(Habitacion h1, String objeto1, Habitacion h2, String objeto2) {
		if (!sePermiteCogerObjeto) {
			Array<Objeto> aux = h1.getObjetos();
			Array<Objeto> aux2 = h2.getObjetos();
			Iterator<Objeto> iter = aux.iterator();
			Objeto objetoAux = null;
			Objeto o = null;
			
			/*se busca el objeto en el vector de objetos de la habitación primera habitación
			 * y se hace que se pueda coger
			 */
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
			
			/*se busca el objeto en el vector de objetos de la habitación segunda habitación
			 * y se hace que se pueda coger
			 */
			while (iter.hasNext()) {
				objetoAux = iter.next();
				if (objetoAux.getIdentificador().toString().equals(objeto2)) {
					o = objetoAux;
					o.seCoge(true);
				}
			}
			
			/*
			 * Se especifica el objeto que hay que entregar
			 */
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
