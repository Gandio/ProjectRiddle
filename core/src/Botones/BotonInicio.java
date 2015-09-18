package Botones;


import Pantallas.Introduccion;

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
		
		//Inicializamos los atributos del botón.
		if(MyGdxGame.SUSPENSE_AMBIENTE)
			boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_INICIO_SUSPENSE));
		else
			boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_INICIO));
		
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/boton.wav"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Si el botón ha sido pulsado crea una nueva partida, se comprueba continuamente en el método render()
	 * de la clase Inicio.
	 */
	public void esPulsado(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonInicio)event.getTarget()).pulsado = true;
                return true;
            }
		});
        
        /*
         * Si se pulsa empieza el juego.
         */
        
		if(pulsado){
			sonido.play();
			game.getScreen().dispose();
			game.setScreen(new Introduccion());
		}
	}
}