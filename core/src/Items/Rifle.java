package Items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Tools;

public class Rifle extends Objeto{

	public Rifle(MyGdxGame game) {
		super(game);
		textura = new Texture(Gdx.files.internal("Imagenes/Objetos/rifle.png"));
		botonObjeto = new Texture(Gdx.files.internal("Imagenes/BotonesObjeto/botonRifle.png"));
		coordenadas = new Vector2(Tools.centrarAncho(game, textura), Tools.centrarAlto(game, textura));
		combinables = null;
		seCoge(true);
		tipoObjeto = this.getClass();
		identificador = Identificador.Daga;
	}
}

