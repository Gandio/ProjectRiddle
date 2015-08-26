package Botones;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

import Objetos.Cursor;
import Pantallas.Pasillo;

/**
 * Esta clase representa el botón que hará que el personaje se mueva hacia la derecha.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonDerecha extends Boton{
	private boolean noDerecha = false;
	
	/**
	 * Constructor de la clase botonDerecha
	 * @param game
	 */
	
	public BotonDerecha(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/botonDerecha.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Se comprueba si el personaje está colisionando con alguna de las paredes, devuelve
	 * true o false.
	 * @return
	 */
	
	private boolean colisionaDerecha(){
		Array<Rectangle> paredes = ((Pasillo) game.getScreen()).getParedes();
		Cursor cursor = Pasillo.getCursor();
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float nuevaCoordenada;
		Rectangle rectanguloAux;
		
		/*Algoritmo de colisiones. Comprobamos todos los bordes izquierdos de las paredes,
		 * si chocamos mientras nos movemos hacia derecha chocamos con el borde izquierdo de las 
		 * paredes. Si en algún momento se solapan los rectangulos de la pared y el cursor se 
		 * activa la variable noDerecha que nos impide seguir con este movimiento. Debemos preveer
		 * el choque, si no el cursor se quedará atrapado, solo podremos movernos de arriba
		 * a abajo mientras nos estemos cochando, por eso comprobamos tambien si se va 
		 * a chocar
		*/
		
		while(i < paredes.size && !noDerecha){
			rectanguloAux = iRect.next();
			nuevaCoordenada = rectanguloAux.getX();
			if(cursor.getLimites().overlaps(rectanguloAux) && (cursor.getX() + cursor.getWidth() == nuevaCoordenada + 1)){
				noDerecha = true;
			}
			
			++i;
		}
		
		return noDerecha;
	}
	
	/**
	 * Este método comprueba si el botón ha sido pulsado, en cuyo caso, y si no colisiona
	 * con ninguna pared hace que el personaje se mueva hacia  la derecha.
	 * @param delta
	 */
	
	public void esPulsado(float delta){
		Cursor cursor = Pasillo.getCursor();
		
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		//sin esto no se puede manter pulsado el botón para moverse
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonDerecha)event.getTarget()).pulsado = true;
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonDerecha)event.getTarget()).pulsado = false;
            }
		});
		
		//Nos movemos
		if(pulsado && !colisionaDerecha()){
			cursor.moverDerecha(delta);
		}else{
			//Dejamos de movernos
			cursor.setVelocityX(0);
		}
	    
	    noDerecha = false;
	}
}