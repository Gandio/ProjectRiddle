package Puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

import Objetos.CuadroTexto;

public class CuadroEleccion extends CuadroTexto{
	
	private boolean eleccionCorrecta;

	public CuadroEleccion(MyGdxGame game) {
		super(game);
		this.game = game;
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroEleccion.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
		
		texto = "";
		font = new BitmapFont();
	}
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(cuadroTexto, coordenadas.x, coordenadas.y);
		font.setScale(2.5f);
		font.setColor(Color.BLACK);
		//No hace falta cuadrar el texto, este texto solo va a consistir en un par de 
		//palabras
		font.drawMultiLine(batch, texto, coordenadas.x, coordenadas.y); //esto hay que ajustarlo
	}
	
	public void update(){}
	
	public void setEleccion(int i){
		if(i == 0) eleccionCorrecta = false;
		else eleccionCorrecta = true;
	}
	
	public boolean getEleccion(){
		return eleccionCorrecta;
	}
}
