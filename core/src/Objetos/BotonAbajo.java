package Objetos;

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

public class BotonAbajo extends Boton{
	Cursor cursor;
	private Array<Rectangle> paredes;
	private boolean noAbajo = false;
	
	public BotonAbajo(MyGdxGame game, Cursor cursor, Array<Rectangle> paredes) {
		super(game);
		this.cursor = cursor;
		this.paredes = paredes;
		boton = new Texture(Gdx.files.internal("Imagenes/botonAbajo.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public boolean colisionaAbajo(){
		int i = 0;
		Iterator<Rectangle> iRect = paredes.iterator();
		float aux;
		Rectangle rectanguloAux;
		
		while(i < paredes.size && !noAbajo){
			rectanguloAux = iRect.next(); 
			aux = (rectanguloAux.getY() + rectanguloAux.getHeight());
			if(cursor.getLimites().overlaps(rectanguloAux) && (cursor.getY() == (aux - 1))){
				noAbajo = true;
			}
			
			++i;
		}
		
		return noAbajo;
	}
	
	public void esPulsado(float delta){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonAbajo)event.getTarget()).pulsado = true;
                return true;
            }
            
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonAbajo)event.getTarget()).pulsado = false;
            }
		});
		
		if(pulsado && !colisionaAbajo()){
			cursor.setVelocityY(-1);
			cursor.setVelocityX(0);
			
			cursor.MirarAbajo();
			
			cursor.setX(cursor.getX() + cursor.getVelocity().x);
			cursor.setY(cursor.getY() + cursor.getVelocity().y);
	      
	        //actualizamos nuestro stateTime y dibujamos el currentFrame.
	        cursor.setStateTime(delta);
	        
	        //Actualizamos la posición de los límites
	        cursor.getLimites().setPosition(cursor.getX(), cursor.getY());
			
		}else{
			cursor.setVelocityY(0);
		}
		
		noAbajo = false;
	}
}