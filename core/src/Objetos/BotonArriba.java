package Objetos;

import java.util.Iterator;

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
 * Esta clase representa el botón que hará que el personaje se mueva hacia arriba.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonArriba extends Boton{
	private boolean noArriba = false;
	
	public BotonArriba(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/botonArriba.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Se comprueba si el personaje está colisionando con alguna de las paredes, devuelve
	 * true o false.
	 * @return
	 */
	
	private boolean colisionaArriba(){
		Array<Rectangle> paredes = ((Pasillo) game.getScreen()).getParedes();
		Cursor cursor = ((Pasillo) game.getScreen()).getCursor();
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noArriba){
			rectanguloAux = iRect.next(); 
			aux = rectanguloAux.getY();
			if(cursor.getLimites().overlaps(rectanguloAux) && (cursor.getY() + cursor.getHeight()) == aux + 1){
				noArriba = true;
			}
			
			++i;
		}
		
		return noArriba;
	}
	
	/**
	 * Este método comprueba si el botón ha sido pulsado, en cuyo caso, y si no colisiona
	 * con ninguna pared hace que el personaje se mueva hacia arriba.
	 * @param delta
	 */
	
	public void esPulsado(float delta){
		Cursor cursor = ((Pasillo) game.getScreen()).getCursor();
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonArriba)event.getTarget()).pulsado = true;
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonArriba)event.getTarget()).pulsado = false;
            }
		});
		
		if(pulsado && !colisionaArriba()){
			cursor.setVelocityY(1);
			cursor.setVelocityX(0);
			
			cursor.MirarArriba();
			
			cursor.setX(cursor.getX() + cursor.getVelocity().x);
			cursor.setY(cursor.getY() + cursor.getVelocity().y);
	      
	        //actualizamos nuestro stateTime y dibujamos el currentFrame.
	        cursor.setStateTime(delta);
	        
	        //Actualizamos la posición de los límites
	        cursor.getLimites().setPosition(cursor.getX(), cursor.getY());
	        
		}else{
			cursor.setVelocityY(0);
		}
		
		noArriba = false;
	}
}