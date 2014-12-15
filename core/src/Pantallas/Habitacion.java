package Pantallas;

import Controladores.ControladorBotonConversacion;
import Controladores.ControladorBotonInvestigar;
import Controladores.ControladorBotonPuerta;
import Controladores.ControladorBotonPuertaHabitacion;
import Objetos.Boton;
import Objetos.BotonConversacion;
import Objetos.BotonInvestigar;
import Objetos.BotonPuerta;
import Objetos.Personaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
public abstract class Habitacion extends Pantalla implements Screen{
	
	//Actores
	private Personaje personaje;
	protected Boton botonInvestigar;
	protected Boton botonConversacion;
	
	//Controladores
	protected ControladorBotonConversacion controladorConversacion;
	protected ControladorBotonInvestigar controladorInvestigar;
	
	public Habitacion(MyGdxGame game) {
		super(game);

		//Actores
		botonConversacion = new BotonConversacion(game);
		botonInvestigar = new BotonInvestigar(game);
		
		//Camaras
		camara = new OrthographicCamera();
		camara.setToOrtho(false, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		Gdx.input.setInputProcessor(stage);
		
		//Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		
		//Añadimos controladores
		controladorConversacion = new ControladorBotonConversacion(this, game);
		controladorInvestigar = new ControladorBotonInvestigar(this, game);
		controladorBotonPuerta = new ControladorBotonPuertaHabitacion(this, game);
		
		//Asignamos controladores a otros
		controladorConversacion.asignarControladorInvestigar(controladorInvestigar);
		controladorInvestigar.asignarControladorConversacion(controladorConversacion);
	}
	
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
}