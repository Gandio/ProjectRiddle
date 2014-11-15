package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame implements ApplicationListener {
	private SpriteBatch batch; //dibuja una imagen
	private Texture textura; //carga imagen
	private OrthographicCamera camara;
	private TextureRegion region; //trozo de imagen que queremos usar
	
	public void create() { 
		//estas variables las usaremos para ajustar la resolución a 800x480
		float anchura = 800;
		float altura = 480;
		
		//instanciamos la camara y la ajustamos a la resolución anterior 
		camara = new OrthographicCamera();
		camara.setToOrtho(false, anchura, altura);
		
		//instanciamos el spritebatch
		batch = new SpriteBatch();
		
		//instanciamos la textura
		textura = new Texture(Gdx.files.internal("data/Imagenes/mapa.png"));
		
		//instanciamos la región, (0,0) es el origen, esquina superior izquierda y altura y 
		//anchura el fin
		region = new TextureRegion(textura, 0, 0, anchura, altura);
	}
	
	public void render() {
		//limpiamos la pantalla aplicando un color, en este caso negro
		Gdx.gl.glClearColor(1, 1, 1, 1); //MIRAR
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //MIRAR
		
		//ajustamos la camara
		batch.setProjectionMatrix(camara.combined); //MIRAR
		
		//empezamos a dibujar
		batch.begin();
		//dibujamos la textura
		batch.draw(region, 0, 0);
		//terminamos de dibujar
		batch.end();
	}
	
	public void dispose() {}
	
	public void pause() {}
	
	public void resize(int width, int height) {}
	
	public void resume() {}
	
}
