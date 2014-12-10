package Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public abstract class Personaje extends Actor{
	MyGdxGame game;
	Texture personaje;
	
	public Personaje(MyGdxGame game){
		this.game = game;
	}
	public void update(){}

}
