package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos bolígrafo.
 * @author Francisco Madueño Chulián
 */

public class Boligrafo extends Objeto{

	/**
	 * Constructor de la clase Bolígrafo
	 * @param game
	 */
	
	public Boligrafo(TheCrimeHouse game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonBoligrafo.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonBoligrafoActivado.png"));
		texturaActualBoton = botonObjeto;
		
		combinables = new Array<Identificador>();
		combinables.add(Identificador.Libro);
		
		tipoObjeto = this.getClass();
		identificador = Identificador.Boligrafo;
		id = 1;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinCon/boligrafo.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosSinSin/boligrafo.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}
