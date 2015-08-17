package Puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.OrganizadorEstados;

public class PantallaAsesino implements Screen{
	private Array<Asesino> asesinos;
	private Stage stage;
	private Texture textura;
	
	public PantallaAsesino(){
		asesinos = new Array<Asesino>();
		stage = new Stage();
		
		asesinos.add(new Asesino(new Texture(Gdx.files.internal("Imagenes/Personajes/chica.png"))));
		asesinos.add(new Asesino(new Texture(Gdx.files.internal("Imagenes/Personajes/hombre.png"))));
		asesinos.add(new Asesino(new Texture(Gdx.files.internal("Imagenes/Personajes/joven.png"))));
		asesinos.add(new Asesino(new Texture(Gdx.files.internal("Imagenes/Personajes/Mujer-mayor.png"))));
		asesinos.add(new Asesino(new Texture(Gdx.files.internal("Imagenes/Personajes/mujer.png"))));
		
		if(OrganizadorEstados.getAsesino() == 0) //es la niña
			asesinos.get(0).setCulpable(true);
		else if(OrganizadorEstados.getAsesino() == 1) //es el hombre
			asesinos.get(1).setCulpable(true);
		else if(OrganizadorEstados.getAsesino() == 2) //es el joven
			asesinos.get(2).setCulpable(true);
		else if(OrganizadorEstados.getAsesino() == 3) //es la anciana
			asesinos.get(3).setCulpable(true);
		else if(OrganizadorEstados.getAsesino() == 4) //es la mujer
			asesinos.get(4).setCulpable(true);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
