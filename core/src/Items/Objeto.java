package Items;

import java.util.Iterator;

import Objetos.Cursor;
import Pantallas.Habitacion;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public abstract class Objeto extends Actor{
	private MyGdxGame game = Habitacion.game;
	protected Texture textura;
	//protected BotonObjeto botonObjeto;
	protected Vector2 coordenadas;
	protected Array<Objeto> combinables;
	private boolean sePuedeCoger, investigando, seleccionado;
	protected Class<?> tipoObjeto;
	private boolean tocadoUnaVez = false;
	
	
	public Objeto(MyGdxGame game){
		//this.game = game;
		
		System.out.println("Objeto " + this.game);
		
		sePuedeCoger = false;
		investigando = false;
		seleccionado = false;
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y);
	}
	
	public void seCoge(boolean b){
		sePuedeCoger = b;
	}
	
	public void seInvestiga(boolean b){
		investigando = b;
	}
	
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
                if(!tocadoUnaVez){
                	((Objeto)event.getTarget()).seleccionado = true;
                	tocadoUnaVez = true;
                	System.out.println("me tocaste");
                	cogerObjeto();
                }
                
                return true;
            }
		});
		
	}
	
	public void dispose(){
		textura.dispose();
	}
}