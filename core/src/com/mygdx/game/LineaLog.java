package com.mygdx.game;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineaLog {
	private int _iId_Usuario;
	private Date _dt;
	private int _iPuntos, _iTotales;
	private char _cAccion;
	private String _sHabitacion= "";
	private String _sObjeto= "";
	private String _sCombinable1= "";
	private String _sCombinable2 = "";
	private String _sTipoPista = "";
	private String _sPista = "";
	private boolean _b = false; // Activa o éxito
	private int _iAciertos= 0;
	private String _sHipotesisPersonaje = "";
	private String _sHipotesisHabitacion = "";
	private String _sHipotesisObjeto = "";
	private DateFormat _CdtFormat = new SimpleDateFormat("yyyyMMdd HHmmss");


	public LineaLog(String sLineaLog) {
		String [] asInfo = sLineaLog.split(";");
		_iId_Usuario = Integer.parseInt(asInfo[0]);
		try {
			_dt = _CdtFormat.parse(asInfo[1] + " " + asInfo[2]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_iPuntos = Integer.parseInt(asInfo[3]);
		_iTotales = Integer.parseInt(asInfo[4]);
		_cAccion = asInfo[5].charAt(1);

		switch (_cAccion){
			case 'T':
				_sHabitacion = asInfo[6];
			break;

		case 'L': 
			_sHabitacion = asInfo[6];
			_b = asInfo[7].equals("1");
		break;

		case 'V': 
			_sHabitacion = asInfo[7];
			_b = asInfo[8].equals("1");
		break;

		case'I': 
			_sHabitacion = asInfo[7];
			_sObjeto = asInfo[8];
		break;

		case 'A': 
			_sObjeto = asInfo[6];
			_sHabitacion = asInfo[7];
			_b = asInfo[8].equals("1");
		break;

		case 'B': 
			_sHabitacion = asInfo[6];
			_sCombinable1 = asInfo[7];
			_sCombinable2 = asInfo[8];
			_sObjeto = asInfo[9];
		break;

		case 'F': 
			_sHabitacion = asInfo[7];
			_sObjeto = asInfo[8];
		break;

		case 'J': 
			_sHabitacion = asInfo[7];
			_sObjeto = asInfo[8];
			_b = asInfo[9].equals("1");
		break;

		case 'P': 
			_sHabitacion = asInfo[7];
			_sTipoPista = asInfo[8];
			_sPista = asInfo[9];
		break;

		case 'H': 
			_sHipotesisPersonaje = asInfo[6];
			_sHipotesisObjeto = asInfo[7];
			_sHipotesisPersonaje = asInfo[8];
			_sObjeto = asInfo[10];
			_sHabitacion = asInfo[11];
			_iAciertos = Integer.parseInt(asInfo[12]);
		break;

		default: 
			try {
				throw new Exception("Acción " + _cAccion + "inválida.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
