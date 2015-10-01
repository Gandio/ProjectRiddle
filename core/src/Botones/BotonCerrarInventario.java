package Botones;

import Objetos.Puntuacion;
import Pantallas.Pasillo;
import Puzzle.Inventario;
import Puzzle.Inventario.EstadoInventario;

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
 * Esta clase representa el botón que cierra el inventario y vuelve al pasillo. Este botón se desactiva cuando
 * el jugador entra en el modo de combinar objetos.
 * @author Francisco Madueño Chulián
 *
 */

public class BotonCerrarInventario extends Boton{
	
	private Texture botonActivado, botonDesactivado;
	private Sound sonido;
	/**
	 * Constructor de la clase.
	 * @param game
	 */

	public BotonCerrarInventario(TheCrimeHouse game) {
		super(game);
		
		botonActivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_CERRAR_INVENTARIO));
		botonDesactivado = new Texture(Gdx.files.internal(GestorImagen.URL_BOTON_CERRAR_INVENTARIO_DES));
		boton = botonActivado;
		
		coordenadas = new Vector2(Tools.centrarAncho(game, boton), Tools.centrarAlto(game, boton));
		sonido = Gdx.audio.newSound(Gdx.files.internal("Sonido/abrirInventario.wav"));
	}
	
	/**
	 * Metodo que actualiza la lógica del boton durante todo el juego, solo se puede 
	 * salir del inventario si el estado de este es normal, es decir, no estamos 
	 * combinando objetos.
	 */
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, boton.getWidth(), boton.getHeight());
		
		/*
		 * Comprobamos si se pulsa el botón
		 */
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((BotonCerrarInventario)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		/*Si el estado del inventario es el normal activamos el botón, si no lo
		 * desactivamos
		 */
		if((((Inventario) game.getScreen()).getEstado() == EstadoInventario.NORMAL)){
			boton = botonActivado;
			//Cerramos el inventario
			if(pulsado){
				pulsado = false;
				Inventario.pararMusica();
				Inventario.restaurarBotonesObjetos();
				sonido.play();
				
				//Linea de archivo de log transición
				TheCrimeHouse.getArchivoLog().escribirLinea(new LineaLog(TheCrimeHouse.getUsuario() + 
						";" +  TheCrimeHouse.getFecha() + ";" + Puntuacion.getError() * (-100) + 
						";" +Puntuacion.getPuntos() + ";" +  "T" + ";" + "pasillo."));
				
				Inventario.getCuadroDescripcion().setTexto("");
				game.setScreen(new Pasillo(game));
			}
		}else{
			boton = botonDesactivado;
		}
		
		pulsado = false;
	}
}
