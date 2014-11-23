package Controladores;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Objetos.Cursor;
import Pantallas.Pasillo;



public class ColisionCursor {
	Pasillo pasillo;
	Array<Rectangle> puertas;
	Array<Rectangle> paredes;
	Cursor cursor;
	
	public ColisionCursor(Pasillo p){
		pasillo = p;
		puertas = pasillo.getPuertas();
		paredes = pasillo.getParedes();
		cursor = pasillo.getCursor();
	}
	
	public void update(){
		
	}
}
