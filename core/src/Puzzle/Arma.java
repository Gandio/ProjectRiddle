package Puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import Objetos.Puntuacion;
import Pantallas.Inicio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class Arma extends Actor{
	private boolean armaUsada = false;
	private Texture textura;
	private boolean pulsado = false;
	private Vector2 coordenadas;
	private static MyGdxGame game = Inicio.game;
	private Sound error;
	
	public Arma(Texture t){
		textura = t;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		
		error = Gdx.audio.newSound(Gdx.files.internal("Sonido/Error.wav"));
	}
	
	public void update(){
		setBounds(coordenadas.x, coordenadas.y, textura.getWidth(), textura.getHeight());
		
		addListener(new InputListener(){
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                ((Arma)event.getTarget()).pulsado = true;
                return true;
            }
		});
		
		if(pulsado){
			if(armaUsada){ //Se acaba el juego
				File prueba = new File("prueba.txt");
				PrintWriter writer;
				try {
					if(!prueba.exists()){
						try {
							prueba.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
				try {
					writer = new PrintWriter(new FileWriter(prueba, true));
					writer.println("Puntuacion final: " + Puntuacion.getPuntos() +  " --- " +
									"Fallos totales: " + Puntuacion.getError());
					writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} finally{}
				
				Gdx.app.exit();
			}else{
				Puntuacion.sumarError();
				error.play();
			}
		}
		
		pulsado = false;
	}
	
	public boolean esArmaUsada(){
		return armaUsada;
	}
	
	public void setUsada(boolean b){
		armaUsada = b;
	}
	
	public void setCoordenadas(float x, float y){
		coordenadas.x = x;
		coordenadas.y = y;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(textura, coordenadas.x, coordenadas.y);
	}
	
	/**
	 * Devuelve la coordenada x del asesino
	 * @return x
	 */
	
	public float coordenadaX(){
		return coordenadas.x;
	}
	
	
	/**
	 * Devuelve la coordenada y del asesino
	 * @return y
	 */
	public float coordenadaY(){
		return coordenadas.y;
	}
}