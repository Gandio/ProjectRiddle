package Botones;

import java.util.Iterator;

import Items.Objeto;
import Puzzle.Inventario;
import Puzzle.Inventario.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class BotonCombinarObjeto extends Boton{

	public BotonCombinarObjeto(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/botonCombinar.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonCombinarObjeto)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(((Inventario) game.getScreen()).getEstado() == Estado.NORMAL){
			if(pulsado){
				((Inventario) game.getScreen()).setEstado(Estado.COMBINANDO);
			}
		}
		
		pulsado = false;
	}
	
	public void deshabilitarObjetos(){
		Inventario i = ((Inventario) game.getScreen());
		Iterator<Objeto> iter = i.getContenido().iterator();
		
		while(iter.hasNext()){
			//si el objeto no es combinable se "apaga"
		}
	}
	
	public void opcionesCombinables(Objeto o){
		Inventario i = ((Inventario) game.getScreen());
		Iterator<Objeto> iter = i.getContenido().iterator();
		
		while(iter.hasNext()){
			//si el objeto esta habilitado y no pertenece a la lista de combinables del 
			//objeto o se "apaga"
		}
	}

}
