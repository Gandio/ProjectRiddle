package Botones;

import Pantallas.Inicio;
import Pantallas.Pasillo;
import Puzzle.PantallaAsesino;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa al botón que iniciará la partida
 * @author Francisco Madueño Chulian
 *
 */

public class BotonContinuar extends Boton{
	
	private Sound sonido;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */

	public BotonContinuar(MyGdxGame game) {
		super(game);
		boton = new Texture(Gdx.files.internal("Imagenes/Botones/botonContinuar.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/boton.wav"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Detecta si el botón ha sido pulsado. Si se pulsa empieza la partida
	 */
	
	public void esPulsado(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonContinuar)event.getTarget()).pulsado = true;
                return true;
            }
		});
        
        /*
         * Si se pulsa empieza el juego.
         */
        
		if(pulsado){
			sonido.play();
			game.getScreen().dispose();
			//game.setScreen(new PantallaAsesino());
			game.setScreen(new Pasillo(Inicio.game));
		}
		
		pulsado = false;
	}
}