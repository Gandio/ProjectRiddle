package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Esta clase representa al estado en el que el puzzle consiste en responder a una pregunta
 * de respuesta múltiple
 * @author Francisco Madueño Chulián
 */

public class EstadoDecision extends Estado{
	
	private String textoOpcion1;
	private String textoOpcion2;
	private String textoOpcion3;
	private String textoOpcion4;

	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	
	private int eleccionCorrecta = -1; // tri estado -1 no lo has intentado todavia, 0 has fallado, 1 correcto
	
	protected String error;
	
	/**
	 * Constructor de la clase
	 * @param numEstado
	 * @param pista
	 */

	public EstadoDecision(int numEstado, String pista) {
		super(numEstado, pista);
		
		textoOpcion1 = puzzle.getChildByName("opcion1").getAttribute("texto");
		textoOpcion2 = puzzle.getChildByName("opcion2").getAttribute("texto");
		textoOpcion3 = puzzle.getChildByName("opcion3").getAttribute("texto");
		textoOpcion4 = puzzle.getChildByName("opcion4").getAttribute("texto");

		opcion1 = puzzle.getChildByName("opcion1").getAttribute("correcto");
		opcion2 = puzzle.getChildByName("opcion2").getAttribute("correcto");
		opcion3 = puzzle.getChildByName("opcion3").getAttribute("correcto");
		opcion4 = puzzle.getChildByName("opcion4").getAttribute("correcto");
		
		objeto = puzzle.getChildByName("objeto").getAttribute("texto");
		error = puzzle.getChildByName("fallo").getAttribute("texto");
		prePuzzle = puzzle.getChildByName("prePuzzle").getAttribute("texto");
	}
	
	/**
	 * Este método devuelve el texto de cada respuesta
	 * @param i
	 * @return
	 */
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

	/**
	 * Este método devuelve si o no, dependiendo del número de respuesta que se le pase. Si
	 * devuelve si, la respuesta es correcta, si no devuelve no.
	 * @param i
	 * @return
	 */
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
	
	/**
	 * Selecciona la respuesta correcta
	 * @param i
	 */
	public void eleccionCorrecta(int i) {
		eleccionCorrecta = i;
	}
	
	/**
	 * Devuelve la respuesta correcta
	 * @return eleccionCorrecta
	 */
	public int getEleccionCorrecta() {
		return eleccionCorrecta;
	}
	
	/**
	 * Devuelve el mensaje de error que te lanza el personaje al fallar una respuesta.
	 * @return error
	 */
	public String getError(){
		return error;
	}
}