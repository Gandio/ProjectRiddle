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
	
	public BotonDerecha(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/botonDerecha.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Se comprueba si el personaje está colisionando con alguna de las paredes, devuelve
	 * true o false.
	 * @return
	 */
	
	private boolean colisionaDerecha(){
		Array<Rectangle> paredes = ((Pasillo) game.getScreen()).getParedes();
		Cursor cursor = ((Pasillo) game.getScreen()).getCursor();
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noDerecha){
			rectanguloAux = iRect.next();
			aux = rectanguloAux.getX();
			if(cursor.getLimites().overlaps(rectanguloAux) && (cursor.getX() + cursor.getWidth() == aux + 1)){
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
		Cursor cursor = ((Pasillo) game.getScreen()).getCursor();
		
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonDerecha)event.getTarget()).pulsado = true;
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonDerecha)event.getTarget()).pulsado = false;
            }
		});
		
		if(pulsado && !colisionaDerecha()){
			cursor.setVelocityX(1);
			cursor.setVelocityY(0);
			
			cursor.MirarDerecha();
			
			cursor.setX(cursor.getX() + cursor.getVelocity().x);
			cursor.setY(cursor.getY() + cursor.getVelocity().y);
		  
		    //actualizamos nuestro stateTime y dibujamos el currentFrame.
		    cursor.setStateTime(delta);
		    
		    //Actualizamos la posición de los límites
		    cursor.getLimites().setPosition(cursor.getX(), cursor.getY());
		}else{
			cursor.setVelocityX(0);
		}
	    
	    noDerecha = false;
	}
}