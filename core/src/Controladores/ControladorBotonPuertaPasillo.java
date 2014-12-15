package Controladores;

import java.util.Iterator;

import Objetos.BotonPuerta;
import Objetos.Cursor;
import Objetos.Cursor.Posicion;
import Pantallas.Biblioteca;
import Pantallas.Habitacion;
import Pantallas.HabitacionDeMuestra;
import Pantallas.Pasillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase se encarga de la lógica del botón puerta, hace que se active si pasamos cerca de
 * una puerta y que se desactive cuando nos alejamos. Además controla en la habitación en la 
 * que se entra.
 * @author Francisco Madueño Chulián
 */

public class ControladorBotonPuertaPasillo extends ControladorBotonPuerta{
	private Cursor cursor;
	private Array<Rectangle> puertas; //contiene los rectangulos que envuelven a las puertas
	private Iterator<Rectangle> iRect;
	private boolean colisionPuerta;
	private Habitacion habitacion;
	private int numHabitacion;
	private Pasillo pasillo;
	
	public ControladorBotonPuertaPasillo(Pasillo pasillo, MyGdxGame game) {
		super(pasillo, game);
		this.pasillo = pasillo;
		cursor = pasillo.getCursor();
		boton = (BotonPuerta) pasillo.getBotonPuerta();
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
				
				if(cursor.getPosicion() == Posicion.ARRIBA) cursor.MirarAbajo();
				else if(cursor.getPosicion() == Posicion.ABAJO) cursor.MirarArriba();
				else if(cursor.getPosicion() == Posicion.DERECHA) cursor.MirarIzquierda();
				else cursor.MirarDerecha();
				
				crearHabitacion(numHabitacion);
				game.setScreen(habitacion);
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
				numHabitacion = i;
			}
			++i;
		}
		
		return colisionPuerta;
	}
	
	/**Actualiza la habitación en la que el jugador ha entrado
	 * @param i
	 * @return
	 */
	
	//Para saber que puerta corresponde con cada habitación hay que leer los comentarios
	//de la clase pasillo.
	public void crearHabitacion(int i){
		/* Esto se queda pendiente hasta que se resuelva el problema en la habitación de 
		 * muestra
		switch(i){
			case 0: habitacion = new Biblioteca(game); break;
			default: habitacion = null;
		}*/
		
		habitacion = new HabitacionDeMuestra(game);
	}
}