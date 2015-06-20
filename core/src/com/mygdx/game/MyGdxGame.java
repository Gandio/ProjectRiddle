package com.mygdx.game;

import Pantallas.Inicio;
import Pantallas.Pasillo;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	public static final boolean SUSPENSE = false;
	
	public static final boolean SUSPENSE_AMBIENTE = false;
	public static final boolean SUSPENSE_MUSICA = false;
	public static final boolean SUSPENSE_OBJETOS = false;
	
	public void create() {
		setScreen(new Inicio(this));
	}
}