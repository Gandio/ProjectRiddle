package Botones;

import Pantallas.Pasillo;
import Puzzle.Inventario;
import Puzzle.Inventario.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class BotonCerrarInventario extends Boton{
	
	private Texture botonActivado, botonDesactivado;

	public BotonCerrarInventario(MyGdxGame game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal("Imagenes/Botones/botonSalirInventario.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/Botones/Desactivados/botonSalirInventarioDesactivado.png"));
		boton = botonActivado;
		
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
		
		if((((Inventario) game.getScreen()).getEstado() == Estado.NORMAL)){
			boton = botonActivado;
			if(pulsado){
				pulsado = false;
				game.setScreen(new Pasillo(game));
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}
