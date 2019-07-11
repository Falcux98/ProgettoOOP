package GestioneDati;

import java.io.*;
import java.util.*;
import java.lang.reflect.Field;

public class Parsing_Serializzazione_Metadati
{
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
