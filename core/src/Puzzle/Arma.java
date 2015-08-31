package Puzzle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Objetos.Puntuacion;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las posibles armas que se han usado durante la partida. El 
 * jugador debe escoger, en base a las pistas conseguidas, cual de las armas que se ofrece
 * es la correcta.
 * @author Francisco Madueño Chulian
 */

public class Arma extends Actor{
	private boolean armaUsada = false;
	private Texture textura;
	private boolean pulsado = false;
	private Vector2 coordenadas;
	private static MyGdxGame game = Inicio.game;
	private Sound error;
	
	public enum NombreArma{
		DAGA, PISTOLA, RIFLE, SERPIENTE
	}
	
	/**
	 * Constructor de la clase
	 * @param t
	 */
	public Arma(Texture t){
		textura = t;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/Error.wav"));
	}
	
	/**
	 * Lógica del actor. Si se escoge el arma correcta se termina el juego y se añade la
	 * puntuación conseguida a un fichero txt. Si no se añade un fallo al contador de 
	 * errores y se restan puntos.
	 */
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Arma)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			if(armaUsada){ //Se acaba el juego
				/*Se crea un fichero para almacenar las puntuaciones, si ya existe no se
				 * sobreescribe. En este fichero se indica tanto los puntos conseguidos al 
				 * final de la partida como el número de fallos que el jugador ha cometido.
				 */
				File prueba = new File("puntuaciones.txt");
				PrintWriter writer;
				try {
					if(!prueba.exists()){
						try {
							prueba.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				try {
					writer = new PrintWriter(new FileWriter(prueba, true));
					writer.println("Puntuacion final: " + Puntuacion.getPuntos() +  " --- " +
									"Fallos totales: " + Puntuacion.getError());
					writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} finally{}
				
				Gdx.app.exit();
			}else{
				Puntuacion.sumarError();
				error.play();
			}
		}
		
		pulsado = false;
	}
	
	/**
	 * Dibuja el actor en el stage
	 */
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y);
	}
	
	/**
	 * Determina el arma que se ha usado
	 * @param b
	 */
	
	public void setUsada(boolean b){
		armaUsada = b;
	}
	
	/**
	 * Modifica las coordenadas de cada actor
	 * @param x
	 * @param y
	 */
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
}