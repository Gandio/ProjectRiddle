package Objetos;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Esta clase representa la puntuación que el jugador obtiene a lo largo  de la partida. 
 * Al final de cada estado se obtienen 1000 puntos, con cada fallo que se cometa se 
 * restan 100
 * @author Francisco Madueño Chulian
 *
 */

public final class Puntuacion extends Actor{
	private static TheHouseOfCrimes game;
	private static Puntuacion unicaInstancia;
	private Vector2 coordenadas;
	
	private static int puntos;
	private static int numFallos;
	
	protected BitmapFont font;
	protected static String texto;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	private Puntuacion(TheHouseOfCrimes game){
		Puntuacion.game = game;
		puntos = 0;
		numFallos = 0;
		texto = "Punkte: " + puntos + "/7000";
		font = new BitmapFont();
		font.scale(1.5f);
		coordenadas = new Vector2();
	}
	
	/**
	 * Dibuja el actor puntuacion en el stage
	 */
	
	public void draw(Batch batch, float parentAlpha){
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, texto, coordenadas.x, coordenadas.y); 
	}
	
	/**
	 * Cambia la puntuación del jugador
	 * @param p
	 */
	
	public static void setPuntuacion(int p){
		puntos += p;
		
		if(puntos < 0) puntos = 0; //con esto impedimos puntuación negativa
		
		texto = "Punkte: " + puntos + "/7000";
	}
	
	/**
	 * Suma 1 al contador de errores del jugador
	 */
	
	public static void sumarError(){
		numFallos += 1;
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
	 * Devuelve los puntos del jugador
	 * @return puntos
	 */
	
	public static int getPuntos(){
		return puntos;
	}
	
	/**
	 * Devuelve el número de fallos del jugador
	 * @return numFallos
	 */
	
	public static int getError(){
		return numFallos;
	}
	
	/**
	 * Si ya existe una instancia de la clase la devuelve si no la crea. Con esto nos
	 * aseguramos de que solo exista un objeto puntuacion
	 * @return unicaInstancia
	 */
	
	public static Puntuacion getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Puntuacion(game);
		}
		
		return unicaInstancia;
	}
}