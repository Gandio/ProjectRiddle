package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos llave.
 * @author Francisco Madueño Chulián
 */

public class Llave extends Objeto{

	/**
	 * Constructor de la clase Llave
	 * @param game
	 */
	
	public Llave(TheHouseOfCrimes game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonLlave.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonLlaveActivado.png"));
		texturaActualBoton = botonObjeto;
		combinables = null;
		identificador = Identificador.Llave;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinCon/llaveSinCon.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinSin/llaveSinSin.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
