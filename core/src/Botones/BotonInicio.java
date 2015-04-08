package Botones;

import Pantallas.Inicio;
import Pantallas.Pasillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón de nueva partida.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonInicio extends Boton{
	private Sound sonido;
	
	/**
	 * Constructor de la clase BotonInicio, posee un capturador de eventos que detecta si el botón
	 * ha sido pulsado. En cuyo caso inicia una nueva partida.
	 * @param game
	 */
	public BotonInicio(MyGdxGame game) {
		super(game);
		
		System.out.println("Boton inicio " + game);
		
		//Inicializamos los atributos del botón.
		boton = new Texture(Gdx.files.internal("Imagenes/botonInicio.png"));
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/boton.wav"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton) - 280);
		
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonInicio)event.getTarget()).pulsado = true;
                return true;
            }
		});
	}
	
	/**
	 * Si el botón ha sido pulsado crea una nueva partida, se comprueba continuamente en el método render()
	 * de la clase Inicio.
	 */
	public void esPulsado(){
		if(pulsado){
			sonido.play();
			game.getScreen().dispose();
			game.setScreen(new Pasillo(Inicio.game));
		}
	}
}