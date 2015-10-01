package Botones;

import java.util.Iterator;

import Objetos.Cursor;
import Pantallas.Pasillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que hará que el personaje se mueva hacia la izquierda.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonIzquierda extends Boton{
	private boolean noIzquierda = false;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	
	public BotonIzquierda(TheCrimeHouse game) {
		super(game);
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_IZQUIERDA));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Se comprueba si el personaje está colisionando con alguna de las paredes, devuelve
	 * true o false.
	 * @return
	 */
	
	private boolean colisionaIzquierda(){
		Array<Rectangle> paredes = ((Pasillo) game.getScreen()).getParedes();
		Cursor cursor = Pasillo.getCursor();
		
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float nuevaCoordenada;
		Rectangle rectanguloAux;
		
		/*Algoritmo de colisiones. Comprobamos todos los bordes derechos de las paredes,
		 * si chocamos mientras nos movemos hacia izquierda chocamos con el borde derecho de las 
		 * paredes. Si en algún momento se solapan los rectangulos de la pared y el cursor se 
		 * activa la variable noIzquierda que nos impide seguir con este movimiento. Debemos preveer
		 * el choque, si no el cursor se quedará atrapado, solo podremos movernos de arriba
		 * a abajo mientras nos estemos cochando, por eso comprobamos tambien si se va 
		 * a chocar
		*/
		
		while(i < paredes.size && !noIzquierda){
			rectanguloAux = iRect.next();
			nuevaCoordenada = rectanguloAux.getX() + rectanguloAux.getWidth();
			if(cursor.getLimites().overlaps(rectanguloAux) && cursor.getX() >= nuevaCoordenada - 2){
				noIzquierda = true;
			}
			
			++i;
		}
		
		return noIzquierda;
	}
	
	/**
	 * Este método comprueba si el botón ha sido pulsado, en cuyo caso, y si no colisiona
	 * con ninguna pared hace que el personaje se mueva hacia la izquierda.
	 * @param delta
	 */
	
	public void esPulsado(float delta){
		Cursor cursor = Pasillo.getCursor();
		
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonIzquierda)event.getTarget()).pulsado = true;
                return true;
            }
            //sin esto no se puede manter pulsado el botón para moverse
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonIzquierda)event.getTarget()).pulsado = false;
            }
		});
		
		//Nos empezamos a mover
		if(pulsado && !colisionaIzquierda()){
			cursor.moverIzquierda(delta);
			
		}else{
			
			//Nos paramos
			cursor.setVelocityX(0);
		}
        
        noIzquierda = false;
	}
}