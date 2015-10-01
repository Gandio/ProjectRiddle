package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos café.
 * @author Francisco Madueño Chulián
 */

public class Cafe extends Objeto{

	/**
	 * Constructor de la clase Cafe
	 * @param game
	 */
	public Cafe(TheCrimeHouse game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonCafe.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonCafeActivado.png"));
		texturaActualBoton = botonObjeto;
		
		combinables = new Array<Identificador>();
		combinables.add(Identificador.Azucar);
		
		tipoObjeto = this.getClass();
		identificador = Identificador.Cafe;
		id = 2;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinCon/cafe.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinSin/cafe.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
