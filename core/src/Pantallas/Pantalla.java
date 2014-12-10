package Pantallas;

import Controladores.ControladorBotonPuerta;
import Objetos.Boton;
import Objetos.BotonPuerta;
import Objetos.Cursor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.mygdx.game.MyGdxGame;

/**
 * Esta clase se usa para generalizar las pantallas y que no haga falta diferenciar entre
 * pasillo y habitación.
 * @author Francisco Madueño Chulián
 *
 */

public abstract class Pantalla{
	//Juego
	protected static MyGdxGame game;
	protected Stage stage;
	protected Music musica;
	protected Boton botonPuerta; //permite entrar en una habitación
	protected Texture pantalla;
	
	//Camaras
	protected OrthographicCamera camara;
	public SpriteBatch batch;
	protected FillViewport viewport; //se usa para adaptar la pantalla
	private float altura = Gdx.graphics.getHeight();
	private float anchura = Gdx.graphics.getWidth();
	protected float proporcionAlto = altura/800;
	protected float proporcionAncho = anchura/480;
	
	//Controladores
	protected ControladorBotonPuerta controladorBotonPuerta;
	
	public Pantalla(MyGdxGame game) {
		stage = new Stage(new FillViewport(proporcionAncho, proporcionAlto));
		this.game = game;
		
		botonPuerta = new BotonPuerta(game);
		
		stage.addActor(botonPuerta);
	}
}