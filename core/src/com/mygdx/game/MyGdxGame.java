package com.mygdx.game;

import Pantallas.Atico;
import Pantallas.Baño;
import Pantallas.Biblioteca;
import Pantallas.Cocina;
import Pantallas.Dormitorio;
import Pantallas.Estudio;
import Pantallas.Habitacion;
import Pantallas.Inicio;
import Pantallas.Pasillo;
import Pantallas.Salon;
import Pantallas.Sotano;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game{
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 800;
	public static final boolean SUSPENSE = false;
	
	public static final boolean SUSPENSE_AMBIENTE = false;
	public static final boolean SUSPENSE_MUSICA = false;
	public static final boolean SUSPENSE_OBJETOS = false;
	
	//Habitaciones
	public static Pasillo pasillo;
	public void create() {
		setScreen(new Inicio(this));
	}
}