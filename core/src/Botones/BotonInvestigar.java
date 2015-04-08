package Botones;

import Pantallas.Habitacion;
import Pantallas.Habitacion.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón de investigar. Este botón se usará pasa activar o 
 * desactivar el modo investigación mientras te encuentres en una habitación
 * @author Francisco Madueño Chulián
 */

public class BotonInvestigar extends Boton{
	
	public BotonInvestigar(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/botonInvestigar.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonInvestigar)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado && ((Habitacion) game.getScreen()).getEstado() == Estado.NORMAL){
			((Habitacion) game.getScreen()).setEstado(Estado.INVESTIGAR);
		}else if(pulsado && ((Habitacion) game.getScreen()).getEstado() == Estado.INVESTIGAR){
			((Habitacion) game.getScreen()).setEstado(Estado.NORMAL);
		}
		
		pulsado = false;
	}
}