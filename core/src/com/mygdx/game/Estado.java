package com.mygdx.game;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import Items.CafeAzucar;
import Items.LibroPintado;
import Items.Objeto;
import Items.SerpienteEnjaulada;
import Pantallas.Habitacion;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

//Esto tiene toda la pinta de clase abstracta, habrá que modificarlo cuando todo funcione
public class Estado {
	private String habitacionInicio;
	private String habitacionDestino;
	private String habitacionDestino2; // esta variable sirve para el puzzle de combinacion
	private String objeto;
	private String objetoCombinacion1; // esta variable sirve para el puzzle de combinacion
	private String objetoCombinacion2; // esta variable sirve para el puzzle de combinacion
	private Objeto item;
	private String personaje;
	private String textoPersonaje;
	private String pistaPersonaje;
	private String objetivo1;
	private String objetivo2;
	private String siguienteHabitacion; // indica la habitacion donde se desarrolla el próximo puzzle

	private String textoOpcion1;
	private String textoOpcion2;
	private String textoOpcion3;
	private String textoOpcion4;

	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	
	private String error;
	private String prePuzzle;
	private String postPuzzle;

	protected XmlReader reader = new XmlReader();
	protected Element raiz;
	protected Array<Element> estados;

	private boolean puzzleSuperado = false;
	private boolean sePermiteCogerObjeto = false;
	private boolean objetoConseguido = false;
	private boolean misionEnCurso = false;
	private int eleccionCorrecta = -1; // tri estado -1 no lo has intentado todavia, 0 has fallado, 1 correcto

	private int numEstado;

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

		// Cogemos el elemento estado actual
		Element child = raiz.getChild(numEstado - 1);

		// Dentro del estado actual seleccionamos, al azar el puzzle que se va a
		// ejecutar

		Random rm = new Random();
		int idPuzzle;
		Element puzzle = null;

		if (numEstado == 1 || numEstado == 3) {// Puzzles de decision
			idPuzzle = 0;
			puzzle = child.getChild(idPuzzle);

			textoOpcion1 = puzzle.getChildByName("opcion1").getAttribute("texto");
			textoOpcion2 = puzzle.getChildByName("opcion2").getAttribute("texto");
			textoOpcion3 = puzzle.getChildByName("opcion3").getAttribute("texto");
			textoOpcion4 = puzzle.getChildByName("opcion4").getAttribute("texto");

			opcion1 = puzzle.getChildByName("opcion1").getAttribute("correcto");
			opcion2 = puzzle.getChildByName("opcion2").getAttribute("correcto");
			opcion3 = puzzle.getChildByName("opcion3").getAttribute("correcto");
			opcion4 = puzzle.getChildByName("opcion4").getAttribute("correcto");
			
			error = puzzle.getChildByName("fallo").getAttribute("texto");
			prePuzzle = puzzle.getChildByName("prePuzzle").getAttribute("texto");

		} else if (numEstado == 2 || numEstado == 5) { // en estos estados se realizará el primer tipo de puzzle
			idPuzzle = rm.nextInt((4 - 0) + 1) + 0;
			puzzle = child.getChild(idPuzzle);

			habitacionDestino = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal");
			objeto = puzzle.getChildByName("objeto").getAttribute("tipoObjeto");
			personaje = puzzle.getChildByName("personaje").getAttribute("tipoPersonaje");
			postPuzzle = puzzle.getChildByName("postPuzzle").getAttribute("texto");
		
		} else if (numEstado == 4) {
			// Estoy hay que modificarlo para que sirva para la versión con suspensesolo hay un puzzle de combinacion
			idPuzzle = rm.nextInt((1 - 0) + 1) + 0;
			puzzle = child.getChild(idPuzzle);
			
			habitacionDestino = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal1");
			habitacionDestino2 = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionFinal2");
			objeto = puzzle.getChildByName("objeto").getAttribute("tipoObjetoFinal");
			objetoCombinacion1 = puzzle.getChildByName("objeto").getAttribute("tipoObjeto1");
			objetoCombinacion2 = puzzle.getChildByName("objeto").getAttribute("tipoObjeto2");
			personaje = puzzle.getChildByName("personaje").getAttribute("tipoPersonaje");
			postPuzzle = puzzle.getChildByName("postPuzzle").getAttribute("texto");
		}
		
		habitacionInicio = puzzle.getChildByName("habitacion").getAttribute("tipoHabitacionInicio");
		objetivo1 = puzzle.getChildByName("objetivo1").getAttribute("texto");
		objetivo2 = puzzle.getChildByName("objetivo2").getAttribute("texto");
		textoPersonaje = puzzle.getChildByName("dialogo").getAttribute("texto");
		pistaPersonaje = pista;
	}

	public String getHabitacionInicio() {
		return habitacionInicio;
	}

	public String getHabitacionDestino() {
		return habitacionDestino;
	}

	public String getHabitacionDestino2() {
		return habitacionDestino2;
	}

	public String getObjeto() {
		return objeto;
	}

	public String getObjetoCombinacion1() {
		return objetoCombinacion1;
	}

	public String getObjetoCombinacion2() {
		return objetoCombinacion2;
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

	public boolean estadoPuzzle() {
		return puzzleSuperado;
	}

	public boolean estadoMision() {
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
	
	public String getError(){
		return error;
	}
	
	public String getPrePuzzle(){
		return prePuzzle;
	}
	
	public String getPostPuzzle(){
		return postPuzzle;
	}

	public String getTextoEleccion(int i) {
		if (i == 1)
			return textoOpcion1;
		else if (i == 2)
			return textoOpcion2;
		else if (i == 3)
			return textoOpcion3;
		else
			return textoOpcion4;
	}

	public String getEleccion(int i) {
		if (i == 1)
			return opcion1;
		else if (i == 2)
			return opcion2;
		else if (i == 3)
			return opcion3;
		else
			return opcion4;
	}

	public void eleccionCorrecta(int i) {
		eleccionCorrecta = i;
	}

	public int getEleccionCorrecta() {
		return eleccionCorrecta;
	}

	public int getNumEstado() {
		return numEstado;
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
	
	public void crearPista(String sigHab){
		if(sigHab == null)
			System.out.println("algo falla o es el estado inicial");
		else if(sigHab.equals("Salon"))
			siguienteHabitacion = "Geh jetzt bitte ins Wohnzimmer und such neue Informationen!";
		else if(sigHab.equals("Dormitorio"))
			siguienteHabitacion = "Geh jetzt bitte ins Schlafzimmer und such neue Informationen!";
		else if(sigHab.equals("Desvan"))
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
