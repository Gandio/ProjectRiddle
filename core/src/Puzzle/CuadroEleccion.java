package Puzzle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

import Objetos.CuadroTexto;

public class CuadroEleccion extends CuadroTexto{

	public CuadroEleccion(MyGdxGame game) {
		super(game);
		this.game = game;
		
		//cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroDescripcion.png"));
		//coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
		
		texto = "";
		font = new BitmapFont();
	}
	
	public void draw(Batch batch, float parentAlpha){}
	
	public void update(){}

}
