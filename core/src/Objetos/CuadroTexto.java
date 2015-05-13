package Objetos;

import Botones.Boton;
import Botones.BotonFinConversacion;
import Botones.BotonSiguienteConversacion;
import Pantallas.Habitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class CuadroTexto extends Actor{
	private MyGdxGame game;
	
	private BotonSiguienteConversacion siguienteConversacion;
	private BotonFinConversacion finConversacion;
	private Texture cuadroTexto;
	private Vector2 coordenadas;
	
	//Un archivo con las conversaciones del personaje que se encuentre en la habitacion
	
	public CuadroTexto(MyGdxGame game){
		this.game = game;
		siguienteConversacion = new BotonSiguienteConversacion(game);
		finConversacion = new BotonFinConversacion(game);
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroTexto.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
	
		siguienteConversacion.setCoordenadas(1180, 20);
		finConversacion.setCoordenadas(1190, 30);
		
		siguienteConversacion.setTouchable(Touchable.enabled);
		finConversacion.setTouchable(Touchable.enabled);
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	public void update(){
		siguienteConversacion.esPulsado();
		finConversacion.esPulsado();
	}
	
	public void draw(Batch batch, float parentAlpha){  
		batch.draw(cuadroTexto, getX(), getY());
	}
	
	public void iniciarConversacion(Stage s){
		s.addActor(siguienteConversacion);
	}
	
	public void finConversacion(){
		finConversacion.remove();
	}
	
	public Boton getFinConv(){
		return finConversacion;
	}
	
	public Boton getSigConv(){
		return siguienteConversacion;
	}
}
