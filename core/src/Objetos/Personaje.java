package Objetos;

import Pantallas.Habitacion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public abstract class Personaje extends Actor{
	MyGdxGame game;
	Texture personaje;
	Habitacion habitacion;
	
	public Personaje(MyGdxGame game, Habitacion habitacion){
		this.game = game;
		this.habitacion = habitacion;
	}
	
	public void update(){}
	

}
