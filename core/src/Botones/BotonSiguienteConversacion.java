package Botones;

import Pantallas.Habitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class BotonSiguienteConversacion extends Boton{

	public BotonSiguienteConversacion(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/sigConv.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void esPulsado(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonSiguienteConversacion)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			//pasa al texto siguiente y si es el ultimo texto dibuja el boton fin de conversacion
			if(((Habitacion) game.getScreen()).getCuadroTexto().getFinTexto()){
				((Habitacion) game.getScreen()).esUltimoTexto();
			}
			
			((Habitacion) game.getScreen()).getCuadroTexto().sigParteTexto();
		}
		
		
		pulsado = false;
	}
}
