package com.mygdx.game;

import com.badlogic.gdx.Input.TextInputListener;

public class EntradaTexto implements TextInputListener{

	@Override
	public void input(String usuario) {
		MyGdxGame.setUsuario(usuario);
	}

	@Override
	public void canceled() {
		// TODO Auto-generated method stub
		
	}
}
