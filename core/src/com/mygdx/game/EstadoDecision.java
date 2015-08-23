package com.mygdx.game;

public class EstadoDecision extends Estado{
	
	private String textoOpcion1;
	private String textoOpcion2;
	private String textoOpcion3;
	private String textoOpcion4;

	private String opcion1;
	private String opcion2;
	private String opcion3;
	private String opcion4;
	
	private int eleccionCorrecta = -1; // tri estado -1 no lo has intentado todavia, 0 has fallado, 1 correcto
	
	protected String error;

	public EstadoDecision(int numEstado, String pista) {
		super(numEstado, pista);
		
		textoOpcion1 = puzzle.getChildByName("opcion1").getAttribute("texto");
		textoOpcion2 = puzzle.getChildByName("opcion2").getAttribute("texto");
		textoOpcion3 = puzzle.getChildByName("opcion3").getAttribute("texto");
		textoOpcion4 = puzzle.getChildByName("opcion4").getAttribute("texto");

		opcion1 = puzzle.getChildByName("opcion1").getAttribute("correcto");
		opcion2 = puzzle.getChildByName("opcion2").getAttribute("correcto");
		opcion3 = puzzle.getChildByName("opcion3").getAttribute("correcto");
		opcion4 = puzzle.getChildByName("opcion4").getAttribute("correcto");
		
		error = puzzle.getChildByName("fallo").getAttribute("texto");
		prePuzzle = puzzle.getChildByName("prePuzzle").getAttribute("texto");
	}
	
	public String getTextoEleccion(int i) {
		if (i == 1)
			return textoOpcion1;
		else if (i == 2)
			return textoOpcion2;
		else if (i == 3)
			return textoOpcion3;
		else
			return textoOpcion4;
	}

	public String getEleccion(int i) {
		if (i == 1)
			return opcion1;
		else if (i == 2)
			return opcion2;
		else if (i == 3)
			return opcion3;
		else
			return opcion4;
	}
	
	public void eleccionCorrecta(int i) {
		eleccionCorrecta = i;
	}

	public int getEleccionCorrecta() {
		return eleccionCorrecta;
	}
	
	public String getError(){
		return error;
	}
}