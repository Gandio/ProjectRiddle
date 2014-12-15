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
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camara.update();
		
		batch.begin();
		batch.draw(pantalla, 0, 0, stage.getWidth(), stage.getHeight());
		batch.end();
		
		//Posicion de botones
		botonPuerta.setCoordenadas(200, 200);
		botonInvestigar.setCoordenadas(250, 200);
		botonConversacion.setCoordenadas(300, 200);
		
		controladorBotonPuerta.update();
		controladorConversacion.update();
		controladorInvestigar.update();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.setViewport(viewport);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		pantalla = new Texture(Gdx.files.internal("Imagenes/plantillaHabitacion.png"));
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		pantalla.dispose();
	}
}