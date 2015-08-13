package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Object;

import com.badlogic.gdx.Gdx;
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
import Pantallas.Habitacion.EstadoHabitacion;

public class OrganizadorEstados {
	private static MyGdxGame game = Inicio.game;
	private static ArrayList<Estado> estados;
	private static OrganizadorEstados unicaInstancia;
	private static int estadoActual = 0;
	private static int nEstados = 5; //numero total de estados del juego
	private static Estado e;
	
	private OrganizadorEstados(MyGdxGame game){
		//this.game = game;
		estados = new ArrayList<Estado>(4);
		
		//llenamos el array de estados aleatorios
		for(int i = 1; i <= nEstados; ++i){
			estados.add(new Estado(i));
		}
	}
	
	public void actualizarEstado(){
		System.out.println("estoy en el estado " + estadoActual);
		e = estados.get(estadoActual);
		if(estadoActual == 0 || estadoActual == 2){
			Habitacion habitacionInicio = conversorStringHabitacion(estados.get(estadoActual).getHabitacionInicio());
			
			//Actualizamos el objetivo
			Inventario.getCuadroEstado().setTexto(e.getObjetivo());
			
			//Se termina de inicializar los cuadros eleccion
			for(int i = 0; i < 4; ++i){ //Siempre hay 4 cuadros de eleccion, lo valores no cambian
				habitacionInicio.getCuadrosEleccion().get(i).setTexto(e.getTextoEleccion(i));
				if(e.getEleccion(i).equals("si"))
					habitacionInicio.getCuadrosEleccion().get(i).setEleccion(1);
				else
					habitacionInicio.getCuadrosEleccion().get(i).setEleccion(0);
			}
			
			if(e.estadoMision()){
				//Aparecería un cuadro de texto, diciendo "Ya lo sabes?"
				if(e.getEleccionCorrecta() == -1){
					habitacionInicio.getCuadroDialogo().setTexto("¿Ya lo sabes?");
				}
				
				//seguidamente aparecerían los 4 cuadros eleccion, esto pasa cuando se pulsa el botón de final de conversacion
				//si pulsas uno incorrecto aparecería un cuadro de texto "vuelve a intentarlo" y se terminaria la secuencia
				else if(e.getEleccionCorrecta() == 0){
					habitacionInicio.getCuadroDialogo().setTexto("Vuelve a intentarlo");
				}else{ //si pulsas el correcto aparece el cuadro de texto con la pista y continua el juego
					habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
				}
			}else{
				habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
			}
			
			
		}else if(estadoActual == 1 || estadoActual == 4){
			Habitacion habitacionInicio = conversorStringHabitacion(estados.get(estadoActual).getHabitacionInicio());
			Habitacion habitacionDestino = conversorStringHabitacion(estados.get(estadoActual).getHabitacionDestino());
			String objeto = estados.get(estadoActual).getObjeto();
			String personaje = estados.get(estadoActual).getPersonaje();
		
			//Actualizamos el objetivo
			Inventario.getCuadroEstado().setTexto(e.getObjetivo());
		
			//Ya has aceptado la misión, no se vuelve a repetir la conversación
			if(e.estadoMision()){
				habitacionInicio.getCuadroDialogo().setTexto("Was ist los? Zackibacki! Geh!!!!");
			
				//Se habilita el objeto para que se pueda coger
				e.permitirCogerObjeto(habitacionDestino, objeto);
			}else{
				habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
			}
		
			if(inventarioContieneObjeto(e.getItem())){
				habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
			}
		}else if(estadoActual == 3){
			Habitacion habitacionInicio = conversorStringHabitacion(estados.get(estadoActual).getHabitacionInicio());
			Habitacion habitacionDestino1 = conversorStringHabitacion(estados.get(estadoActual).getHabitacionDestino());
			Habitacion habitacionDestino2 = conversorStringHabitacion(estados.get(estadoActual).getHabitacionDestino());
			String objeto = estados.get(estadoActual).getObjeto(); //este es el objeto final
			String objetoCombinacion1 = estados.get(estadoActual).getObjetoCombinacion1();
			String objetoCombinacion2 = estados.get(estadoActual).getObjetoCombinacion2();
			String personaje = estados.get(estadoActual).getPersonaje();
			Inventario.getCuadroEstado().setTexto(e.getObjetivo());
			
			//Actualizamos el objetivo
			Inventario.getCuadroEstado().setTexto(e.getObjetivo());
		
			//Ya has aceptado la misión, no se vuelve a repetir la conversación
			if(e.estadoMision()){
				habitacionInicio.getCuadroDialogo().setTexto("Was ist los? Zackibacki! Geh!!!!");
			
				//Se habilita el objeto para que se pueda coger
				e.permitirCogerObjeto(habitacionDestino1, objetoCombinacion1);
				e.permitirCogerObjeto(habitacionDestino2, objetoCombinacion2);
			}else{
				habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
			}
		
			if(inventarioContieneObjeto(e.getItem())){
				habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
			}			
			
		}
		//Si se supera el puzzle, pasamos al nuevo estado
		if(e.estadoPuzzle()){
			estadoActual++;
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
	
	private boolean inventarioContieneObjeto(Objeto o){
		System.out.println(e.getItem());
		if(Inventario.getContenido().contains(e.getItem(), false)){ 
			e.seCogeObjeto(true);
			return true;
		}else{
			return false;
		}
	}
}
