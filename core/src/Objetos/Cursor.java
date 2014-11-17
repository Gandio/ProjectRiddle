package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.MyGdxGame;

public class Cursor extends Actor{
	
	MyGdxGame game;
	private Texture cursor;
	private TextureRegion arriba, abajo, derecha, izquierda;
	private TextureRegion[][] tmp;
	private TextureRegion frameActual;
	private Vector2 posicion;
	private float velocidadCursor;
	
	public Cursor(MyGdxGame game) {
		this.game = game;
		cursor = new Texture(Gdx.files.internal("Imagenes/personaje.png"));
		tmp = TextureRegion.split(cursor, cursor.getWidth() / 4, cursor.getHeight());
		arriba = tmp[0][0];
		abajo = tmp[0][2];
		derecha = tmp[0][3];
		izquierda = tmp[0][1];
		
		frameActual = new TextureRegion(arriba);
		posicion = new Vector2(0, 0);
		
		velocidadCursor = 90.0f;
	}
	
	//Dibujamos al actor
	public void draw(Batch batch, float parentAlpha){
		batch.draw(frameActual, (int)posicion.x, (int)posicion.y);
		
	}
	
	//Logica del actor
	public void act(float delta){
		if(Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			frameActual = arriba;
			posicion.y += Gdx.graphics.getDeltaTime() * velocidadCursor;
		}
		
		if(!Gdx.input.isKeyJustPressed(Keys.W) && Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			frameActual = izquierda;
			posicion.x -= Gdx.graphics.getDeltaTime() * velocidadCursor;
		}
		
		if(!Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& Gdx.input.isKeyJustPressed(Keys.S) && !Gdx.input.isKeyJustPressed(Keys.D)){
			frameActual = abajo;
			posicion.y -= Gdx.graphics.getDeltaTime() * velocidadCursor;
		}
		
		if(!Gdx.input.isKeyJustPressed(Keys.W) && !Gdx.input.isKeyJustPressed(Keys.A)
				&& !Gdx.input.isKeyJustPressed(Keys.S) && Gdx.input.isKeyJustPressed(Keys.D)){
			frameActual = derecha;
			posicion.x += Gdx.graphics.getDeltaTime() * velocidadCursor;
		} 
	}
	
	public Vector2 getPosicion(){
		return posicion;
	}
}