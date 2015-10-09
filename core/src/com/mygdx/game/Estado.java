package com.mygdx.game;

import java.io.IOException;
import java.util.Random;

import Items.Objeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Esta clase representa la generalización de los estados del juego.
 * @author Francisco Madueño Chulián
 */

public abstract class Estado {
	private String habitacionInicio;
	protected String habitacionDestino;
	protected String objeto;
	protected String personaje;
	private String textoPersonaje;
	private String pistaPersonaje;
	private String finalSinPista;
	private String objetivo1;
	private String objetivo2;
	private String agradecimiento;
	protected String siguienteHabitacion = ""; // indica la habitacion donde se desarrolla el próximo puzzle
	protected String resumenPista;
	protected String tipoPista;
	
	
	protected Objeto item;
	
	protected Random rm = new Random();
	
	protected int idPuzzle;
	protected Element puzzle, child;

	protected String prePuzzle, postPuzzle;

	protected XmlReader reader = new XmlReader();
	protected Element raiz;
	protected Array<Element> estados;

	private boolean puzzleSuperado = false;
	protected boolean sePermiteCogerObjeto = false;
	private boolean objetoConseguido = false;
	private boolean misionEnCurso = false;

	private int numEstado;
	private int contErrores;
	
	/**
	 * Constructor de la clase
	 * @param numEstado
	 * @param pista
	 */

	public Estado(int numEstado, String pista) {
		try {
			if (TheHouseOfCrimes.SUSPENSE_OBJETOS)
				if(TheHouseOfCrimes.TEST)
					raiz = reader.parse(Gdx.files.internal("xml/prueba.xml"));
				else
					raiz = reader.parse(Gdx.files.internal("xml/logicaAlemanSuspense.xml"));
			else
				raiz = reader.parse(Gdx.files.internal("xml/logicaAlemanSinSuspense.xml"));
		} catch (IOException e) {
		}

		estados = raiz.getChildrenByName("estado");

		this.numEstado = numEstado - 1;
		
		child = raiz.getChild(this.numEstado);
		int numPuzzles = child.getChildCount() - 1; //El vector empieza en 0, restamos uno
		idPuzzle = rm.nextInt((numPuzzles - 0) + 1) + 0;
		puzzle = child.getChild(idPuzzle);

		habitacionInicio = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionInicio");
		objetivo1 = puzzle.getChildByName("objetivo1").getAttribute("texto");
		objetivo2 = puzzle.getChildByName("objetivo2").getAttribute("texto");
		textoPersonaje = puzzle.getChildByName("dialogo").getAttribute("texto");
		pistaPersonaje = pista;
		agradecimiento = puzzle.getChildByName("agradecimiento").getAttribute("texto");
		
		contErrores = 0;
	}
	
	/**
	 * Devuelve la habitacion de inicio
	 * @return habitacionInicio
	 */

	public String getHabitacionInicio() {
		return habitacionInicio;
	}
	
	/**
	 * Devuelve la habitacion de destino
	 * @return habitacionDestino
	 */

	public String getHabitacionDestino() {
		return habitacionDestino;
	}
	
	/**
	 * Devuelve el nombre del objeto que hay que buscar en ese estado
	 * @return objeto
	 */

	public String getObjeto() {
		return objeto;
	}
	
	/**
	 * Devuele el personaje con el que hay que hablar en ese estado
	 * @return personaje
	 */

	public String getPersonaje() {
		return personaje;
	}
	
	/**
	 * Devuelve el texto actual del personaje
	 * @return textoPersonaje
	 */

	public String getTextoPersonaje() {
		return textoPersonaje;
	}

	/**
	 * Devuelve la pista que te da el personaje
	 * @return pistaPersonaje
	 */
	public String getPistaPersonaje() {
		return pistaPersonaje;
	}

	/**
	 * Devuelve el primer objetivo del estado
	 * @return objetivo1
	 */
	public String getObjetivo1() {
		return objetivo1;
	}
	
	/**
	 * Devuelve el segundo objetivo del estado
	 * @return objetivo2
	 */
	
	public String getObjetivo2(){
		return objetivo2;
	}

	/**
	 * Comprueba si el puzzle se ha superado o no
	 * @return
	 */
	public boolean puzzleSuperado() {
		return puzzleSuperado;
	}
	
	/**
	 * Comprueba si el jugador ya ha hablado con el personaje implicado en el estado
	 * @return
	 */

	public boolean misionEnCurso() {
		return misionEnCurso;
	}
	
	/**
	 * Comprueba si el jugador ha conseguido el objeto del estado
	 * @return
	 */

	public boolean objetoConseguido() {
		return objetoConseguido;
	}
	
	/**
	 * Cambia el valor de la variable puzzleSuperado
	 * @param b
	 */

	public void seSuperaPuzzle(boolean b) {
		puzzleSuperado = b;
	}
	
	/**
	 * Cambia el valor de la variable misionEnCurso
	 * @param b
	 */

	public void seIniciaMision(boolean b) {
		misionEnCurso = b;
	}
	
	/**
	 * Cambia el valor de la variable objetoConseguido
	 * @param b
	 */

	public void seCogeObjeto(boolean b) {
		objetoConseguido = b;
	}
	
	/**
	 * Cambia el objeto que hay que buscar en cada estado
	 * @param o
	 */

	public void setItem(Objeto o) {
		item = o;
	}
	
	/**
	 * Devuelve el objeto que hay que buscar en el estado
	 * @return item
	 */

	public Objeto getItem() {
		return item;
	}
	
	/**
	 * Devuelve el texto que se muestra antes de iniciar el puzzle
	 * @return prePuzzle
	 */
	
	public String getPrePuzzle(){
		return prePuzzle;
	}
	
	/**
	 * Devuelve el texto que se muestra despues de iniciar el puzzle
	 * @return postPuzzle
	 */
	public String getPostPuzzle(){
		return postPuzzle;
	}
	
	/**
	 * Devuelve el numero de estado
	 * @return numEstado
	 */

	public int getNumEstado() {
		return numEstado;
	}
	
	/**
	 * Devuelve el contador de errores
	 * @return contErrores
	 */
	
	public int getContErrores(){
		return contErrores;
	}
	
	/**
	 * Aumenta en 1 el numero de errores cometidos en el estado
	 */
	public void aumentarContErrores(){
		contErrores++;
	}
	
	/**
	 * Devuelve el resumen de la pista
	 * @return resumenPista
	 */
	
	public String getResumenPista(){
		return resumenPista;
	}
	
	/**
	 * Modifica el resumen de la pista
	 * @param s
	 */
	public void setResumenPista(String s){
		resumenPista = s;
	}
	
	/**
	 * Devuelve el tipo de pista, arma o asesino
	 * @return tipoPista
	 */
	public String getTipoPista(){
		return tipoPista;
	}
	
	/**
	 * Modifica el tipo de pista
	 * @param s
	 */
	public void setTipoPista(String s){
		tipoPista = s;
	}
	
	/**
	 * Devuelve el dialogo final de un puzzle, pero sin la pista del personaje
	 * @return
	 */
	public String getFinalSinPista(){
		return finalSinPista;
	}
	
	/**
	 * Escoge la pista que te dará el personaje una vez acabado el puzzle
	 * @param sigHab
	 */
	
	public void crearPista(String sigHab){
		XmlReader reader = new XmlReader();
		Element raiz = null;
		Array<Element> indicaciones;
		
		try {
			raiz = reader.parse(Gdx.files.internal("xml/indicaciones.xml"));
		} catch (IOException e) {}
		
		indicaciones = raiz.getChildrenByName("habitacion");
		
		for (Element child : indicaciones){	
			if(sigHab.equals(child.getAttribute("nombre")))
				siguienteHabitacion = child.getAttribute("texto");
		}
		
		finalSinPista = agradecimiento + " "  + siguienteHabitacion;
		pistaPersonaje = agradecimiento + " " + pistaPersonaje + " " + siguienteHabitacion;
	}
}