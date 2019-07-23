package GestioneDati;

import java.io.*;   
import java.util.*;
import java.lang.reflect.Field;
import GestioneDati.*;

//classe che si occupa del parsing e della serializzazione dei metadati.

public class Parsing_Serializzazione_Metadati
{
	/**
	 * Metodo static che si occupa del Parsing dei metadati degli attributi della classe EuropeanInformationSociety.
	 * @return Vector di oggetti di tipo Metadata
	 */

	public static Vector<Metadata> getMetadata()
	{
		Vector<Metadata> v = new Vector<Metadata>();
		Class<?> c = EuropeanInformationSociety.class;
		Field[] attributi = c.getDeclaredFields();
		for (int i=0; i<attributi.length; i++)
		{
			Metadati annotation = attributi[i].getAnnotation(Metadati.class);
			v.add(new Metadata(annotation.name(), annotation.type()));
		}
		return v;
	}

	/**
	 * Metodo static che si occupa della serializzazione dei metadati dal Vector di oggetti di tipo Metadata.
	 * 
	 * @param file		File su cui vengono serializzati i metadati
	 * @param metadata	Vector di Metadata da dove vengono presi i metadati
	 */

	public static void SerializzazioneMetadati(File file, Vector<Metadata>metadata)
	{
		try 
		{
			ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			output.writeObject(metadata);
			output.close();
		}
		catch (IOException e)
		{
			System.out.println("Errore di I/O!");
			e.printStackTrace();
		}
	}
	
}
