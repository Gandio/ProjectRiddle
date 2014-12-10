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

public abstract class Habitacion extends Pantalla implements Screen{
	
	//Actores
	private Personaje personaje;
	private Boton botonInvestigar;
	private Boton botonConversacion;
	private Pasillo pasillo;
	
	//Controladores
	private ControladorBotonConversacion controladorConversacion;
	private ControladorBotonInvestigar controladorInvestigar;
	
	public Habitacion(MyGdxGame game, Pasillo pasillo) {
		super(game);
		
		//Mantenemos la referencia del pasillo
		this.pasillo = pasillo;

		//Actores
		botonConversacion = new BotonConversacion(game);
		botonInvestigar = new BotonInvestigar(game);
		
		//Camaras
		camara = new OrthographicCamera();
		camara.position.set(MyGdxGame.WIDTH/2f, MyGdxGame.HEIGHT/2f, 0);
		viewport = new FillViewport(MyGdxGame.WIDTH, MyGdxGame.HEIGHT, camara);
		Gdx.input.setInputProcessor(stage);
		
		//Añadimos actores
		stage.addActor(botonConversacion);
		stage.addActor(botonInvestigar);
		
		//Añadimos controladores
		controladorBotonPuerta = new ControladorBotonPuertaHabitacion(this, game);
	}
}
