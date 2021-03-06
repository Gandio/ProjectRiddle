package Botones;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón de salir.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonSalir extends Boton{
	private Sound sonido;
	
	/**
	 * Constructor de la clase BotonSalir, posee un capturador de eventos que detecta si el botón
	 * ha sido pulsado. En cuyo caso sale de la aplicación.
	 * @param game
	 */
	public BotonSalir(TheHouseOfCrimes game, boolean esPantallaFinal) {
		super(game);
		
		//Inicializamos los atributos del botón.
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE && !esPantallaFinal)
			boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_SALIR_SUSPENSE));
		else 
			boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_SALIR));
		
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/boton.wav"));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
	}
	
	/**
	 * Si el botón ha sido pulsado se sale del juego se comprueba continuamente en el método render()
	 * de la clase Inicio.
	 */
	public void esPulsado(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonSalir)event.getTarget()).pulsado = true;
                return true;
            }
		});
        
        //Salimos del juego
        
		if(pulsado){
			sonido.play();
			Gdx.app.exit();
		}
	}
}