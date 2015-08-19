package Puzzle;

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
import com.mygdx.game.OrganizadorEstados;
import com.mygdx.game.Tools;

public class Asesino extends Actor{
	private boolean culpable = false;
	private Texture textura;
	private boolean pulsado = false;
	public Vector2 coordenadas;
	private static MyGdxGame game = Inicio.game;
	private Sound error;
	
	public Asesino(Texture t){
		textura = t;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/Error.wav"));
	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Asesino)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			if(culpable){ //se pasa a la pantalla de seleccion de arma
				game.setScreen(new PantallaArma());
			}else{
				Puntuacion.sumarError();
				error.play();
			}
		}
		
		pulsado = false;
	}
	
	public boolean esCulpable(){
		return culpable;
	}
	
	public void setCulpable(boolean b){
		culpable = b;
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y);
	}
	
	/**
	 * Devuelve la coordenada x del asesino
	 * @return x
	 */
	
	public float coordenadaX(){
		return coordenadas.x;
	}
	
	
	/**
	 * Devuelve la coordenada y del asesino
	 * @return y
	 */
	public float coordenadaY(){
		return coordenadas.y;
	}
}