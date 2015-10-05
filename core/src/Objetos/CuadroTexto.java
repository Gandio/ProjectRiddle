package Objetos;

import Pantallas.Inicio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Esta clase representa los cuadros donde aparecerá el texto durante el juego
 * @author Francisco Madueño Chulián
 *
 */

public class CuadroTexto extends Actor{
	protected TheHouseOfCrimes game;
	
	protected Texture cuadroTexto;
	protected Vector2 coordenadas;
	protected BitmapFont font;
	protected String texto;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	public CuadroTexto(TheHouseOfCrimes game){
		this.game = Inicio.game;
		
	}
	
	/**
	 * Modifica las coordenadas del actor
	 * @param x
	 * @param y
	 */
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	/**
	 * Este algoritmo ajusta el texto al cuadro de texto añadiendo saltos de lineas
	 * @param string
	 * @param charWrap
	 * @return
	 */
	public static String wrapString(String string, int charWrap) {
	    int lastBreak = 0;
	    int nextBreak = charWrap; // número de caracteres permitidos por cada linea
	    if (string.length() > charWrap) {
	    	//se empieza con la partición
	        String setString = "";
	        do {
	            while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
	            	/*mientras no se encuentre con un espacio, para no cortar palabras
	            	 * y no se haya superado el numero de caracteres permitidos
	            	 */
	                nextBreak--;
	            }
	            
	            if (nextBreak == lastBreak) {
	                nextBreak = lastBreak + charWrap;
	            }
	            //se divide la cadena y se actualizan los valores
	            setString += string.substring(lastBreak, nextBreak).trim() + "\n";
	            lastBreak = nextBreak;
	            nextBreak += charWrap;

	        } while (nextBreak < string.length());
	        setString += string.substring(lastBreak).trim();
	        return setString;
	    } else {
	        return string;
	    }
	}
	
	/**
	 * Este algoritmo divide el texto completo en fragmentos y los mete en un array.
	 * Cada 4 saltos de linea fragmenta el texto.
	 * @param texto
	 * @return
	 */
	public Array<String> dividirTexto(String texto){
		Array<String> textos = new Array<String>();
		int contador = 0; // cuenta los /n que nos encontramos
		int liminf = 0;
		String aux;
		for(int i = 0; i < texto.length(); ++i){
			if(texto.charAt(i) == '\n') contador++;
			
			if(contador == 4){
				textos.add(texto.substring(liminf, i));
				contador = 0; 
				liminf = i+1;
				aux = "";
			}
		}
		
		aux = (String) texto.toString().subSequence(liminf, texto.length());
		textos.add(aux);
		
		return textos;
	}
	
	/**
	 * Modifica el texto
	 * @param t
	 */
	
	public void setTexto(String t){
		texto = t;
	}
}