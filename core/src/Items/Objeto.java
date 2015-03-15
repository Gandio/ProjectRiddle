package Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public abstract class Objeto extends Actor{
	private MyGdxGame game;
	protected Texture textura;
	protected Vector2 coordenadas;
	boolean sePuedeCoger;
	
	public Objeto(MyGdxGame game){
		this.game = game;
		sePuedeCoger = false;
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y);
	}
}
