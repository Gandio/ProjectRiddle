package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

import Objetos.Puntuacion;
import Puzzle.Asesino.NombreAsesino;
import Puzzle.Inventario;
import Puzzle.PantallaAsesino;
import Puzzle.Arma.NombreArma;
import Pantallas.Atico;
//import Pantallas.Baño;
import Pantallas.Biblioteca;
//import Pantallas.Cocina;
import Pantallas.Dormitorio;
//import Pantallas.Estudio;
import Pantallas.Habitacion;
import Pantallas.Inicio;
import Pantallas.Salon;
import Pantallas.Sotano;

/**
 * Esta clase representa como funciona cada estado, es decir, la lógica del juego.
 * @author Francisco Madueño Chulián
 */

public class OrganizadorEstados {
	private static TheHouseOfCrimes game = Inicio.game;
	private static ArrayList<Estado> estados;
	private static OrganizadorEstados unicaInstancia;
	private static int estadoActual = 0;
	private static int nEstados = 7; //numero total de estados del juego
	private static Estado e;
	
	protected XmlReader reader = new XmlReader();
	protected Element raiz;
	protected Array<Element> pistasAsesino;
	protected Array<Element> pistasArma;
	protected Array<String> pistas;
	protected Array<String> resumenPista;
	protected Array<String> tipoPista;
	
	private static NombreAsesino nombreAsesino;
	private static NombreArma nombreArma;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	private OrganizadorEstados(TheHouseOfCrimes game){
		estados = new ArrayList<Estado>(nEstados-1);
		pistas = new Array<String>(nEstados);
		resumenPista = new Array<String>(nEstados);
		tipoPista = new Array<String>(nEstados);
		
		//Vamos a seleccionar al asesino y almacenar las pistas para descubrirlo
		
		Random rm = new Random();
		/*
		 * 0 - chica
		 * 1 - hombre
		 * 2 - joven
		 * 3 - anciana
		 * 4 - mujer
		 */
		
		int asesino = rm.nextInt((4 - 0) + 1) + 0;
		try{
			if(asesino == 0){
				raiz = reader.parse(Gdx.files.internal("xml/pistasChica.xml"));
				nombreAsesino = NombreAsesino.NIÑA;
				System.out.println("Es la niña");
			}
			else if(asesino == 1){
				raiz = reader.parse(Gdx.files.internal("xml/pistasHombre.xml"));
				nombreAsesino = NombreAsesino.HOMBRE;
				System.out.println("Es el hombre");
			}
			else if(asesino == 2){
				raiz = reader.parse(Gdx.files.internal("xml/pistasJoven.xml"));
				nombreAsesino = NombreAsesino.JOVEN;
			}
			else if(asesino == 3){
				raiz = reader.parse(Gdx.files.internal("xml/pistasMujerMayor.xml"));
				nombreAsesino = NombreAsesino.ANCIANA;
			}
			else{
				raiz = reader.parse(Gdx.files.internal("xml/pistasMujer.xml"));
				nombreAsesino = NombreAsesino.MUJER;
			}
				
		}catch(IOException e){}
		
		pistasAsesino = raiz.getChildrenByName("pista");
		
		for(int i = 0; i < pistasAsesino.size; ++i){
			pistas.add(raiz.getChild(i).getAttribute("texto"));
			resumenPista.add(raiz.getChild(i).getAttribute("resumen"));
			tipoPista.add(raiz.getChildByName("tipoPista").getAttribute("texto"));
		}
		
		//Vamos a escoger el arma y las pistas para descubrirla
		
		rm = new Random();
		int arma = rm.nextInt((3 - 0) + 1) + 0;
		/*
		 * 0 - daga
		 * 1 - pistola
		 * 2 - rifle
		 * 3 - serpiente
		 */
		
		try{
			if(arma == 0){
				raiz = reader.parse(Gdx.files.internal("xml/dagaPistas.xml"));
				nombreArma = NombreArma.DAGA;
			}
			else if(arma == 1){
				raiz = reader.parse(Gdx.files.internal("xml/pistolaPistas.xml"));
				nombreArma = NombreArma.PISTOLA;
			}
			else if(arma == 2){
				raiz = reader.parse(Gdx.files.internal("xml/riflePistas.xml"));
				nombreArma = NombreArma.RIFLE;
			}
			else{
				raiz = reader.parse(Gdx.files.internal("xml/serpientePistas.xml"));
				nombreArma = NombreArma.SERPIENTE;
			}
				
		}catch(IOException e){}
		
		pistasArma= raiz.getChildrenByName("pista");
		
		for(int i = 0; i < pistasArma.size; ++i){
			pistas.add(raiz.getChild(i).getAttribute("texto"));
			resumenPista.add(raiz.getChild(i).getAttribute("resumen"));
			tipoPista.add(raiz.getChildByName("tipoPista").getAttribute("texto"));
		}
		
		//llenamos el array de estados aleatorios y le pasamos una pista al azar
		String siguienteHabitacion = null;
		for(int i = 1; i <= nEstados; ++i){
			int j = rm.nextInt((pistas.size - 0)) + 0;
			
			if(i == 1 || i == 2 || i == 4 || i == 5){
				estados.add(new EstadoDecision(i, pistas.get(j)));
				estados.get(i-1).setResumenPista(resumenPista.get(j));
				estados.get(i-1).setTipoPista(tipoPista.get(j));
			}else if(i == 3 || i == 6){
				estados.add(new EstadoCogerObjeto(i, pistas.get(j)));
				estados.get(i-1).setResumenPista(resumenPista.get(j));
				estados.get(i-1).setTipoPista(tipoPista.get(j));
			}else if(i == 7){
				estados.add(new EstadoCombinarObjeto(i, pistas.get(j)));
				estados.get(i-1).setResumenPista(resumenPista.get(j));
				estados.get(i-1).setTipoPista(tipoPista.get(j));
			}
			
			if(i > 1){
				siguienteHabitacion = estados.get(i-1).getHabitacionInicio();
				estados.get(i-2).crearPista(siguienteHabitacion);
			}
			if(i == 7)
				estados.get(i-1).crearPista("");
			
			
			pistas.removeIndex(j);
		}
	}
	
	/**
	 * Este método actualiza el estado durante todo el juego
	 */
	
	public void actualizarEstado(){
		e = estados.get(estadoActual);
		
		Habitacion habitacionInicio = conversorStringHabitacion(estados.get(estadoActual).getHabitacionInicio());
		
		if(e.getClass().equals(EstadoDecision.class)){
			logicaEstadoDecision(habitacionInicio);
			
		}else if(e.getClass().equals(EstadoCogerObjeto.class)){
			logicaEstadoCogerObjeto(habitacionInicio);
			
		}else if(e.getClass().equals(EstadoCombinarObjeto.class)){
			logicaEstadoCombinarObjeto(habitacionInicio);
		}
		
		//Si se supera el puzzle, pasamos al nuevo estado
		
		if(e.puzzleSuperado()){
			//Linea de archivo de log de pista
			TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" +  TheHouseOfCrimes.getFecha() + ";" + 
					Puntuacion.getError() * (-100) + ";" +Puntuacion.getPuntos() + ";" +  "P" + ";" 
					+ ((Habitacion) game.getScreen()).getPersonaje().toString() + ";" + 
					game.getScreen().getClass().getSimpleName() + ";" + 
					OrganizadorEstados.getEstadoActual().getTipoPista() + ";" + 
					OrganizadorEstados.getEstadoActual().getResumenPista()));
			
			habitacionInicio.terminarConversacion();
			habitacionInicio.getCuadroDialogo().setTexto(habitacionInicio.getCuadroDialogo().getTextoDefecto());
			Puntuacion.setPuntuacion(1000 - e.getContErrores()*100);
			
			//Linea de archivo de log fin mision
			TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" +  
					TheHouseOfCrimes.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" +
					Puntuacion.getPuntos() + ";" +  "F" + ";" + 
					((Habitacion) game.getScreen()).getPersonaje().toString() + ";" + 
					game.getScreen().getClass().getSimpleName() + ";" 
					+ OrganizadorEstados.getEstadoActual().getObjeto()));
			
			estadoActual++;
			
			if(estadoActual >= nEstados){ //Se pasa a la pantalla de seleccion de asesino
				((Habitacion) game.getScreen()).pararMusica();
				game.setScreen(new PantallaAsesino());
			}
		}
	}
	
	/**
	 * Conversor de String a Habitacion
	 * @param s
	 * @return
	 */
	
	private Habitacion conversorStringHabitacion(String s){
		if(s.equals("Atico"))
			return Atico.getInstancia();
		//else if(s.equals("Baño"))
			//return Baño.getInstancia();
		else if(s.equals("Biblioteca"))
			return Biblioteca.getInstancia();
		//else if(s.equals("Cocina"))
			//return Cocina.getInstancia();
		else if(s.equals("Dormitorio"))
			return Dormitorio.getInstancia();
		//else if(s.equals("Estudio"))
			//return Estudio.getInstancia();
		else if(s.equals("Salon"))
			return Salon.getInstancia();
		else if(s.equals("Sotano"))
			return Sotano.getInstancia();
		else
			return null;
	}
	
	/**
	 * Comprueba si el inventario contiene el objeto que se pide en un estado.
	 * @return
	 */
	
	private boolean inventarioContieneObjeto(){
		if(Inventario.getContenido().contains(e.getItem(), false)){ 
			e.seCogeObjeto(true);
			return true;
			/*
			 * El contains solo funciona si es el mismo objeto, como cada vez que combinamos creamos
			 * un objeto que antes no estaba en el universo del juego tenemos que buscar otra forma
			 * de comparar. Comparar por el nombre es la forma más sencilla. Tenemos que asegurarnos
			 * de que el inventario no está vacio, si no se produce un error al intentar acceder al
			 * primer elemento
			 */
		}else if(Inventario.getContenido().size != 0){
			if(Inventario.getContenido().first().toString().equals(e.getItem().toString())){
				e.seCogeObjeto(true);
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	/**
	 * Devuelve el id del asesino
	 * @return
	 */
	
	public static NombreAsesino getAsesino(){
		return nombreAsesino;
	}
	
	/**
	 * Devuelve el id del arma
	 * @return
	 */
	
	public static NombreArma getArma(){
		return nombreArma;
	}
	
	/**
	 * Devuelve el estado actual
	 * @return
	 */
	
	public static Estado getEstadoActual(){
		return e;
	}
	
	/**
	 * Este método se asegura de que solo haya un organizador de estados en el juego
	 * @return
	 */
	
	public static OrganizadorEstados getInstancia(){
		if(unicaInstancia == null)
			unicaInstancia = new OrganizadorEstados(game);
		
		return unicaInstancia; 
	}
	
	//-----------------------------------------------------------------------------------
	//----------------------------- METODOS LOGICAS DE ESTADOS --------------------------
	//-----------------------------------------------------------------------------------
	
	/**
	 * Este método representa los la logica de los subestados que hay dentro de cada estado
	 * principal.
	 */
	
	public static void logicaSubestados(){
		Estado estadoActual = OrganizadorEstados.getEstadoActual();
		//termina la conversacion
		((Habitacion) game.getScreen()).setConversando(false);
		
		//Vamos a hacer un poco de trampa, como salón no aparece como "Salón", esta es la
		//forma de saber si estamos en la misma habitación de la lógica del puzzle.
		String cadenaClase = "class Pantallas." + estadoActual.getHabitacionInicio().toString();
		
		if(estadoActual.getClass().equals(EstadoCogerObjeto.class) || estadoActual.getClass().equals(EstadoCombinarObjeto.class)){
			//Se inicia la misión, solo si estamos en la habitación del inicio del puzzle
			if(cadenaClase.equals(game.getScreen().getClass().toString())){
				estadoActual.seIniciaMision(true);
			}
			
			//Se entrega el objeto, es decir, lo borramos del inventario
			if(cadenaClase.equals(game.getScreen().getClass().toString()) && estadoActual.objetoConseguido()){
				Inventario.borrarObjeto(estadoActual.getItem());
				estadoActual.seSuperaPuzzle(true);
			}
		}else if(estadoActual.getClass().equals(EstadoDecision.class)){
			//Estamos en la habitacion de inicio y además ya se ha hablado con la persona, nos muestran las elecciones
			if(cadenaClase.equals(game.getScreen().getClass().toString()) && estadoActual.misionEnCurso()){				
				((Habitacion) game.getScreen()).horaDeElegir();
			}
			
			if(cadenaClase.equals(game.getScreen().getClass().toString()) && !estadoActual.misionEnCurso()){
				estadoActual.seIniciaMision(true);
				
				//Linea de archivo de log inicio mision
				TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" +  
						TheHouseOfCrimes.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" +
						Puntuacion.getPuntos() + ";" +  "I" + ";" + 
						((Habitacion) game.getScreen()).getPersonaje().toString() + ";" + 
						game.getScreen().getClass().getSimpleName() + ";" + OrganizadorEstados.getEstadoActual().getObjeto()));
			}
			
			if(((EstadoDecision) estadoActual).getEleccionCorrecta() == 0){
				((Habitacion) game.getScreen()).terminarConversacion();
				e.aumentarContErrores();
				Puntuacion.sumarError();
				((EstadoDecision) estadoActual).eleccionCorrecta(-1);
			}else if(((EstadoDecision) estadoActual).getEleccionCorrecta() == 1){
				estadoActual.seSuperaPuzzle(true);
				((Habitacion) game.getScreen()).terminarConversacion();
				((Habitacion) game.getScreen()).terminarEleccion();
			}
		}
	}
	
	/**
	 * Logica de los estados de decision
	 * @param habitacionInicio
	 */
	
	private void logicaEstadoDecision(Habitacion habitacionInicio){
		//Actualizamos el objetivo
		Inventario.getCuadroEstado().setTexto(e.getObjetivo1());
		
		//Se termina de inicializar los cuadros eleccion
		for(int i = 0; i < 4; ++i){ //Siempre hay 4 cuadros de eleccion, lo valores no cambian
			habitacionInicio.getCuadrosEleccion().get(i).setTexto(((EstadoDecision) e).getTextoEleccion(i));
			if(((EstadoDecision) e).getEleccion(i).equals("si"))
				habitacionInicio.getCuadrosEleccion().get(i).setEleccion(1);
			else
				habitacionInicio.getCuadrosEleccion().get(i).setEleccion(0);
		}
		
		if(e.misionEnCurso()){
			//Aparecería un cuadro de texto, diciendo "Ya lo sabes?"
			Inventario.getCuadroEstado().setTexto(e.getObjetivo2());
			if(((EstadoDecision) e).getEleccionCorrecta() == -1){
				habitacionInicio.getCuadroDialogo().setTexto(e.getPrePuzzle());
			}
			
			//seguidamente aparecerían los 4 cuadros eleccion, esto pasa cuando se pulsa el botón de final de conversacion
			//si pulsas uno incorrecto aparecería un cuadro de texto "vuelve a intentarlo" y se terminaria la secuencia
			else if(((EstadoDecision) e).getEleccionCorrecta() == 0){
				habitacionInicio.getCuadroDialogo().setTexto(((EstadoDecision) e).getError());
			}else{ //si pulsas el correcto aparece el cuadro de texto con la pista y continua el juego
				if(e.getContErrores() > 2)
					habitacionInicio.getCuadroDialogo().setTexto(e.getFinalSinPista());
				else
					habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
			}
		}else{
			habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
		}
	}
	
	/**
	 * Logica de los estados de recoger objetos
	 * @param habitacionInicio
	 */
	
	private void logicaEstadoCogerObjeto(Habitacion habitacionInicio){
		Habitacion habitacionDestino = conversorStringHabitacion(estados.get(estadoActual).getHabitacionDestino());
		String objeto = estados.get(estadoActual).getObjeto();
		//Actualizamos el objetivo
		Inventario.getCuadroEstado().setTexto(e.getObjetivo1());
	
		//Ya has aceptado la misión, no se vuelve a repetir la conversación
		if(e.misionEnCurso()){
			Inventario.getCuadroEstado().setTexto(e.getObjetivo2());
			habitacionInicio.getCuadroDialogo().setTexto(e.getPostPuzzle());
		
			//Se habilita el objeto para que se pueda coger
			((EstadoCogerObjeto) e).permitirCogerObjeto(habitacionDestino, objeto);
		}else{
			habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
		}
	
		if(inventarioContieneObjeto()){
			if(e.getContErrores() > 2)
				habitacionInicio.getCuadroDialogo().setTexto(e.getFinalSinPista());
			else
				habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
		}
	}
	
	/**
	 * Logica de los estados de combinar objetos
	 * @param habitacionInicio
	 */
	
	private void logicaEstadoCombinarObjeto(Habitacion habitacionInicio){
		Habitacion habitacionDestino1 = conversorStringHabitacion(estados.get(estadoActual).getHabitacionDestino());
		Habitacion habitacionDestino2 = conversorStringHabitacion(((EstadoCombinarObjeto) estados.get(estadoActual)).getHabitacionDestino2());
		String objetoCombinacion1 = ((EstadoCombinarObjeto) estados.get(estadoActual)).getObjetoCombinacion1();
		String objetoCombinacion2 = ((EstadoCombinarObjeto) estados.get(estadoActual)).getObjetoCombinacion2();
		Inventario.getCuadroEstado().setTexto(e.getObjetivo1());
		
		//Actualizamos el objetivo
		Inventario.getCuadroEstado().setTexto(e.getObjetivo1());
	
		//Ya has aceptado la misión, no se vuelve a repetir la conversación
		if(e.misionEnCurso()){
			Inventario.getCuadroEstado().setTexto(e.getObjetivo2());
			habitacionInicio.getCuadroDialogo().setTexto(e.getPostPuzzle());
		
			//Se habilita el objeto para que se pueda coger
			((EstadoCombinarObjeto) e).permitirCogerObjetosCombinacion(habitacionDestino1, objetoCombinacion1, habitacionDestino2, objetoCombinacion2);
			
		}else{
			habitacionInicio.getCuadroDialogo().setTexto(e.getTextoPersonaje());
		}
	
		if(inventarioContieneObjeto()){
			if(e.getContErrores() > 1)
				habitacionInicio.getCuadroDialogo().setTexto(e.getFinalSinPista());
			else
				habitacionInicio.getCuadroDialogo().setTexto(e.getPistaPersonaje());
		}
	}
}