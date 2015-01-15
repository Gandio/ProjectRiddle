package Controladores;

import Objetos.Personaje;
import Pantallas.Habitacion;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.MyGdxGame;

public class ControladorBotonInvestigar {
	private MyGdxGame game;
	private Habitacion habitacion;
	private ControladorBotonConversacion controladorConversacion;
	private Personaje personaje;
	private boolean investigar;
	
	public ControladorBotonInvestigar(Habitacion h, MyGdxGame game) {
		this.game = game;
		habitacion = h;
		investigar = false; //por defecto el modo investigar está desactivado
		personaje = habitacion.getPersonaje();
		
		
	}
	
	public void asignarControladorConversacion(ControladorBotonConversacion c){
		controladorConversacion = c;
	}
	
	public void update(){
		if(Gdx.input.isKeyJustPressed(Keys.I)){
			if(!controladorConversacion.conversacionActiva() && !investigacionActiva()){
				personaje.setCoordenadas(personaje.coordenadaX(), -400);
				System.out.println("Paso al modo investigación");
				activarInvestigacion();
			}else if(!controladorConversacion.conversacionActiva() && investigacionActiva()){
				System.out.println("Paso al modo conversacion");
				personaje.setCoordenadas(personaje.coordenadaX(), 0);
				desactivarInvestigacion();
			}else{
				System.out.println("Debes de activar el modo investigacion");
			}
		}
	}

	public boolean investigacionActiva() {
		return investigar;
	}
	
	public void activarInvestigacion(){
		investigar = true;
	}
	
	public void desactivarInvestigacion(){
		investigar = false;
	}
}