package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class CuadroTexto {
	BitmapFont cuadroTexto;
	Texture cuadro;
	CharSequence texto;
	
	public CuadroTexto(CharSequence texto) {
		this.texto = texto;
	}
	
	public void update(){} //logica del cuadro de texto
	
	public CharSequence getTexto(){
		return texto;
	}
	
	public void setTexto(CharSequence texto){
		this.texto = texto;
	}

}
