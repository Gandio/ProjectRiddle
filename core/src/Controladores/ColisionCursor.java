package Controladores;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Objetos.Cursor;
import Pantallas.Pasillo;

/**
 * Esta clase controla toda la lógica del cursor
 * @author Francisco Madueño Chulián
 *
 */
public class ColisionCursor {
	private Pasillo pasillo;
	private Array<Rectangle> paredes;
	private Iterator<Rectangle> iRect;
	private Cursor cursor;
	private boolean noAbajo, noArriba, noIzquierda, noDerecha;
	
	/**
	 * Recibe el objeto Pasillo que se haya instanciado cuando se crea el juego
	 * @param p
	 */
	public ColisionCursor(Pasillo p){
		pasillo = p;
		paredes = pasillo.getParedes();
		cursor = pasillo.getCursor();
		noAbajo = noArriba = noIzquierda = noDerecha = false;
	}
	
	/**
	 * Este método controla el movimiento del cursor y las colisiones con los obstaculos del 
	 * pasillo
	 * @param delta
	 */
	public void update(float delta){
		//Me muevo
		if(Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			
			if(colisionaArriba()){
				cursor.setVelocityY(0);
			}else{
				cursor.setVelocityY(1);
				cursor.setVelocityX(0);
			}
			
			cursor.MirarArriba();
			
			
		}else if(!Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			
			if(colisionaAbajo()){
				cursor.setVelocityY(0);
			}else{
				cursor.setVelocityY(-1);
				cursor.setVelocityX(0);
			}
			
			cursor.MirarAbajo();
			
		}else if(!Gdx.input.isKeyJustPressed(Keys.W) && Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			
			if(colisionaIzquierda()){
				cursor.setVelocityX(0);
			}else{
				cursor.setVelocityX(-1);
				cursor.setVelocityY(0);
			}
			
			cursor.MirarIzquierda();
			
		}else if(!Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && Gdx.input.isKeyJustPressed(Keys.D)){
			
			if(colisionaDerecha()){
				cursor.setVelocityX(0);
			}else{
				cursor.setVelocityX(1);
				cursor.setVelocityY(0);
			}
			
			cursor.MirarDerecha();
		
		}else{
			cursor.setVelocityX(0);
			cursor.setVelocityY(0);
		}

		cursor.setX(cursor.getX() + cursor.getVelocity().x);
		cursor.setY(cursor.getY() + cursor.getVelocity().y);
      
        //actualizamos nuestro stateTime y dibujamos el currentFrame.
        cursor.setStateTime(delta);
        
        //Actualizamos la posición de los límites
        cursor.getLimites().setPosition(cursor.getX(), cursor.getY());
        
        noAbajo = noArriba = noIzquierda = noDerecha = false;

	}
	
	/**
	 * Este método comprueba si el jugador puede moverse hacia abajo
	 * @return noAbajo
	 */
	public boolean colisionaAbajo(){
		int i = 0;
		iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noAbajo){
			rectanguloAux = iRect.next(); 
			aux = (rectanguloAux.getY() + rectanguloAux.getHeight());
			if(cursor.getLimites().overlaps(rectanguloAux) && (cursor.getY() == (aux - 1))){
				noAbajo = true;
			}
			++i;
		}
		
		return noAbajo;
	}
	
	/**
	 * Este método comprueba si el jugador puede moverse hacia arriba
	 * @return noArriba
	 */
	public boolean colisionaArriba(){
		int i = 0;
		iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noAbajo){
			rectanguloAux = iRect.next(); 
			aux = rectanguloAux.getY();
			if(cursor.getLimites().overlaps(rectanguloAux) &&  
					(cursor.getY() + cursor.getHeight()) == aux + 1){
				noArriba = true;
			}
			++i;
		}
		
		return noArriba;
	}
	
	/**
	 * Este método comprueba si el jugador puede moverse hacia la derecha
	 * @return noDerecha
	 */
	public boolean colisionaDerecha(){
		int i = 0;
		iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noDerecha){
			rectanguloAux = iRect.next();
			aux = rectanguloAux.getX();
			if(cursor.getLimites().overlaps(rectanguloAux) && 
					(cursor.getX() + cursor.getWidth() == aux + 1)){
				noDerecha = true;
			}
			
			++i;
		}
		
		return noDerecha;
	}
	
	/**
	 * Este método comprueba si el jugador puede moverse hacia la izquierda
	 * @return noIzquierda
	 */
	public boolean colisionaIzquierda(){
		int i  = 0;
		iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noIzquierda){
			rectanguloAux = iRect.next();
			aux = rectanguloAux.getX() + rectanguloAux.getWidth();
			if(cursor.getLimites().overlaps(rectanguloAux) && cursor.getX() == aux - 1){
				noIzquierda = true;
			}
			
			++i;
		}
		
		return noIzquierda;
	}
}