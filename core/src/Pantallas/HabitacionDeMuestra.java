package Pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class HabitacionDeMuestra extends Habitacion{
	public HabitacionDeMuestra(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show() {
		pantalla = new Texture(Gdx.files.internal("Imagenes/plantillaHabitacion.png"));
	}
}