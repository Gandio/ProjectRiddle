package Botones;

import Puzzle.Inventario;
import Puzzle.Inventario.EstadoInventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Este clase representa el botón que cancela la combinanción de los objetos. Este botón esta activado durante
 * todo momento en el modo combinación.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonCancelarCombinar extends Boton{
	
	private Texture botonActivado, botonDesactivado;

	public BotonCancelarCombinar(MyGdxGame game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonCancelarCombinar.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonCancelarCombinarDesactivado.png"));
		boton = botonDesactivado;
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
			//se pulsa el botón
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                //Se actualiza la variable pulsado
            	((BotonCancelarCombinar)event.getTarget()).pulsado = true;
                
                return true;
            }
		});
		
		//Si el estado actual del inventario es combinando o combinacion preparada se activa el botón
		if(((Inventario) game.getScreen()).getEstado() == EstadoInventario.COMBINANDO || 
				((Inventario) game.getScreen()).getEstado() == EstadoInventario.COMBINACION_PREPARADA){
			boton = botonActivado;
			//Solo si se ha pulsado
			if(pulsado){
				//Se vuelve al estado normal, se vacia el array de combinacion y se devuelve
				//la textura original de los objetos
				((Inventario) game.getScreen()).setEstado(EstadoInventario.NORMAL);
				((Inventario) game.getScreen()).getCombinacion().clear();
				((Inventario) game.getScreen()).restaurarBotonesObjetos();
			}
		}else{
			//El botón está desactivado y no funciona
			boton = botonDesactivado;
		}
		
		//Volvemos a actualizar la variable pulsado
		pulsado = false;
	}
}
