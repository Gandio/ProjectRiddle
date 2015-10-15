package Botones;

import Puzzle.Inventario;
import Puzzle.Inventario.EstadoInventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que activa el modo combinación.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonCombinarObjeto extends Boton{
	
	/**
	 * Constructor de la clase
	 * @param game
	 */

	public BotonCombinarObjeto(TheHouseOfCrimes game) {
		super(game);
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_COMBINAR_OBJETO));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Si se pulsa se activa el modo combinación
	 */
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonCombinarObjeto)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(((Inventario) game.getScreen()).getEstado() == EstadoInventario.NORMAL){
			if(pulsado){
				((Inventario) game.getScreen()).setEstado(EstadoInventario.COMBINANDO);
				Inventario.restaurarBotonesObjetos();
			}
		}
		
		pulsado = false;
	}
}