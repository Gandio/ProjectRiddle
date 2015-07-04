package com.mygdx.game;

import java.util.ArrayList;
import java.lang.Object;

import Items.Objeto;
import Puzzle.Inventario;
import Pantallas.Habitacion;

public class OrganizadorEstados {
	private static MyGdxGame game;
	private static ArrayList<Estado> estados;
	private static OrganizadorEstados unicaInstancia;
	private static int estadoActual = 0;
	private static int nEstados = 1; //numero total de estados del juego
	
	private OrganizadorEstados(MyGdxGame game){
		this.game = game;
		estados = new ArrayList<Estado>();
		
		//llenamos el array de estados aleatorios
		for(int i = 1; i <= nEstados; ++i){
			estados.add(new Estado(i));
		}
	}
	
	public void actualizarEstado(){
		Estado e = estados.get(estadoActual);
		Object habitacionInicio = estados.get(estadoActual).getHabitacionInicio();
		Object habitacionDestino = estados.get(estadoActual).getHabitacionDestino();
		Object objeto = estados.get(estadoActual).getObjeto();
		Object personaje = estados.get(estadoActual).getPersonaje();
		
		//Actualizamos el objetivo
		Inventario.getCuadroDescripcion().setTexto(e.getObjetivo());
		
		//Ya has aceptado la misión, no se vuelve a repetir la conversación
		if(e.estadoMision()){
			((Habitacion) habitacionInicio).getCuadroDialogo().setTexto("Was ist los? Zackibacki! Geh!!!!");
		}else{
			((Habitacion) habitacionInicio).getCuadroDialogo().setTexto(e.getTextoPersonaje());
			e.seIniciaMision(true);
		}
		
		//Se habilita el objeto para que se pueda coger
		((Objeto) objeto).seCoge(true);
		
	}
	
	public static OrganizadorEstados getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new OrganizadorEstados(game);
		
		return unicaInstancia; 
	}

}
