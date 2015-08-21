package Puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.OrganizadorEstados;
import com.mygdx.game.Tools;

import Objetos.CuadroTexto;
import Pantallas.Habitacion;

/**
 * Esta clase representa a los cuadros que almacenan las posibles respuestas en los puzzles
 * de respuesta múltiple.
 * @author Francisco Madueño Chulián
 */

public class CuadroEleccion extends CuadroTexto{
	
	private boolean eleccionCorrecta; //indica si el cuadro contiene la eleccion correcta o no
	private boolean pulsado;
	
	/**
	 * Constructor de la clase
	 * @param game
	 */

	public CuadroEleccion(MyGdxGame game) {
		super(game);
		this.game = game;
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroEleccion.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
		
		texto = "";
		font = new BitmapFont();
		
		pulsado = false;
	}
	
	/**
	 * Dibuja al actor en el stage
	 */
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(cuadroTexto, coordenadas.x, coordenadas.y);
		font.setScale(2.5f);
		font.setColor(Color.BLACK);
		//No hace falta cuadrar el texto, este texto solo va a consistir en un par de 
		//palabras
		font.drawMultiLine(batch, texto, coordenadas.x + 20, coordenadas.y + 50); //esto hay que ajustarlo
	}
	
	/**
	 * Contiene la lógica del actor. Si se pulsa la opción correcta se manda un 1 al 
	 * organizador de estados, si no un 0. El organizador de estados actua de una forma
	 * u otra en consecuencia.
	 */
	
	public void update(){
		//Capturador de eventos, si el actor ha sido tocado pone la variable pulsado a true.
		setBounds(coordenadas.x, coordenadas.y, cuadroTexto.getWidth(), cuadroTexto.getHeight());
        addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((CuadroEleccion)event.getTarget()).pulsado = true;
                return true;
            }
		});
        
		if(pulsado){
			if(eleccionCorrecta){
				OrganizadorEstados.getEstadoActual().eleccionCorrecta(1);
			}else{
				OrganizadorEstados.getEstadoActual().eleccionCorrecta(0);
				
			}
			/*Siempre que eliges una opción sigue una conversacion, ya sea para
			 * darte la pista o indicarte que has fallado
			 */
			((Habitacion) game.getScreen()).terminarEleccion();
			((Habitacion) game.getScreen()).setConversando(true);
		}
		
		pulsado = false;
	}
	
	/**
	 * Dependiendo de la información que contega el fichero de la lógica del juego hace
	 * que una elección sea correcta o erronea.
	 */
	public void setEleccion(int i){
		if(i == 0) eleccionCorrecta = false;
		else eleccionCorrecta = true;
	}
}
