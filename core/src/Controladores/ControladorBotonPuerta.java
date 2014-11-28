package Controladores;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Objetos.BotonPuerta;
import Objetos.Cursor;
import Pantallas.Pasillo;

public class ControladorBotonPuerta {
	private Pasillo pasillo;
	private BotonPuerta boton;
	private Cursor cursor;
	private Array<Rectangle> puertas;
	private Iterator<Rectangle> iRect;
	private boolean colisionPuerta;
	
	public ControladorBotonPuerta(Pasillo p) {
		pasillo = p;
		cursor = pasillo.getCursor();
		boton = pasillo.getBotonPuerta();
		puertas = pasillo.getPuertas();
	}
	
	public void update(){
		if(colisionaPuerta()){
			boton.activar();
			if(Gdx.input.isKeyJustPressed(Keys.P)){
				System.out.println("has pulsado la p");
			}
		}else{
			boton.desactivar();
		}
		
		colisionPuerta = false;
	}
	
	public boolean colisionaPuerta(){
		int i = 0;
		iRect = puertas.iterator();
		Rectangle rectanguloAux;
		
		while(i < puertas.size && !colisionPuerta){
			rectanguloAux = iRect.next();
			if(cursor.getLimites().overlaps(rectanguloAux)){
				colisionPuerta = true;
			}
			
			++i;
		}
		
		return colisionPuerta;
	}
}
