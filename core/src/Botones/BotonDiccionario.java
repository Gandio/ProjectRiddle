package Botones;

import Objetos.Puntuacion;
import Pantallas.Pasillo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.LineaLog;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que da acceso al diccionario de palabras.
 * @author Francisco Madueño Chulián
 *
 */
public class BotonDiccionario extends Boton {
	private Sound sonido;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */

	public BotonDiccionario(TheHouseOfCrimes game) {
		super(game);
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_DICCIONARIO));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
		
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/abrirInventario.wav"));
	}
	
	/**
	 * Este método actualiza el comportamiento del botón durante toda la partida
	 */
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonDiccionario)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		/*
		 * Si pulsamos el botón nos muestra el diccionario
		 */
		
		if(pulsado){
			pulsado = false;
			((Pasillo) game.getScreen()).pararMusica();
			sonido.play();
			
			//Linea de archivo de log transición
			TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + ";" +  
					TheHouseOfCrimes.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" + 
					Puntuacion.getPuntos() + ";" +  "T" + ";" + "diccionario."));
		}
		
		pulsado = false;
	}
}
