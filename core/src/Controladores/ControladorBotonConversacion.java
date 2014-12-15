package Controladores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.game.MyGdxGame;

import Pantallas.Habitacion;

public class ControladorBotonConversacion {
	private MyGdxGame game;
	private Habitacion habitacion;
	private boolean conversando; //lo usamos para saber si estoy en medio de una conversacion
	private ControladorBotonInvestigar controladorInvestigar;
	
	public ControladorBotonConversacion(Habitacion h, MyGdxGame game) {
		this.game = game;
		habitacion = h;
		conversando = false; //por defecto está activado, hay un personaje en pantalla
	}
	
	public void asignarControladorInvestigar(ControladorBotonInvestigar c){
		controladorInvestigar = c;
	}
	
	public void update(){
		if(Gdx.input.isKeyJustPressed(Keys.H)){
			if(!controladorInvestigar.investigacionActiva()){
				iniciarConversacion();
				System.out.println("Estoy conversando");
				terminarConversacion();
			}else{
			System.out.println("Debo salir del modo investigacion");
			}
		}
	}
	
	public boolean conversacionActiva(){
		return conversando;
	}
	
	public void iniciarConversacion(){
		conversando = true;
	}
	
	public void terminarConversacion(){
		conversando = false;
	}
}