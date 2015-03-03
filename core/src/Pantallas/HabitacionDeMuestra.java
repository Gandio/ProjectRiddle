package Pantallas;

import Objetos.Dummie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

public class HabitacionDeMuestra extends Habitacion{
	
	private MyGdxGame game;
	
	public HabitacionDeMuestra(MyGdxGame game) {
		super(game);
		this.game = game;
		
		//Actores
		personaje = new Dummie(game);
		personaje.setCoordenadas(300, 0);
		
		//Añadimos controladores
		//controladorConversacion = new ControladorBotonConversacion(this, game);
		//controladorInvestigar = new ControladorBotonInvestigar(this, game);
		//controladorBotonPuerta = new ControladorBotonPuertaHabitacion(this, game);
		
		//Asignamos controladores a otros
		//controladorConversacion.asignarControladorInvestigar(controladorInvestigar);
		//controladorInvestigar.asignarControladorConversacion(controladorConversacion);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		stage.addActor(personaje);
		
		stage.draw();
	}

	@Override
	public void show() {
		pantalla = new Texture(Gdx.files.internal("Imagenes/plantillaHabitacion.png"));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
}