package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos ataud.
 * @author Francisco Madueño Chulián
 */

public class Ataud extends Objeto{

	/**
	 * Constructor de la clase Ataud.
	 * @param game
	 */
	
	public Ataud(TheCrimeHouse game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonAtaud.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonAtaudActivado.png"));
		texturaActualBoton = botonObjeto;
		combinables = null;
		tipoObjeto = this.getClass();
		identificador = Identificador.Ataud;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConCon/ataud.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConSin/ataud.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}

