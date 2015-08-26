package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos espejo.
 * @author Francisco Madueño Chulián
 */

public class Espejo extends Objeto{

	/**
	 * Constructor de la clase Espejo
	 * @param game
	 */
	public Espejo(MyGdxGame game) {
		super(game);
		textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSin/espejo.png"));
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonEspejo.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonEspejoActivado.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		texturaActualBoton = botonObjeto;
		combinables = null;
		tipoObjeto = this.getClass();
		identificador = Identificador.Espejo;
		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
