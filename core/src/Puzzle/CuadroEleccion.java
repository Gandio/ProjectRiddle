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

import Botones.BotonSalir;
import Objetos.CuadroTexto;
import Pantallas.Habitacion;
import Pantallas.Habitacion.EstadoHabitacion;

public class CuadroEleccion extends CuadroTexto{
	
	private boolean eleccionCorrecta;
	private boolean pulsado;

	public CuadroEleccion(MyGdxGame game) {
		super(game);
		this.game = game;
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroEleccion.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
		
		texto = "";
		font = new BitmapFont();
		
		pulsado = false;
	}
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(cuadroTexto, coordenadas.x, coordenadas.y);
		font.setScale(2.5f);
		font.setColor(Color.BLACK);
		//No hace falta cuadrar el texto, este texto solo va a consistir en un par de 
		//palabras
		font.drawMultiLine(batch, texto, coordenadas.x + 20, coordenadas.y + 50); //esto hay que ajustarlo
	}
	
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
			if(getEleccion()){
				OrganizadorEstados.getEstadoActual().eleccionCorrecta(1);
			}else{
				OrganizadorEstados.getEstadoActual().eleccionCorrecta(0);
				
			}
			//Siempre que eliges una opción termina en una conversacion
			((Habitacion) game.getScreen()).terminarEleccion();
			((Habitacion) game.getScreen()).setConversando(true);
		}
		
		pulsado = false;
	}
	
	public void setEleccion(int i){
		if(i == 0) eleccionCorrecta = false;
		else eleccionCorrecta = true;
	}
	
	public boolean getEleccion(){
		return eleccionCorrecta;
	}
}
