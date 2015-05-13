package Items;

import java.util.Iterator;

import Objetos.Cursor;
import Pantallas.Habitacion;
import Pantallas.Habitacion.Estado;
import Puzzle.Inventario;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase abstracta representa a todos los objetos con los que se puede interactuar durante una 
 * partida.
 * @author Francisco Madueño Chulián
 */

public abstract class Objeto extends Actor{
	private MyGdxGame game = Habitacion.game;
	protected Texture textura;
	protected Texture botonObjeto;
	protected Vector2 coordenadas;
	protected Array<Identificador> combinables;
	private boolean sePuedeCoger, investigando, seleccionado;
	protected Class<?> tipoObjeto;
	private boolean tocadoUnaVez = false;
	private boolean control1 = false, control2 = false;
	protected Identificador identificador;
	protected int id;
	
	
	public Objeto(MyGdxGame game){
		//this.game = game;
		
		sePuedeCoger = false;
		investigando = false;
		seleccionado = false;
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	//Si estamos en el inventario se pinta el botón asociado al objeto, si no dibujamos el objeto.
	
	public void draw(Batch batch, float parentAlpha) {
		if(game.getScreen().getClass() == Inventario.class){
			batch.draw(botonObjeto, coordenadas.x, coordenadas.y);
		}else{
			batch.draw(textura, coordenadas.x, coordenadas.y);
		}
	}
	
	public void seCoge(boolean b){
		sePuedeCoger = b;
	}
	
	public void seInvestiga(boolean b){
		investigando = b;
	}
	
	public void dispose(){
		textura.dispose();
	}
	
	//------------------------------------------------------------------------------------------
	//--------------------------------------METODOS AUXILIARES----------------------------------
	//------------------------------------------------------------------------------------------
	
	public void cogerObjeto(){
		Habitacion h = (Habitacion) game.getScreen(); //habitación del objeto
		Cursor c = h.getCursor();
		Iterator<Objeto> iter;
		
		if(investigando && sePuedeCoger && seleccionado){;
			//Colocamos el objeto en el inventario
			c.getInventario().añadirObjeto(this);
			
			//Y lo quitamos de la habitacion
			iter = h.getObjetos().iterator();
			
			while(iter.hasNext()){
				if(iter.next().getClass() == tipoObjeto){
					iter.remove();
					remove();
					System.out.println("lo quito");
				}
			}
		}
	}
	
	public void seSelecciona(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(game.getScreen().getClass() != Inventario.class && ((Habitacion) game.getScreen()).getEstado() == Estado.INVESTIGAR){
                	if(!tocadoUnaVez){
                		((Objeto)event.getTarget()).seleccionado = true;
                		tocadoUnaVez = true;
                		System.out.println("me tocaste");
                		cogerObjeto();
                	}
                }
                
                return true;
            }
		});
		
	}
	
	public void seSeleccionaBoton(){
		final Objeto o = this;
		setBounds(coordenadas.x, coordenadas.y, botonObjeto.getWidth(), botonObjeto.getHeight());
		addListener(new InputListener(){
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(control1 && !control2) control2 = true;
        		else if(!control1 && control2)control2 = false;
            }
            
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(((Inventario) game.getScreen()).getEstado() == Puzzle.Inventario.Estado.COMBINANDO
                		&& ((Inventario) game.getScreen()).getCombinacion().size < 2){
                	
                	if(!control1 && !control2){
                		//Iluminamos el boton y añadimos el objeto al vector de combinación
                    	((Inventario) game.getScreen()).getCombinacion().add(o);
                    	control1 = true;
                		
                	}else if(control1 && control2){
                		//Devolvemos el botón a su estado inicial y quitamos el objeto del 
                		//vector de combinacion
                    	((Inventario) game.getScreen()).getCombinacion().removeValue(o, true);
                    	control1 = false;
                	}
                }
                
                return true;
            }
		});
	}
}