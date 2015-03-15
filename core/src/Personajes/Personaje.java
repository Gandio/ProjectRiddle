package Personajes;

import java.io.File;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.CuadroTexto;
import com.mygdx.game.MyGdxGame;

public abstract class Personaje extends Actor{
	private MyGdxGame game;
	protected Texture personaje;
	private Vector2 posicion;
	private CuadroTexto cuadroTexto;
	private File dialogo = new File("/Dialogos/prueba.txt");
	
	
	public Personaje(MyGdxGame game){
		this.game = game;
		posicion = new Vector2(0, 0);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(personaje, posicion.x, posicion.y);
	}
	
	public void setCoordenadas(float x, float y){
		posicion.x = x;
		posicion.y = y;
	}
	
	public float coordenadaX(){
		return posicion.x;
	}
	
	public float coordenadaY(){
		return posicion.y;
	}
}
