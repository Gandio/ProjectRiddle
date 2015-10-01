package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos caramelo.
 * @author Francisco Madueño Chulián
 */

public class Caramelo extends Objeto{

	/**
	 * Constructor de la clase Caramelo
	 * @param game
	 */
	public Caramelo(TheCrimeHouse game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonCaramelo.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonCarameloActivado.png"));
		texturaActualBoton = botonObjeto;
		combinables = null;
		tipoObjeto = this.getClass();
		identificador = Identificador.Caramelo;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinCon/caramelo.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinSin/caramelo.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}