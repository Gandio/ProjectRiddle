package com.mygdx.game;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Esta clase representa cada línea del archivo de log.
 * @author Francisco Madueño Chulián
 *
 */

public class LineaLog {
	private String _iId_Usuario;
	private Date _dt;
	private int _iPuntos, _iTotales;
	private char _cAccion;
	private String _sHabitacion= "";
	private String _sObjeto= "";
	private String _sCombinable1= "";
	private String _sCombinable2 = "";
	private String _sTipoPista = "";
	private String _sPista = "";
	private String _sPersonaje = "";
	private boolean _b = false; // Activa o éxito
	private int _iAciertos= 0;
	
	private String _sHipotesis = "";
	private String _sTipoHipotesis = "";
	private String _sCulpable = "";
	private DateFormat _CdtFormat = new SimpleDateFormat("yyyyMMdd HHmmss");
	private String fecha;
	
	private String sLineaLog;

	/**
	 * Contructor de la clase, divide cada linea en varias partes usando el ; como referencia
	 * @param sLineaLog
	 */
	
	public LineaLog(String sLineaLog) {
		this.sLineaLog = sLineaLog;
		
		String [] asInfo = sLineaLog.split(";");
		_iId_Usuario = asInfo[0];
		fecha = asInfo[1];
		_iPuntos = Integer.parseInt(asInfo[2]);
		_iTotales = Integer.parseInt(asInfo[3]);
		_cAccion = asInfo[4].charAt(0);

		switch (_cAccion){
			case 'T':
				_sHabitacion = asInfo[5];
			break;

		case 'L': 
			_sHabitacion = asInfo[5];
			_b = asInfo[6].equals("1");
		break;

		case 'V': 
			_sPersonaje = asInfo[5];
			_sHabitacion = asInfo[6];
			_b = asInfo[7].equals("1");
		break;

		case'I': 
			_sPersonaje = asInfo[5];
			_sHabitacion = asInfo[6];
			_sObjeto = asInfo[7];
		break;

		case 'A': 
			_sObjeto = asInfo[5];
			_sHabitacion = asInfo[6];
			_b = asInfo[7].equals("1");
		break;

		case 'B': 
			_sHabitacion = asInfo[5];
			_sCombinable1 = asInfo[6];
			_sCombinable2 = asInfo[7];
			_sObjeto = asInfo[8];
		break;

		case 'F': 
			_sPersonaje = asInfo[5];
			_sHabitacion = asInfo[6];
			_sObjeto = asInfo[7];
		break;

		case 'J':
			_sPersonaje = asInfo[5];
			_sHabitacion = asInfo[6];
			_sObjeto = asInfo[7];
			_b = asInfo[8].equals("1");
		break;

		case 'P':
			_sPersonaje = asInfo[5];
			_sHabitacion = asInfo[6];
			_sTipoPista = asInfo[7];
			_sPista = asInfo[8];
		break;

		case 'H':
			_sTipoHipotesis = asInfo[5];
			_sHipotesis = asInfo[6];
			_sCulpable = asInfo[7];
			_b = asInfo[8].equals("1");
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
	
	public String getSLineaLog(){
		return sLineaLog;
	}
}
