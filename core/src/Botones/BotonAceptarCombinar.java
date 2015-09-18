package Botones;

import Items.Objeto;
import Puzzle.Inventario;
import Puzzle.Inventario.EstadoInventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que realiza la combinación de objetos. Este botón solo se activa cuando 
 * el jugador se encuentra combinando y se han seleccionado dos objetos que pueden combinarse.
 * @author Francisco Madueño Chulián
 */

public class BotonAceptarCombinar extends Boton{

	private Texture botonActivado, botonDesactivado;
	private Sound sonido;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */
	public BotonAceptarCombinar(MyGdxGame game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_ACEPTAR_COMBINAR));
		botonDesactivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_ACEPTAR_COMBINAR_DES));
		boton = botonDesactivado;
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
		
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/combinarObjeto.wav"));
	}
	
	/**
	 * Este método implementa la lógica del boton. Solo se activa si hay dos objetos 
	 * seleccionados. Y solo sirve si ambos objetos se pueden combinar, en caso contrario no
	 * sirve de nada.
	 */
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		//Si el array de combinacion está lleno se pasa al estado combinacion preparada
		if(((Inventario) game.getScreen()).getCombinacion().size == 2){
			((Inventario) game.getScreen()).setEstado(EstadoInventario.COMBINACION_PREPARADA);
		}
		
		addListener(new InputListener(){
			//Se pulsa el botón de aceptar la combinación
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonAceptarCombinar)event.getTarget()).pulsado = true;
                
                return true;
            }
		});
		
		if(((Inventario) game.getScreen()).getEstado() == EstadoInventario.COMBINACION_PREPARADA){
			boton = botonActivado;
			if(pulsado){
				//Compruebo si los objetos se pueden combinar, si lo son se combinan, se eliminan del 
				//inventario, se añade el resultado de la combinación y se vuelve al estado normal, si no 
				//se vuelve al estado normal y se vacia el array de combinacion
				
				((Inventario) game.getScreen()).restaurarBotonesObjetos();
				
				if(((Inventario) game.getScreen()).sePuedeCombinar()){
					sonido.play();
					combinar();
				}
				
				((Inventario) game.getScreen()).getCombinacion().clear();
				((Inventario) game.getScreen()).setEstado(EstadoInventario.NORMAL);
			}
		}else{
			//Boton desactivado
			boton = botonDesactivado;
		}
		//Se actualiza la variable pulsado
		pulsado = false;
	}
	
	/**
	 * Este método combina dos objetos
	 */
	private void combinar(){
		Objeto o1 = ((Inventario) game.getScreen()).getCombinacion().get(0);
		Objeto o2 = ((Inventario) game.getScreen()).getCombinacion().get(1);
		
		Inventario.borrarObjeto(o1);
		Inventario.borrarObjeto(o2);
		
		Inventario.añadirObjeto(Tools.devolverCombinacion(game, o1.getId(), o2.getId()));
	}
}