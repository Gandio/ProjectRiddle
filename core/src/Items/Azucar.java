package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos azucar.
 * @author Francisco Madueño Chulián
 */

public class Azucar extends Objeto{

	/**
	 * Constructor de la clase azucar
	 * @param game
	 */
	
	public Azucar(MyGdxGame game) {
		super(game);
		textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSin/azucar.png"));
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonAzucar.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonAzucarActivado.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		texturaActualBoton = botonObjeto;
		
		combinables = new Array<Identificador>();
		combinables.add(Identificador.Cafe);
		
		seCoge(true);
		tipoObjeto = this.getClass();
		identificador = Identificador.Azucar;
		id = 0;
		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
