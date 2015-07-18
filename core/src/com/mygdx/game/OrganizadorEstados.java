package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Object;

import com.badlogic.gdx.utils.Array;

import Items.Objeto;
import Puzzle.Inventario;
import Pantallas.Atico;
import Pantallas.Baño;
import Pantallas.Biblioteca;
import Pantallas.Cocina;
import Pantallas.Dormitorio;
import Pantallas.Estudio;
import Pantallas.Habitacion;
import Pantallas.Inicio;
import Pantallas.Salon;
import Pantallas.Sotano;

public class OrganizadorEstados {
	private static MyGdxGame game = Inicio.game;
	private static ArrayList<Estado> estados;
	private static OrganizadorEstados unicaInstancia;
	private static int estadoActual = 0;
	private static int nEstados = 1; //numero total de estados del juego
	private static Estado e;
	
	private OrganizadorEstados(MyGdxGame game){
		//this.game = game;
		estados = new ArrayList<Estado>();
		
		//llenamos el array de estados aleatorios
		for(int i = 1; i <= nEstados; ++i){
			estados.add(new Estado(i));
		}
	}
	
	public void actualizarEstado(){
		e = estados.get(estadoActual);
		Habitacion habitacionInicio = conversorStringHabitacion(estados.get(estadoActual).getHabitacionInicio());
		Habitacion habitacionDestino = conversorStringHabitacion(estados.get(estadoActual).getHabitacionDestino());
		String objeto = estados.get(estadoActual).getObjeto();
		String personaje = estados.get(estadoActual).getPersonaje();
		Objeto o = null;
		
		//Actualizamos el objetivo
		Inventario.getCuadroDescripcion().setTexto(e.getObjetivo());
		
		//Ya has aceptado la misión, no se vuelve a repetir la conversación
		if(e.estadoMision()){
			habitacionInicio.getCuadroDialogo().setTexto("Was ist los? Zackibacki! Geh!!!!");
			
			//Se habilita el objeto para que se pueda coger
			o = permitirCogerObjeto(habitacionDestino, objeto);
		}else{
			habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
		}
		
		if(inventarioContieneObjeto(o)){
			habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
		}
	}
	
	public static OrganizadorEstados getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new OrganizadorEstados(game);
		
		return unicaInstancia; 
	}
	
	public static Estado getEstadoActual(){
		return e;
	}
	
	private Habitacion conversorStringHabitacion(String s){
		if(s.equals("Atico"))
			return Atico.getInstancia();
		else if(s.equals("Baño"))
			return Baño.getInstancia();
		else if(s.equals("Biblioteca"))
			return Biblioteca.getInstancia();
		else if(s.equals("Cocina"))
			return Cocina.getInstancia();
		else if(s.equals("Dormitorio"))
			return Dormitorio.getInstancia();
		else if(s.equals("Estudio"))
			return Estudio.getInstancia();
		else if(s.equals("Salon"))
			return Salon.getInstancia();
		else if(s.equals("Sotano"))
			return Sotano.getInstancia();
		else
			return null;
	}
	
	private Objeto permitirCogerObjeto(Habitacion h, String objeto){
		Array<Objeto> aux = h.getObjetos();
		Iterator<Objeto> iter = aux.iterator();
		Objeto o = null;
		
		while(iter.hasNext()){
			o = iter.next();
			if(o.getIdentificador().equals(objeto)){
				o.seCoge(true);
			}
		}
		
		return o;
	}
	
	private boolean inventarioContieneObjeto(Objeto o){
		if(Inventario.getContenido().contains(o, true)) return true;
		else return false;
	}
}
