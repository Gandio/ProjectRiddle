package Puzzle;

import Pantallas.Inicio;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class Arma extends Actor{
	private boolean armaUsada;
	private Texture textura;
	private boolean pulsado = false;
	private Vector2 coordenadas;
	private static MyGdxGame game = Inicio.game;
	
	public Arma(boolean b, Texture t){
		armaUsada = b;
		textura = t;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Arma)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			if(armaUsada){
				//Se acaba el juego
			}else{
				//Error
			}
		}
	}
	
	public boolean esArmaUsada(){
		return armaUsada;
	}
}
