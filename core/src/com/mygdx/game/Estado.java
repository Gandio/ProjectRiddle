package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class Estado {
	private String habitacionInicio;
	private String habitacionDestino;
	private String objeto;
	private String personaje;
	private String textoPersonaje;
	private String pistaPersonaje;
	private String objetivo;
	
	protected XmlReader reader = new XmlReader();
	protected Element raiz;
	protected Array<Element> estados;
	
	public Estado(int numEstado){
		try{
			raiz = reader.parse(Gdx.files.internal("xml/logicaAlemanSuspense.xml"));
		}catch(IOException e){}
		
		estados = raiz.getChildrenByName("estado");
	}

}
