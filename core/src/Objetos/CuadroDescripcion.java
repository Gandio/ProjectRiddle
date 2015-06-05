package Objetos;

import Pantallas.Habitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class CuadroDescripcion extends CuadroTexto{
	private static MyGdxGame game;
	
	public CuadroDescripcion(MyGdxGame game) {
		super(game);
		this.game = game;
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroDescripcion.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
		
		texto = "es un objeto";
		font = new BitmapFont();
	}
	
	public void draw(Batch batch, float parentAlpha){
		batch.draw(cuadroTexto, getX(), getY());
		font.setScale(3f);
		String textoConLimites = wrapString(texto, 50);
		
		font.drawMultiLine(batch, textoConLimites, 30, 260);
	}
	
	public void setTexto(String t){
		texto = t;
	}
}
