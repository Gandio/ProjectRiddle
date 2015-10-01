package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.mygdx.game.TheCrimeHouse;
import com.mygdx.game.Tools;

/**
 * Esta clase representa las características de los objetos jaula.
 * @author Francisco Madueño Chulián
 */

public class Jaula extends Objeto{

	/**
	 * Constructor de la clase Jaula
	 * @param game
	 */
	
	public Jaula(TheCrimeHouse game) {
		super(game);
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonJaula.png"));
		botonObjetoActivado = new Texture(Gdx.files.internal("Imagenes/BotonesObjetoActivado/botonJaulaActivado.png"));
		texturaActualBoton = botonObjeto;
		
		combinables = new Array<Identificador>();
		combinables.add(Identificador.Serpiente);
		
		tipoObjeto = this.getClass();
		identificador = Identificador.Jaula;
		id = 3;
		
		if(TheCrimeHouse.SUSPENSE_AMBIENTE)
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConCon/jaula.png"));
		else
			textura = new Texture(Gdx.files.internal("Imagenes/ObjetosConSin/jaula.png"));
		
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));

		
		//Descripción del objeto
		for (Element child : objetos){	
			if(identificador.name().equals(child.getAttribute("nombre")))
				descripcionObjeto = child.getChildByName("descripcion").getAttribute("texto");
		}
	}
}

