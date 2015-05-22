package Objetos;

import Botones.Boton;
import Botones.BotonFinConversacion;
import Botones.BotonSiguienteConversacion;
import Pantallas.Habitacion;
import Pantallas.Habitacion.Estado;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class CuadroTexto extends Actor{
	private MyGdxGame game;
	
	private BotonSiguienteConversacion siguienteConversacion;
	private BotonFinConversacion finConversacion;
	private Texture cuadroTexto;
	private Vector2 coordenadas;
	private Array<String> textos = new Array<String>();
	
	private BitmapFont font;
	private String texto;
	private int parteTexto = 0;
	private boolean finTexto = false;
	
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
		
		
		//Provisional, el texto no va asociado al cuadro de texto, se usa otro metodo para crear el texto
		texto = "hola mundo, espero que esto se adapte al cuadro de texto "
				+ "porque si no vamos a tener que usar "
				+ "otra cosa :3. Cosa que si que hace gracias algoritmo raro y desconocido que yo ya habia pensado "
				+ "pero que otra persona ya había implementado. Ahora me queda que esto salga en otra ventana y todos"
				+ " contentos :3. A ver este salto de linea raro si queda bien yo que se lo mismo si. Bueno ahora lo que"
				+ " queda es ver si las transiciones de texto funcionan, he hecho una prueba con dos y si, probemos para "
				+ "n pues que eso es más jodido.";
				
		
		
		//texto = "hola";
		
		font = new BitmapFont();
		
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
		/*usar un vector, cada celda será un string, cada 4 \n (numero de lineas) se crea una nueva celda,
		 * hasta que no se llegue a la última celda no se dibuja el boton de fin de conversacion
		 */
		batch.draw(cuadroTexto, getX(), getY());
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
	
	
	//Este algoritmo ajusta el texto al cuadro de texto forzando los saltos de líneas
	public static String wrapString(String string, int charWrap) {
	    int lastBreak = 0;
	    int nextBreak = charWrap;
	    if (string.length() > charWrap) {
	        String setString = "";
	        do {
	            while (string.charAt(nextBreak) != ' ' && nextBreak > lastBreak) {
	                nextBreak--;
	            }
	            if (nextBreak == lastBreak) {
	                nextBreak = lastBreak + charWrap;
	            }
	            setString += string.substring(lastBreak, nextBreak).trim() + "\n";
	            lastBreak = nextBreak;
	            nextBreak += charWrap;

	        } while (nextBreak < string.length());
	        setString += string.substring(lastBreak).trim();
	        return setString;
	    } else {
	        return string;
	    }
	}
	
	public Array<String> dividirTexto(String texto){
		Array<String> textos = new Array<String>();
		int contador = 0; // cuenta los /n que nos encontramos
		int liminf = 0;
		String aux;
		for(int i = 0; i < texto.length(); ++i){
			if(texto.charAt(i) == '\n') contador++;
			
			if(contador == 4){
				textos.add(texto.substring(liminf, i));
				contador = 0; 
				liminf = i+1;
				aux = "";
			}
		}
		
		aux = (String) texto.toString().subSequence(liminf, texto.length());
		textos.add(aux);
		
		return textos;
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
