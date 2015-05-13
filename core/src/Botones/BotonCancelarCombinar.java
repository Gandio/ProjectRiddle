package Botones;

import Puzzle.Inventario;
import Puzzle.Inventario.Estado;

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
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonCancelarCombinar)event.getTarget()).pulsado = true;
                
                return true;
            }
		});
		
		if(((Inventario) game.getScreen()).getEstado() == Estado.COMBINANDO || 
				((Inventario) game.getScreen()).getEstado() == Estado.COMBINACION_PREPARADA){
			boton = botonActivado;
			if(pulsado){
				//logica
				((Inventario) game.getScreen()).setEstado(Estado.NORMAL);
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}
