package Pantallas;

import java.util.Iterator;

import Items.Bombilla;
import Items.Llave;
import Items.Objeto;
import Items.Pistola;
import Items.Rifle;
import Items.Tabaco;
import Objetos.Cursor;
import Personajes.Anciana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public final class Salon extends Habitacion {
	//Objetos con suspense
	private static Objeto pistola = new Pistola(game);
	private static Objeto rifle = new Rifle(game);
	private static Objeto tabaco = new Tabaco(game);
	
	//Objetos sin suspense
	private static Objeto llave = new Llave(game);
	private static Objeto bombilla = new Bombilla(game);
	
	private static Salon unicaInstancia;
	
	private Salon(MyGdxGame game, Cursor c) {
		super(Pasillo.game, c);
		objetos = new Array<Objeto>();
		
		//Objetos
		Iterator<Objeto> iter = objetos.iterator();
		
		//Añadimos objetos a la habitacion
		if(MyGdxGame.SUSPENSE){
			objetos.add(pistola);
			objetos.add(rifle);
			objetos.add(tabaco);
			
			pistola.setCoordenadas(1120, 15);
			rifle.setCoordenadas(350, 10);
			tabaco.setCoordenadas(500, 240);
			
		}else{
			objetos.add(llave);
			objetos.add(bombilla);
			
			llave.setCoordenadas(500, 220);
			bombilla.setCoordenadas(50, 250);
		}
		
		while(iter.hasNext()){
			iter.next().setTouchable(Touchable.enabled);
		}
		
		iter = objetos.iterator();
		
		while(iter.hasNext()){
			stage.addActor(iter.next());
		}
		
		//Actores
		personaje = Anciana.getInstancia();
		personaje.setCoordenadas(450, 0);
		
		//añadimos los actores
		stage.addActor(personaje);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);

		//si vamos a conversar con el personaje se debe mostrar el cuadro de texto
		if(personaje != null && estado == Estado.CONVERSAR){
			
		}
		
		Iterator<Objeto> iterObjetos = objetos.iterator();
		while(iterObjetos.hasNext()){
			iterObjetos.next().seSelecciona();
		}
		
		Gdx.input.setInputProcessor(stage);
		stage.draw();
	}

	@Override
	public void show() {
		if(MyGdxGame.SUSPENSE){
			pantalla = new Texture(Gdx.files.internal("Imagenes/Escenarios/salon.png"));
		}else{
			pantalla = new Texture(Gdx.files.internal("Imagenes/EscenariosSinSuspense/salonSin.png"));
		}
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public static Salon getInstancia(){
		if(unicaInstancia == null){
			unicaInstancia = new Salon(game, c);
		}
		
		return unicaInstancia;
	}
}
