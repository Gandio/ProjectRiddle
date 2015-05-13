package com.mygdx.game;


import Items.Objeto;

import com.badlogic.gdx.graphics.Texture;

public class Tools {
	private int nObjetos = 2;
	private boolean matrizObjetos[][] = new boolean[nObjetos][nObjetos];
	
	public Tools(){
		//inicializamos la matriz de booleanos
	}
	
	public static float centrarAlto(MyGdxGame game, Texture textura){
		return game.HEIGHT / 2f - textura.getHeight() / 2f;
	}
	
	public static float centrarAncho(MyGdxGame game, Texture textura){
		return game.WIDTH / 2f - textura.getWidth() / 2f;
	}
	
	public boolean esPosibleCombinar(Objeto o1, Objeto o2){
		int x = 0, y = 0;
		return matrizObjetos[x][y];
	}
}