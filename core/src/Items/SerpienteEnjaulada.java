package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos serpiente enjaulada.
 * @author Francisco Madueño Chulián
 */

public class SerpienteEnjaulada extends Objeto{

	/**
	 * Constructor de la clase SerpienteEnjaulada
	 * @param game
	 */
	
	public SerpienteEnjaulada(TheHouseOfCrimes game) {
		super(game);
		textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConCon/serpienteConCon.png"));
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonSerpienteEnjaulada.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonSerpienteEnjauladaActivado.png"));
		texturaActualBoton = botonObjeto;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		combinables = null;
		identificador = Identificador.SerpienteEnjaulada;
		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}