package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheHouseOfCrimes;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos pistola.
 * @author Francisco Madueño Chulián
 */

public class Pistola extends Objeto{
	
	/**
	 * Constructor de la clase Pistola
	 * @param game
	 */

	public Pistola(TheHouseOfCrimes game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonRevolver.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonRevolverActivado.png"));
		texturaActualBoton = botonObjeto;
		combinables = null;
		identificador = Identificador.Pistola;
		
		if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConCon/pistolaConCon.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConSin/pistolaConSin.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}