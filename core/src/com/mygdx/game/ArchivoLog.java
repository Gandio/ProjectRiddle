package com.mygdx.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import com.badlogic.gdx.utils.Array;

import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;

import com.dropbox.core.DbxWriteMode;

/**
 * Esta clase representa el fichero de log que se va actualizado durante toda la partida.
 * Le provee de métodos necesarios para su actualización y subida a un servidor web.
 * @author Francisco Madueño Chulián
 *
 */
public class ArchivoLog {
	private Array<LineaLog> lineas;
	private PrintWriter writer;
	private File archivoLog = new File("Log.txt");
	
	/**
	 * Constructor de la clase.
	 */
	public ArchivoLog(){
		lineas = new Array<LineaLog>();
		
		//Si no existe lo creamos, si ya existe limpiamos el contenido y volvemos a escribir
		
		try {
			if(!archivoLog.exists()){
				try {
					archivoLog.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				try {
					writer = new PrintWriter(new FileWriter(archivoLog));
					writer.print("");
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				writer = new PrintWriter(new FileWriter(archivoLog, true));
				writer.println("############################################################");
				writer.println("#######FICHERO DE LOG DE PARTIDA, PUEDES BORRARLO###########");
				writer.println("############################################################");
				
				if(TheHouseOfCrimes.SUSPENSE_AMBIENTE)
					writer.println(TheHouseOfCrimes.getFecha() + ";" + "Suspense");
				else
					writer.println(TheHouseOfCrimes.getFecha() + ";" + "Neutro");
				
				writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		} finally{}
	}
	
	/**
	 * Añade líneas al fichero de log
	 * @param l
	 */
	public void escribirLinea(LineaLog l){
		lineas.add(l);
		
		try {
			writer = new PrintWriter(new FileWriter(archivoLog, true));
			writer.println(l.getSLineaLog());
			writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Permite subir el fichero de log a una cuenta de Dropbox
	 * @throws DbxException
	 * @throws IOException
	 */
	
	public void subirArchivo() throws DbxException, IOException{
        DbxRequestConfig config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
        
        String accessToken = "cGF10uurG7MAAAAAAAAFGGi0UJD9H-TpMKBgESMHV5kDy5jTeF7ou8It1HY8ZFC7";
        
        DbxClient client = new DbxClient(config, accessToken);
        System.out.println("Linked account: " + client.getAccountInfo().displayName);
 
        FileInputStream inputStream = new FileInputStream(archivoLog);
        try {
            DbxEntry.File uploadedFile = client.uploadFile(
            		"/ArchivoLog_ " + TheHouseOfCrimes.getUsuario() + "_" + TheHouseOfCrimes.getFecha(),
                DbxWriteMode.add(), archivoLog.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        } finally {
            inputStream.close();
        }
	}
}
