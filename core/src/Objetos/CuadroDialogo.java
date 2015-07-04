package Objetos;

import Botones.Boton;
import Botones.BotonFinConversacion;
import Botones.BotonSiguienteConversacion;
import Pantallas.Habitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;
public class CuadroDialogo extends CuadroTexto{
	
	private static MyGdxGame game;
	private BotonSiguienteConversacion siguienteConversacion;
	private BotonFinConversacion finConversacion;
	
	private Array<String> textos = new Array<String>();
	
	
	private int parteTexto = 0;
	private boolean finTexto = false;
	
	public CuadroDialogo(MyGdxGame game) {
		super(game);
		
		this.game = game;
		siguienteConversacion = new BotonSiguienteConversacion(game);
		finConversacion = new BotonFinConversacion(game);
		
		cuadroTexto = new Texture(Gdx.files.internal("Imagenes/cuadroTexto.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, cuadroTexto), Tools.centrarAlto(game, cuadroTexto));
	
		siguienteConversacion.setCoordenadas(1180, 20);
		finConversacion.setCoordenadas(1190, 30);
		
		siguienteConversacion.setTouchable(Touchable.enabled);
		finConversacion.setTouchable(Touchable.enabled);
		
		
		//Texto por defecto.
		texto = "Ich habe momentan keine Information für dich!";
				
		
		
		//texto = "hola";
		
		font = new BitmapFont();
		
	}
	
	public void update(){
		
		siguienteConversacion.esPulsado();
		finConversacion.esPulsado();
	}
	
	public void draw(Batch batch, float parentAlpha){
		/*usar un vector, cada celda será un string, cada 4 \n (numero de lineas) se crea una nueva celda,
		 * hasta que no se llegue a la última celda no se dibuja el boton de fin de conversacion
		 */
		batch.draw(cuadroTexto, coordenadas.x, coordenadas.y);
		font.setScale(3f);
		String textoConLimites = wrapString(texto, 50);
		textos = dividirTexto(textoConLimites);
		
		if(textos.size == 1){
			siguienteConversacion.remove();
			((Habitacion) game.getScreen()).getStage().addActor(finConversacion);
		}
		else if(parteTexto == textos.size-2){
			finTexto = true;
		}
		
		font.drawMultiLine(batch, textos.get(parteTexto), 30, 260);
	}
	
	public void iniciarConversacion(Stage s){
		s.addActor(siguienteConversacion);
	}
	
	public void finConversacion(){
		parteTexto = 0;
		finTexto = false;
		finConversacion.remove();
	}
	
	public Boton getFinConv(){
		return finConversacion;
	}
	
	public Boton getSigConv(){
		return siguienteConversacion;
	}
	
	public void setTexto(String t){
		texto = t;
	}
	
	public int getParteTexto(){
		return parteTexto;
	}
	
	public void sigParteTexto(){
		parteTexto++;;
	}
	
	public boolean getFinTexto(){
		return finTexto;
	}

}
