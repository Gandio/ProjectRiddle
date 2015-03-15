package Botones;

import Pantallas.Pasillo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class BotonCerrarInventario extends Boton{

	public BotonCerrarInventario(MyGdxGame game) {
		super(game);
		//boton = textura del boton
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonCerrarInventario)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			game.getScreen().dispose();
			game.setScreen(new Pasillo(game));
			pulsado = false;
		}
		
		pulsado = false;
	}
}
