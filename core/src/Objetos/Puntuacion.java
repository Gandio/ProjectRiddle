package Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class Puntuacion extends Actor{
	private static MyGdxGame game;
	private static int puntos;
	private Texture puntuacion;
	
	public Puntuacion(MyGdxGame game){
		this.game = game;
		puntos = 0;
	}
	
	
	public void draw(Batch batch, float parentAlpha){
		
	}
	
	
	public void setPuntuacion(int p){
		puntos += p;
	}

}
