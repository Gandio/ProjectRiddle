package Objetos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public final class Puntuacion extends Actor{
	private static MyGdxGame game;
	private static Puntuacion unicaInstancia;
	private Vector2 coordenadas;
	
	private static int puntos;
	private static int numFallos;
	private Texture puntuacion;
	
	protected BitmapFont font;
	protected String texto;
	
	private Puntuacion(MyGdxGame game){
		this.game = game;
		puntos = 0;
		numFallos = 0;
		texto = "Puntos: 0";
		font = new BitmapFont();
		font.scale(1.5f);
		coordenadas = new Vector2();
	}
	
	public void draw(Batch batch, float parentAlpha){
		font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		font.draw(batch, texto, coordenadas.x, coordenadas.y); 
	}
	
	public void setPuntuacion(int p){
		puntos += p;
	}
	
	public void sumarError(){
		numFallos += 1;
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public int getError(){
		return numFallos;
	}
	
	public static Puntuacion getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Puntuacion(game);
		}
		
		return unicaInstancia;
	}
}
