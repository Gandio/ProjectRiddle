package com.mygdx.game;

import Pantallas.Pasillo;

import com.badlogic.gdx.Game;


public class MyGdxGame extends Game{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;

	public void create() {
		setScreen(new Pasillo(this));
	}
}
