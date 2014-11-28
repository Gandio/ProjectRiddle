package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

public class BotonPuerta extends Boton{
	Texture actual, botonActivado, botonDesactivado;
	
	public BotonPuerta(MyGdxGame game) {
		super(game);
		botonActivado = new Texture(Gdx.files.internal("Imagenes/botonPuerta.png"));
		botonDesactivado = new Texture(Gdx.files.internal("Imagenes/botonPuertaDesactivado.png"));
		actual = botonDesactivado;
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(actual, coordenadaX(), coordenadaY());
	}
	
	public void activar(){
		actual = botonActivado;
	}
	
	public void desactivar(){
		actual = botonDesactivado;
	}
}
