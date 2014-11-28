package Controladores;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Objetos.Cursor;
import Pantallas.Pasillo;

public class ColisionCursor {
	private Pasillo pasillo;
	private Array<Rectangle> puertas;
	private Array<Rectangle> paredes;
	private float[] colisionaParedesAbajo;
	private float[] colisionaParedesArriba;
	private float[] colisionaParedesDerecha;
	private float[] colisionaParedesIzquierda;
	Iterator<Rectangle> iRect;
	private Cursor cursor;
	private boolean noAbajo, noArriba, noIzquierda, noDerecha, colisionPuerta;
	
	public ColisionCursor(Pasillo p){
		pasillo = p;
		paredes = pasillo.getParedes();
		cursor = pasillo.getCursor();
		noAbajo = noArriba = noIzquierda = noDerecha = false;
		
		colisionaParedesAbajo = new float[paredes.size];
		colisionaParedesArriba = new float[paredes.size];
		colisionaParedesDerecha = new float[paredes.size];
		colisionaParedesIzquierda = new float[paredes.size];
		
		iRect = paredes.iterator();
		
		/*for(int i = 0; i < paredes.size; ++i){
			rectanguloAux = iRect.next(); 
			colisionaParedesAbajo[i] = rectanguloAux.getY();
			colisionaParedesIzquierda[i] = rectanguloAux.getX();
			colisionaParedesArriba[i] = (rectanguloAux.getY() + rectanguloAux.getHeight());
			colisionaParedesDerecha[i] = (rectanguloAux.getX() + rectanguloAux.getWidth());
		}*/
	}
	
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
	
	public boolean colisionaPuerta(){return colisionPuerta;}
}