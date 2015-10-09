package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos basura.
 * @author Francisco Madueño Chulián
 */

public class Basura extends Objeto{
	
	/**
	 * Constructor de la clase Basura.
	 * @param game
	 */

	public Basura(TheHouseOfCrimes game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonPapelera.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonPapeleraActivado.png"));
		texturaActualBoton = botonObjeto;
		combinables = null;
		identificador = Identificador.Basura;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConCon/basuraConCon.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConSin/basuraConSin.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}

