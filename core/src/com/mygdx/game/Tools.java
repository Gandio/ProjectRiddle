package com.mygdx.game;


import Items.CafeAzucar;
import Items.LibroPintado;
import Items.Objeto;
import Items.SerpienteEnjaulada;

import com.badlogic.gdx.graphics.Texture;

public class Tools {
	public static float centrarAlto(MyGdxGame game, Texture textura){
		return game.HEIGHT / 2f - textura.getHeight() / 2f;
	}
	
	public static float centrarAncho(MyGdxGame game, Texture textura){
		return game.WIDTH / 2f - textura.getWidth() / 2f;
	}
	
	public static Objeto devolverCombinacion(MyGdxGame game, int i, int j){
		if((i == 0 && j == 2) || (i == 2 && j == 0)) return new CafeAzucar(game);
		else if((i == 1 && j == 4) || (i == 4 && j == 1)) return new LibroPintado(game);
		else if((i == 3 && j == 5) || (i == 5 && j == 3)) return new SerpienteEnjaulada(game);
		else return null;
	}
}