package Pantallas;

import Controladores.ControladorBotonConversacion;
import Controladores.ControladorBotonInvestigar;
import Controladores.ControladorBotonPuerta;
import Controladores.ControladorBotonPuertaHabitacion;
import Objetos.Boton;
import Objetos.BotonConversacion;
import Objetos.BotonInvestigar;
import Objetos.BotonPuertaPasillo;
import Objetos.Personaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase abstracta generaliza todas las habitaciones del juego.
 * @author Francisco Madueño Chulián
 */
public abstract class Habitacion extends Pantalla{
	
	//Actores
	protected Personaje personaje;
	protected Boton botonInvestigar;
	protected Boton botonConversacion;
	private Boton botonPuerta; //permite entrar en una habitación
	
	//Controladores
	protected ControladorBotonConversacion controladorConversacion;
	protected ControladorBotonInvestigar controladorInvestigar;
	
	public Habitacion(MyGdxGame game) {
		super(game);
		
		//Camaras
		camara.setToOrtho(false, 1280, 720);
		viewport = new FillViewport(1280, 720, camara);
		Gdx.input.setInputProcessor(stage);
		
		//Actores
		botonConversacion = new BotonConversacion(game);
		botonInvestigar = new BotonInvestigar(game);
		//botonPuerta = new BotonPuertaPasillo(game);
		
		//Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		stage.addActor(botonPuerta);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

		batch.begin();
		batch.draw(pantalla, 0, 0, pantalla.getWidth(), pantalla.getHeight());
		batch.end();
		
		//Posicion de botones
		botonPuerta.setCoordenadas(200, 200);
		botonInvestigar.setCoordenadas(250, 200);
		botonConversacion.setCoordenadas(300, 200);
		
		controladorBotonPuerta.update();
		controladorConversacion.update();
		controladorInvestigar.update();
		
		stage.act(Gdx.graphics.getDeltaTime());
		//stage.draw();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {}
	
	/*----------------------------------------------------------------
	 * -----------------------FUNCIONES AUXILIARES--------------------
	 * ---------------------------------------------------------------
	 */
	
	public Boton getBotonConversacion(){
		return botonConversacion;
	}
	
	public Boton getBotonInvestigar(){
		return botonInvestigar;
	}
	
	public ControladorBotonInvestigar getControladorInvestigar(){
		return controladorInvestigar;
	}
	
	public ControladorBotonConversacion getControladorConversacion(){
		return controladorConversacion;
	}
	
	public Personaje getPersonaje(){
		return personaje;
	}
}