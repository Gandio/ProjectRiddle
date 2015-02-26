package Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

/** Esta clase abstracta representa las funcionalidades básicas de los botones de todo el juego.
*   @author Francisco Madueño Chulián 
*/

public abstract class Boton extends Actor{
	protected MyGdxGame game;
	protected Texture boton;
	protected Vector2 coordenadas;
	protected boolean pulsado = false;
	
	public Boton(MyGdxGame game){
		this.game = game;
	}

	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(boton, coordenadas.x, coordenadas.y);
	}
}
