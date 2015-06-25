package Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase representa los cuadros donde aparecerá el texto durante el juego
 * @author Francisco Madueño Chulián
 *
 */

public class CuadroTexto extends Actor{
	protected MyGdxGame game;
	
	protected Texture cuadroTexto;
	protected Vector2 coordenadas;
	protected BitmapFont font;
	protected String texto;
	
	//Un archivo con las conversaciones del personaje que se encuentre en la habitacion
	
	public CuadroTexto(MyGdxGame game){
		this.game = game;
		
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	//Este algoritmo ajusta el texto al cuadro de texto forzando los saltos de líneas
	public static String wrapString(String string, int charWrap) {
	    int lastBreak = 0;
	    int nextBreak = charWrap;
	    if (string.length() > charWrap) {
	        String setString = "";
	        do {
	            while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
	                nextBreak--;
	            }
	            if (nextBreak == lastBreak) {
	                nextBreak = lastBreak + charWrap;
	            }
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
	
	public void setTexto(String t){
		texto = t;
	}
}