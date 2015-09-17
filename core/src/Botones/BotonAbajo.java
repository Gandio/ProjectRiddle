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
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que hará que el personaje se mueva hacia abajo.
 * @author Francisco Madueño Chulián
 */

public class BotonAbajo extends Boton{
	
	private boolean noAbajo = false;
	
	/** 
	 * Constructor de la clase
	 * @param game
	 */
	public BotonAbajo(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/botonAbajo.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Se comprueba si el personaje está colisionando con alguna de las paredes, devuelve
	 * true o false.
	 * @return
	 */
	private boolean colisionaAbajo(){
		Array<Rectangle> paredes = ((Pasillo) game.getScreen()).getParedes();
		Cursor cursor = Pasillo.getCursor();
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float nuevaCoordenada;
		Rectangle rectanguloAux;
		
		/*Algoritmo de colisiones. Comprobamos todos los bordes superiores de las paredes,
		 * si chocamos mientras nos movemos hacia abajo chocamos con el borde superior de las 
		 * paredes. Si en algún momento se solapan los rectangulos de la pared y el cursor se 
		 * activa la variable noAbajo que nos impide seguir con este movimiento. Debemos preveer
		 * el choque, si no el cursor se quedará atrapado, solo podremos movernos de izquierda
		 * a derecha mientras nos estemos cochando, por eso comprobamos tambien si se va 
		 * a chocar
		*/
		while(i < paredes.size && !noAbajo){
			rectanguloAux = iRect.next(); 
			nuevaCoordenada = (rectanguloAux.getY() + rectanguloAux.getHeight());
			if(cursor.getLimites().overlaps(rectanguloAux) && (cursor.getY() >= (nuevaCoordenada - 2))){
				noAbajo = true;
			}
			
			++i;
		}
		
		return noAbajo;
	}
	
	/**
	 * Este método comprueba si el botón ha sido pulsado, en cuyo caso, y si no colisiona
	 * con ninguna pared hace que el personaje se mueva hacia abajo.
	 * @param delta
	 */
	public void esPulsado(float delta){
		Cursor cursor = Pasillo.getCursor();
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonAbajo)event.getTarget()).pulsado = true;
                return true;
            }
            
            //sin esto no se puede manter pulsado el botón para moverse
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonAbajo)event.getTarget()).pulsado = false;
            }
		});
		
		/*Me empiezo a mover. Mientras no colosione y el botón esté pulsado nos movemos hacia
		 * abajo reduciendo la coordenada "y" del cursor y manteniendo la "x"
		*/
		if(pulsado && !colisionaAbajo()){
			cursor.moverAbajo(delta);
		}else{
			//dejo de moverme
			
			cursor.setVelocityY(0);
		}
		
		noAbajo = false;
	}
}