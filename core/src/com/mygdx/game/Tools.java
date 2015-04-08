package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;

public class Tools {
	public static float centrarAlto(MyGdxGame game, Texture textura){
		return game.HEIGHT / 2f - textura.getHeight() / 2f;
	}
	
	public static float centrarAncho(MyGdxGame game, Texture textura){
		return game.WIDTH / 2f - textura.getWidth() / 2f;
	}
}


