package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos anillo.
 * @author Francisco Madueño Chulián
 */

public class Anillo extends Objeto{

	/**
	 * Constructor de la clase Anillo.
	 * @param game
	 */
	
	public Anillo(TheCrimeHouse game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonAnillo.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonAnilloActivado.png"));
		texturaActualBoton = botonObjeto;
		tipoObjeto = this.getClass();
		identificador = Identificador.Anillo;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinCon/anillo.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinSin/anillo.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
