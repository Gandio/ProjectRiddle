package Objetos;

import Botones.Boton;
import Botones.BotonFinConversacion;
import Botones.BotonSiguienteConversacion;
import Pantallas.Habitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa los cuadros donde se colocan los dialogos con los personajes
 * durante la partida.
 * @author Francisco Madueño Chulian
 */

public class CuadroDialogo extends CuadroTexto{
	private BotonSiguienteConversacion siguienteConversacion;
	private BotonFinConversacion finConversacion;
	
	private Array<String> textos = new Array<String>();
	
	private int parteTexto = 0;
	private boolean finTexto = false;
	
	//Todos los personajes te dirán esto a no ser que estén implicados en el puzzle
	private String textoDefecto = "Ich habe momentan keine Information für dich!";
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	public CuadroDialogo(TheCrimeHouse game) {
		super(game);
		siguienteConversacion = new BotonSiguienteConversacion(game);
		finConversacion = new BotonFinConversacion(game);
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroTextoSuspense.png"));
		else
			cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroTexto.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
	
		siguienteConversacion.setCoordenadas(1180, 20);
		//finConversacion.setCoordenadas(1180, 30);
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			finConversacion.setCoordenadas(1130, 20);
		else
			finConversacion.setCoordenadas(1150, 20);
		
		siguienteConversacion.setTouchable(Touchable.enabled);
		finConversacion.setTouchable(Touchable.enabled);
		
		//Texto por defecto.
		texto = textoDefecto;
		
		font = new BitmapFont();
	}
	
	/**
	 * Comprueba si se han pulsado los botones de siguiente conversacion o  de fin 
	 * conversacion.
	 */
	
	public void update(){
		siguienteConversacion.esPulsado();
		finConversacion.esPulsado();
	}
	
	/**
	 * Dibuja el actor en el stage
	 */
	
	public void draw(Batch batch, float parentAlpha){
		/*usar un vector, cada celda será un string, cada 4 \n (numero de lineas) se crea una nueva celda,
		 * hasta que no se llegue a la última celda no se dibuja el boton de fin de conversacion
		 */
		batch.draw(cuadroTexto, coordenadas.x, coordenadas.y);
		font.setScale(3f);
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE) 
			font.setColor(Color.BLACK);
		
		String textoConLimites = wrapString(texto, 50);
		textos = dividirTexto(textoConLimites);
		
		if(textos.size == 1){
			siguienteConversacion.remove();
			((Habitacion) game.getScreen()).getStage().addActor(finConversacion);
		}
		//Me preparo para añadir el botón de fin conversación
		else if(parteTexto == textos.size-2){
			finTexto = true;
		}
		
		font.drawMultiLine(batch, textos.get(parteTexto), 30, 260);
	}
	
	/**
	 * Este método añade el botón de siguiente conversación al stage
	 * @param s
	 */
	public void iniciarConversacion(Stage s){
		s.addActor(siguienteConversacion);
	}
	
	/**
	 * Se termina la conversación
	 */
	public void finConversacion(){
		parteTexto = 0;
		finTexto = false;
		finConversacion.remove();
		siguienteConversacion.remove();
	}
	
	/**
	 * Devuelve el botón de fin conversación
	 * @return finConversacion
	 */
	
	public Boton getFinConv(){
		return finConversacion;
	}
	
	/**
	 * Devuelve el botón de siguiente conversación
	 * @return
	 */
	public Boton getSigConv(){
		return siguienteConversacion;
	}
	
	/**
	 * Modifica el texto que se muestra en el cuadro
	 */
	public void setTexto(String t){
		texto = t;
	}
	
	/**
	 * Devuelve la posicion del vector donde se almacena el texto
	 * @return parteTexto
	 */
	public int getParteTexto(){
		return parteTexto;
	}
	
	/**
	 * Accede a la siguiente parte del texto
	 */
	public void sigParteTexto(){
		parteTexto++;;
	}
	
	/**
	 * Comprueba si se ha acabado el texto
	 * @return
	 */
	
	public boolean getFinTexto(){
		return finTexto;
	}
	
	/**
	 * Devuelve el texto por defecto del personaje
	 * @return textoDefecto
	 */
	
	public String getTextoDefecto(){
		return textoDefecto;
	}
}