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

public class BotonSalirDiccionario extends Boton{
	private Sound sonido;

	/**
	 * Constructor de la clase
	 * @param game
	 */

	public BotonSalirDiccionario(TheHouseOfCrimes game) {
		super(game);
		
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_CERRAR_INVENTARIO));
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
		
		//sonido = 
	}
	
	/**
	 * Comprueba si se ha pulsado el botón, si es así, el jugador vuelve a la pantalla Inicio
	 */
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		/*
		 * Comprobamos si se pulsa el botón
		 */
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonSalirDiccionario)event.getTarget()).pulsado = true;
                return true;
            }
		});
	
		//Cerramos el diccionario
		
		if(pulsado){
			pulsado = false;
			sonido.play();
			
			//Linea de archivo de log transición
			TheHouseOfCrimes.getArchivoLog().escribirLinea(new LineaLog(TheHouseOfCrimes.getUsuario() + 
					";" +  TheHouseOfCrimes.getFecha() + ";" + Puntuacion.getError() * (-100) + 
					";" +Puntuacion.getPuntos() + ";" +  "T" + ";" + "pasillo."));
			
			game.setScreen(new Pasillo(game));
		}
		
		pulsado = false;
	}
}
