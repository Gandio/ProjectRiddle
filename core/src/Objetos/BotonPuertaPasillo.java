package Objetos;

import java.util.Iterator;

import Objetos.Cursor.Posicion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	private Cursor cursor;
	private Texture botonActivado, botonDesactivado;
	private Array<Rectangle> puertas;
	
	public BotonPuertaPasillo(MyGdxGame game, Cursor cursor, Array<Rectangle> puertas) {
		super(game);
		
		this.cursor = cursor;
		this.puertas = puertas;
		
		botonActivado = new Texture(Gdx.files.internal("Imagenes/botonPuerta.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/botonPuertaDesactivado.png"));
		boton = botonDesactivado;
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public boolean colisionaPuerta(){
		int i = 0;
		Iterator<Rectangle> iRect = puertas.iterator();
		Rectangle rectanguloAux;
		
		while(i < puertas.size){
			rectanguloAux = iRect.next();
			if(cursor.getLimites().overlaps(rectanguloAux)){
				return true;
				//numHabitacion = i;
			}
			
			++i;
		}
		
		return false;
	}
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonArriba)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(colisionaPuerta()){
			boton = botonActivado;
			if(pulsado){
				if(cursor.getPosicion() == Posicion.ARRIBA) cursor.MirarAbajo();
				else if(cursor.getPosicion() == Posicion.ABAJO) cursor.MirarArriba();
				else if(cursor.getPosicion() == Posicion.DERECHA) cursor.MirarIzquierda();
				else cursor.MirarDerecha();
				
				//entramos en una habitación
				
				pulsado = false;
			}
			
		}else{
			boton = botonDesactivado;
		}
	}
}