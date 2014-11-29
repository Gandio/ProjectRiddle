package Controladores;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Objetos.BotonPuerta;
import Objetos.Cursor;
import Pantallas.Pasillo;

/**
 * Esta clase se encarga de la lógica del botón puerta, hace que se active si pasamos cerca de
 * una puerta y que se desactive cuando nos alejamos. Además controla en la habitación en la 
 * que se entra.
 * @author Francisco Madueño Chulián
 *
 */
public class ControladorBotonPuerta {
	private Pasillo pasillo;
	private BotonPuerta boton;
	private Cursor cursor;
	private Array<Rectangle> puertas; //contiene los rectangulos que envuelven a las puertas
	private Iterator<Rectangle> iRect;
	private boolean colisionPuerta;
	
	public ControladorBotonPuerta(Pasillo p) {
		pasillo = p;
		cursor = pasillo.getCursor();
		boton = pasillo.getBotonPuerta();
		puertas = pasillo.getPuertas();
	}
	
	/**
	 * Este método comprueba si el jugador está cerca de una puerta, si lo está, se le da la
	 * posibilidad de pulsar la tecla P, el propio botón si se está ejecutando en android, para
	 * entrar en la habitación
	 */
	public void update(){
		if(colisionaPuerta()){
			boton.activar();
			if(Gdx.input.isKeyJustPressed(Keys.P)){
				System.out.println("has pulsado la p");
			}
		}else{
			boton.desactivar();
		}
		
		colisionPuerta = false;
	}
	/**
	 * Comprueba si el personaje está cerca de una puerta
	 * @return colisionPuerta
	 */
	public boolean colisionaPuerta(){
		int i = 0;
		iRect = puertas.iterator();
		Rectangle rectanguloAux;
		
		while(i < puertas.size && !colisionPuerta){
			rectanguloAux = iRect.next();
			if(cursor.getLimites().overlaps(rectanguloAux)){
				colisionPuerta = true;
			}
			
			++i;
		}
		
		return colisionPuerta;
	}
}
