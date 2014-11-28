package Objetos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

public class BotonPuertaDesactivado extends Boton{
	
	public BotonPuertaDesactivado(MyGdxGame game) {
		super(game);
		super.boton = new Texture(Gdx.files.internal("Imagenes/botonPuertaDesactivado.png"));
	}
	
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(boton, coordenadaX(), coordenadaY());
	}
}
