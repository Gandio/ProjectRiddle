package Botones;

import java.util.Iterator;

import Objetos.Cursor;
import Objetos.Cursor.Posicion;
import Pantallas.Biblioteca;
import Pantallas.Dormitorio;
import Pantallas.Pasillo;
import Pantallas.Salon;
import Pantallas.Sotano;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase hereda de Boton, representa el botón que nos permite entrar en las habitaciones
 * si estamos cerca de una de las puertas.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonPuertaPasillo extends Boton{
	private Texture botonActivado, botonDesactivado;
	private Sound sonido;
	private int numPuerta = -1;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	public BotonPuertaPasillo(MyGdxGame game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonPuerta.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonPuertaDesactivado.png"));
		boton = botonDesactivado;
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/botonPuerta.wav"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Comprueba si el personaje está cerca de una puerta, en cuyo caso se activa la 
	 * funcionalidad del botón.
	 * @return
	 */
	
	private boolean colisionaPuerta(){
		Array<Rectangle> puertas = ((Pasillo) game.getScreen()).getPuertas();
		Cursor cursor = ((Pasillo) game.getScreen()).getCursor();
		int i = 0;
		Iterator<Rectangle> iRect = puertas.iterator();
		Rectangle rectanguloAux;
		
		while(i < puertas.size){
			rectanguloAux = iRect.next();
			if(cursor.getLimites().overlaps(rectanguloAux)){
				numPuerta = i;
				return true;
			}
			
			++i;
		}
		
		return false;
	}
	
	/**
	 * Comprueba si hemos pulsado el botón y si estamos cerca de una puerta, si 
	 * ambas condiciones se cumplen el jugador entrará en una habitación.
	 */
	
	public void update(){
		Cursor cursor = ((Pasillo) game.getScreen()).getCursor();
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonPuertaPasillo)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(colisionaPuerta()){
			boton = botonActivado;
			if(pulsado){
				sonido.play();
				if(cursor.getPosicion() == Posicion.ARRIBA) cursor.MirarAbajo();
				else if(cursor.getPosicion() == Posicion.ABAJO) cursor.MirarArriba();
				else if(cursor.getPosicion() == Posicion.DERECHA) cursor.MirarIzquierda();
				else cursor.MirarDerecha();
				
				pulsado = false;
				((Pasillo) game.getScreen()).pararMusica();
				
				Cursor c = ((Pasillo) game.getScreen()).getCursor();
				
				if(numPuerta == 0){ //es el salon
					game.setScreen(Salon.getInstancia());
				}else if(numPuerta == 1){ //es el dormitorio
					game.setScreen(Dormitorio.getInstancia());
				}else if(numPuerta == 2){
					System.out.println("no se entra");
				}else if(numPuerta == 3){
					game.setScreen(Biblioteca.getInstancia());
				}else if(numPuerta == 4){
					game.setScreen(Sotano.getInstancia());
				}
			}
			
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}