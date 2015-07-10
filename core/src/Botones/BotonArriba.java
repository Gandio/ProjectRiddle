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
 * Esta clase representa el botón que hará que el personaje se mueva hacia arriba.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonArriba extends Boton{
	private boolean noArriba = false;
	
	public BotonArriba(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/botonArriba.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Se comprueba si el personaje está colisionando con alguna de las paredes, devuelve
	 * true o false.
	 * @return
	 */
	
	private boolean colisionaArriba(){
		Array<Rectangle> paredes = ((Pasillo) game.getScreen()).getParedes();
		Cursor cursor = Pasillo.getCursor();
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		/*Algoritmo de colisiones. Comprobamos todos los bordes inferiores de las paredes,
		 * si chocamos mientras nos movemos hacia arriba chocamos con el borde inferior de las 
		 * paredes. Si en algún momento se solapan los rectangulos de la pared y el cursor se 
		 * activa la variable noArriba que nos impide seguir con este movimiento. Debemos preveer
		 * el choque, si no el cursor se quedará atrapado, solo podremos movernos de izquierda
		 * a derecha mientras nos estemos cochando, por eso comprobamos tambien si se va 
		 * a chocar
		*/
		
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
		Cursor cursor = Pasillo.getCursor();
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
		
		//Me empiezo a mover. Hacmos justo lo contrario que con el boton Abajo.
		
		if(pulsado && !colisionaArriba()){
			cursor.moverArriba(delta);
		}else{
			//Dejo de moverme
			cursor.setVelocityY(0);
		}
		
		noArriba = false;
	}
}