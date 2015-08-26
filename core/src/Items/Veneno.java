package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos veneno.
 * @author Francisco Madueño Chulián
 */

public class Veneno extends Objeto{

	/**
	 * Constructor de la clase Veneno
	 * @param game
	 */
	
	public Veneno(MyGdxGame game) {
		super(game);
		textura = new Texture(Gdx.files.internal("Imagenes/Objetos/veneno.png"));
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonVeneno.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonVenenoActivado.png"));
		texturaActualBoton = botonObjeto;
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		combinables = null;
		tipoObjeto = this.getClass();
		identificador = Identificador.Veneno;
		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
