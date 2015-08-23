package com.mygdx.game;

import java.io.IOException;
import java.util.Random;

import Items.Objeto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

//Esto tiene toda la pinta de clase abstracta, habrá que modificarlo cuando todo funcione
public abstract class Estado {
	private String habitacionInicio;
	protected String habitacionDestino;
	protected String objeto;
	protected String personaje;
	private String textoPersonaje;
	private String pistaPersonaje;
	private String objetivo1;
	private String objetivo2;
	private String siguienteHabitacion; // indica la habitacion donde se desarrolla el próximo puzzle
	
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

	public Estado(int numEstado, String pista) {
		try {
			if (MyGdxGame.SUSPENSE_AMBIENTE)
				raiz = reader.parse(Gdx.files
						.internal("xml/logicaAlemanSuspense.xml"));
			else
				raiz = reader.parse(Gdx.files
						.internal("xml/logicaAlemanSinSuspense.xml"));
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
		
		contErrores = 0;
	}

	public String getHabitacionInicio() {
		return habitacionInicio;
	}

	public String getHabitacionDestino() {
		return habitacionDestino;
	}

	public String getObjeto() {
		return objeto;
	}

	public String getPersonaje() {
		return personaje;
	}

	public String getTextoPersonaje() {
		return textoPersonaje;
	}

	public String getPistaPersonaje() {
		return pistaPersonaje;
	}

	public String getObjetivo1() {
		return objetivo1;
	}
	
	public String getObjetivo2(){
		return objetivo2;
	}

	public boolean puzzleSuperado() {
		return puzzleSuperado;
	}

	public boolean misionEnCurso() {
		return misionEnCurso;
	}

	public boolean objetoConseguido() {
		return objetoConseguido;
	}

	public void seSuperaPuzzle(boolean b) {
		puzzleSuperado = b;
	}

	public void seIniciaMision(boolean b) {
		misionEnCurso = b;

	}

	public void seCogeObjeto(boolean b) {
		objetoConseguido = b;
	}

	public void setItem(Objeto o) {
		item = o;
	}

	public Objeto getItem() {
		return item;
	}
	
	public String getPrePuzzle(){
		return prePuzzle;
	}
	
	public String getPostPuzzle(){
		return postPuzzle;
	}

	public int getNumEstado() {
		return numEstado;
	}
	
	public int getContErrores(){
		return contErrores;
	}
	
	public void aumentarContErrores(){
		contErrores++;
	}
	
	public void crearPista(String sigHab){
		if(sigHab == null)
			System.out.println("algo falla o es el estado inicial");
		else if(sigHab.equals("Salon"))
			siguienteHabitacion = "Geh jetzt bitte ins Wohnzimmer und such neue Informationen!";
		else if(sigHab.equals("Dormitorio"))
			siguienteHabitacion = "Geh jetzt bitte ins Schlafzimmer und such neue Informationen!";
		else if(sigHab.equals("Atico"))
			siguienteHabitacion = "Geh jetzt bitte auf den Dachboden und such neue Informationen!";
		else if(sigHab.equals("Sotano"))
			siguienteHabitacion = "Geh jetzt bitte in den Keller und such neue Informationen!";
		else if(sigHab.equals("Biblioteca"))
			siguienteHabitacion = "Geh jetzt bitte die Bibliothek und such neue Informationen!";
		else if(sigHab.equals("Cocina"))
			siguienteHabitacion = "Geh jetzt bitte la cocina und such neue Informationen!";
		else if(sigHab.equals("Estudio"))
			siguienteHabitacion = "Geh jetzt bitte al estudio und such neue Informationen!";
		else if(sigHab.equals("Baño"))
			siguienteHabitacion = "Geh jetzt bitte al baño und such neue Informationen!";
		else
			siguienteHabitacion = "fallo";

		pistaPersonaje = pistaPersonaje + siguienteHabitacion;
	}
}