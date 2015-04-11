package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class BotellaVacia extends Objeto{

	public BotellaVacia(MyGdxGame game) {
		super(game);
		textura = new Texture(Gdx.files.internal("Imagenes/Objetos/botellaVacia.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		combinables = null;
		seCoge(true);
		esCombinable = true;
		tipoObjeto = this.getClass();
		identificador = Identificador.BotellaVacia;
	}
}