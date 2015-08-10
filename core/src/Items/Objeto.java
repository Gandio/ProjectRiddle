package Items;

import java.io.IOException;
import java.util.Iterator;

import Objetos.Cursor;
import Objetos.Puntuacion;
import Pantallas.Habitacion;
import Pantallas.Habitacion.EstadoHabitacion;
import Pantallas.Pasillo;
import Puzzle.Inventario;
import Puzzle.Inventario.EstadoInventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase abstracta representa a todos los objetos con los que se puede interactuar durante una 
 * partida.
 * @author Francisco Madueño Chulián
 */

public abstract class Objeto extends Actor{
	private MyGdxGame game = Pasillo.game;
	protected Texture textura, botonObjeto, botonObjetoActivado, texturaActualBoton;
	protected Vector2 coordenadas;
	protected Array<Identificador> combinables;
	private boolean sePuedeCoger, investigando, seleccionado;
	protected Class<?> tipoObjeto;
	private boolean tocadoUnaVez = false, control1 = false, control2 = false;
	protected Identificador identificador;
	protected int id = -1;
	
	protected XmlReader reader = new XmlReader();
	protected Element raiz;
	protected Array<Element> objetos;
	protected String descripcionObjeto;
	
	private Sound sonido, error;
	
	public Objeto(MyGdxGame game){
		//this.game = game;
		
		sePuedeCoger = false;
		investigando = false;
		seleccionado = false;
		
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/cogerObjeto.wav"));
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/Error.wav"));
		
		try{
			raiz = reader.parse(Gdx.files.internal("xml/objetosAleman.xml"));
		}catch(IOException e){}
		
		objetos = raiz.getChildrenByName("objeto");
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	//Si estamos en el inventario se pinta el botón asociado al objeto, si no dibujamos el objeto.
	
	public void draw(Batch batch, float parentAlpha) {
		game.getClass();
		if(game.getScreen().getClass() == Inventario.class){
			batch.draw(texturaActualBoton, coordenadas.x, coordenadas.y);
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
		
		if(investigando && seleccionado){
			if(sePuedeCoger){
				//Colocamos el objeto en el inventario
				c.getInventario().añadirObjeto(this);
			
			//Y lo quitamos de la habitacion
			iter = h.getObjetos().iterator();
			sonido.play();
			
			while(iter.hasNext()){
				if(iter.next().getClass() == tipoObjeto){
					iter.remove();
					remove();
				}
			}
			
			}else{
				//Sumamos 1 al contador de errores y se reproduce un efecto de sonido
				Puntuacion.sumarError();
				error.play();
			}
		}
	}
	
	public void seSelecciona(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(game.getScreen().getClass() != Inventario.class && ((Habitacion) game.getScreen()).getEstado() == EstadoHabitacion.INVESTIGAR){
                	/*
                	 * Si pulsamos sobre el objeto se selecciona y se deseleciona durante varios
                	 * frames, para que no ocurra eso usamos este condicional.
                	 */
                	if(!tocadoUnaVez){
                		((Objeto)event.getTarget()).seleccionado = true;
                		tocadoUnaVez = true;
                		/*
                		 * Si no estuviera el condicional se cogerían varias veces el objeto
                		 */
                		cogerObjeto();
                	}
                }
                
                return true;
            }
		});
	}
	
	public void seSeleccionaBoton(){
		final Objeto o = this;
		setBounds(coordenadas.x, coordenadas.y, texturaActualBoton.getWidth(), texturaActualBoton.getHeight());
		addListener(new InputListener(){
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
            	if(((Inventario) game.getScreen()).getEstado() == Puzzle.Inventario.EstadoInventario.COMBINANDO
            			|| ((Inventario) game.getScreen()).getEstado() == Puzzle.Inventario.EstadoInventario.COMBINACION_PREPARADA){
        			if(control1 && !control2) control2 = true;
        			else if(!control1 && control2)control2 = false;
        		}
            }
            
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            	//Se pueden activar o desactivar los botones objeto en cualquier momento mientras que
            	//no se acepte la combinación
            	
                if(((Inventario) game.getScreen()).getEstado() == Puzzle.Inventario.EstadoInventario.COMBINANDO 
                		||((Inventario) game.getScreen()).getEstado() == Puzzle.Inventario.EstadoInventario.COMBINACION_PREPARADA){
                	
                	//Borramos el texto de las descripciones mientras este modo esté activo
                	Inventario.getCuadroDescripcion().setTexto("");
                	
                	
                	if(!control1 && !control2 && ((Inventario) game.getScreen()).getCombinacion().size < 2){
                		//Iluminamos el boton y añadimos el objeto al vector de combinación
                		texturaActualBoton = botonObjetoActivado;
                    	((Inventario) game.getScreen()).getCombinacion().add(o);
                    	control1 = true;
                		
                	}else if(control1 && control2){
                		//Devolvemos el botón a su estado inicial y quitamos el objeto del 
                		//vector de combinacion
                		texturaActualBoton = botonObjeto;
                    	((Inventario) game.getScreen()).getCombinacion().removeValue(o, true);
                    	((Inventario) game.getScreen()).setEstado(EstadoInventario.COMBINANDO);
                    	control1 = false;
                    	
                	}
                }
            	
            	if(((Inventario) game.getScreen()).getEstado() == Puzzle.Inventario.EstadoInventario.NORMAL)
            		Inventario.getCuadroDescripcion().setTexto(o.getDescripcion());
                
                return true;
            }
		});
	}
	
	public String getDescripcion(){
		return descripcionObjeto;
	}
	
	public void devolverTexturaOriginal(){
		texturaActualBoton = botonObjeto;
	}
	
	public Identificador getIdentificador(){
		return identificador;
	}
	
	public Array<Identificador> getCombinables(){
		return combinables;
	}
	
	public int getId(){
		return id;
	}
}