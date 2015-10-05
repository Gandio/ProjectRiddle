package Objetos;

import Puzzle.Inventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.TheHouseOfCrimes;

/**
 * Esta clase representa al protagonista mientras se mueve por los pasillos de la casa.
 * @author Francisco Madueño Chulián
 */
public class Cursor extends Actor{
	private Texture cursor;
	private TextureRegion[] arriba, abajo, derecha, izquierda;
	private TextureRegion[][] tmp;
	private Animation moverArriba, moverAbajo, moverDerecha, moverIzquierda;
	private TextureRegion frameActual;
	public enum Posicion{ARRIBA, ABAJO, DERECHA, IZQUIERDA};
	private Posicion posicionActual;
	private float stateTime;
	private Vector2 velocity;
	private Rectangle limites;
	private float velocidad = 2;
	public static Inventario inventario = Inventario.getInstancia(); //creamos el inventario
	
	/**
	 * Recibe un objeto MyGdxGame que hereda de la clase Game
	 * @param game
	 */
	public Cursor(TheHouseOfCrimes game) {
		//Creamos el grafico del cursor
		cursor = new Texture(Gdx.files.internal("Imagenes/nuevoCursor.png"));
		tmp = TextureRegion.split(cursor, cursor.getWidth() / 3, cursor.getHeight() / 4);
		arriba = new TextureRegion[4];
		arriba[0] = tmp[0][1];  
		arriba[1] = tmp[0][0];
		arriba[2] = tmp[0][1]; 
		arriba[3] = tmp[0][2];
		
		abajo = new TextureRegion[4];
		abajo[0] = tmp[2][1];
		abajo[1] = tmp[2][0];
		abajo[2] = tmp[2][1];
		abajo[3] = tmp[2][2];
		
		derecha = new TextureRegion[4];
		derecha[0] = tmp[1][1];
		derecha[1] = tmp[1][0];
		derecha[2] = tmp[1][1];
		derecha[3] = tmp[1][2];
		
		izquierda = new TextureRegion[4];
		izquierda[0] = tmp[3][1];
		izquierda[1] = tmp[3][0];
		izquierda[2] = tmp[3][1];
		izquierda[3] = tmp[3][2];
		
		//Al principio del juego el personaje mirará hacia arriba y se situará en la entrada
		frameActual = new TextureRegion(arriba[0]);
		
		stateTime = 0f;
		setPosition(830, 170);
		setWidth(cursor.getWidth() / 3);
		setHeight((cursor.getHeight() / 4) - 40);
		
		//Creamos la animación
		moverArriba = new Animation(0.3f, arriba);
		moverAbajo = new Animation(0.3f, abajo);
		moverIzquierda = new Animation(0.3f, izquierda);
		moverDerecha = new Animation(0.3f, derecha);
		
		velocity = new Vector2(0, 0);
		
		//Creamos un rectangulo que envuelva al personaje, nos ayudará con las colisiones
		limites = new Rectangle();
		limites.setSize(cursor.getWidth() / 3, (cursor.getHeight() / 4) - 40);
	}
	
	/**
	 * Este método dibujará al actor
	 */
	public void draw(Batch batch, float parentAlpha){  
		batch.draw(frameActual, getX(), getY());
	}
	
	/**
	 * Usamos este método para modificar el stateTime del cursor
	 * @param time
	 */
	public void setStateTime(float time){
		stateTime += time;
	}
	
	/**
	 * Este método devuelve el stateTime del cursor
	 * @return stateTime
	 */
	public float getStateTime(){
		return stateTime;
	}
	
	/**
	 * Este método devuelve la dirección en la que se mueve el cursor
	 * @return velocity
	 */
	public Vector2 getVelocity(){
		return velocity;
	}
	
	/**
	 * Modifica la dirección X del cursor
	 * @param x
	 */
	public void setVelocityX(float x){
		velocity.x = x;
	}
	
	/**
	 * Modifica la dirección Y del cursor
	 * @param y
	 */
	public void setVelocityY(float y){
		velocity.y = y;
	}
	
	/**
	 * Cambia el frame del cursor, hace que mire hacia arriba
	 */
	public void MirarArriba(){
		frameActual = moverArriba.getKeyFrame(stateTime,true);
		posicionActual = Posicion.ARRIBA;
	}
	
	/**
	 * Cambia el frame del cursor, hace que mire hacia abajo
	 */
	public void MirarAbajo(){
		frameActual = moverAbajo.getKeyFrame(stateTime,true);
		posicionActual = Posicion.ABAJO;
	}
	
	/**
	 * Cambia el frame del cursor, hace que mire hacia la derecha
	 */
	public void MirarDerecha(){
		frameActual = moverDerecha.getKeyFrame(stateTime,true);
		posicionActual = Posicion.DERECHA;
	}
	
	/**
	 * Cambia el frame del cursor, hace que mire hacia la izquierda
	 */
	public void MirarIzquierda(){
		frameActual = moverIzquierda.getKeyFrame(stateTime,true);
		posicionActual = Posicion.IZQUIERDA;
	}
	
	/**
	 * Con este método nos movemos a la izquierda
	 * @param delta
	 */
	public void moverIzquierda(float delta){
		velocity.x = -2;
		velocity.y = 0;
		
		MirarIzquierda();
		
		setX(getX() + velocity.x);
		setY(getY() + velocity.y);
      
        //actualizamos nuestro stateTime y dibujamos el currentFrame.
        setStateTime(delta);
        
        //Actualizamos la posición de los límites
        getLimites().setPosition(getX(), getY());
	}
	
	/**
	 * Con este método nos movemos a la derecha
	 * @param delta
	 */
	public void moverDerecha(float delta){
		velocity.x = 2;
		velocity.y = 0;
		
		MirarDerecha();
		
		setX(getX() + velocity.x);
		setY(getY() + velocity.y);
	  
	    //actualizamos nuestro stateTime y dibujamos el currentFrame.
	    setStateTime(delta);
	    
	    //Actualizamos la posición de los límites
	    getLimites().setPosition(getX(), getY());
	}
	
	/**
	 * Con este método nos movemos hacia arriba
	 * @param delta
	 */
	public void moverArriba(float delta){
		velocity.x = 0;
		velocity.y = 2;
		
		MirarArriba();
		
		setX(getX() + velocity.x);
		setY(getY() + velocity.y);
      
        //actualizamos nuestro stateTime y dibujamos el currentFrame.
        setStateTime(delta);
        
        //Actualizamos la posición de los límites
        getLimites().setPosition(getX(), getY());
	}
	
	/**
	 * Con este método nos movemos hacia abajo
	 * @param delta
	 */
	public void moverAbajo(float delta){
		velocity.y = -2;
		velocity.x = 0;
		
		MirarAbajo();
		
		setX(getX() + velocity.x);
		setY(getY() + velocity.y);
      
        //actualizamos nuestro stateTime y dibujamos el currentFrame.
        setStateTime(delta);
        
        //Actualizamos la posición de los límites
        getLimites().setPosition(getX(), getY());
	}
	
	/**
	 * Devuelve el rectangulo que envuelve al cursor
	 * @return limites
	 */
	public Rectangle getLimites(){
		return limites;
	}
	
	/**
	 * Devuelve la posición actual del cursor
	 * @return posicionActual
	 */
	public Posicion getPosicion(){
		return posicionActual;
	}
	
	/**
	 * Devuelve el inventario asociado al cursor
	 * @return inventario
	 */
	public Inventario getInventario(){
		return inventario;
	}
	
	/**
	 * Devuelve a la velocidad a la que se mueve el cursor
	 * @return velocidad
	 */
	public float getVelocidad(){
		return velocidad;
	}
}