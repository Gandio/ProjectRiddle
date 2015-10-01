package Botones;

import Objetos.Puntuacion;
import Pantallas.Pasillo;
import Puzzle.Inventario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.GestorImagen;
import com.mygdx.game.LineaLog;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa el botón que da acceso al inventario del jugador.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonInventario extends Boton{
	private Sound sonido;
	
	/**
	 * Constructor de la clase.
	 * @param game
	 */

	public BotonInventario(TheCrimeHouse game) {
		super(game);
		boton = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_INVENTARIO));
		
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
                ((BotonInventario)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		/*
		 * Si pulsamos el botón nos muestra el inventario del jugador
		 */
		
		if(pulsado){
			pulsado = false;
			((Pasillo) game.getScreen()).pararMusica();
			sonido.play();
			
			//Linea de archivo de log transición
			TheCrimeHouse.getArchivoLog().escribirLinea(new LineaLog(TheCrimeHouse.getUsuario() + ";" +  
					TheCrimeHouse.getFecha() + ";" + Puntuacion.getError() * (-100) + ";" + 
					Puntuacion.getPuntos() + ";" +  "T" + ";" + "inventario."));
			
			Pasillo.getCursor();
			game.setScreen(Inventario.getInstancia());
		}
		
		pulsado = false;
	}
}