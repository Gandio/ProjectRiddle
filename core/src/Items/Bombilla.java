package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos bombilla.
 * @author Francisco Madueño Chulián
 */

public class Bombilla extends Objeto{

	/**
	 * Constructor de la clase Bombilla
	 * @param game
	 */
	public Bombilla(TheHouseOfCrimes game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonBombilla.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonBombillaActivado.png"));
		texturaActualBoton = botonObjeto;
		combinables = null;
		identificador = Identificador.Bombilla;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinCon/bombillaSinCon.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinSin/bombillaSinSin.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
