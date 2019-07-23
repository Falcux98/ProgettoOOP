package Tool;

import java.io.*;  
import java.util.*;
import GestioneDati.*;

//Classe che si occupa della lettura dei dati e dei metadati

public class Lettura 
{

	/**il metodo static legge dal file in ingresso gli oggetti di tipo EuropeanInformationSociety
	 * @param file file da cui vengono letti gli oggetti di tipo EuropeanInformationSociety
	 * @return Vector di oggetti di tipo EuropeanInformationSociety
	 */
	
	public static Vector<EuropeanInformationSociety> LetturaDati(File file)
	{
		Vector<EuropeanInformationSociety> v=new Vector<EuropeanInformationSociety>();
		try 
			{
			ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			v= (Vector<EuropeanInformationSociety>) input.readObject();
			input.close();
			}
		catch(ClassNotFoundException e)
		{
			System.out.println("Lista non trovata");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("Errore di I/O");
			e.printStackTrace();
		}
	
		return v;
	}
	/**
	 * Metodo static che legge dal file preso come parametro in ingresso gli oggetti di tipo Metadata.
	 * 
	 * @param file	File da cui vengono letti gli oggetti di tipo Metadata
	 * 
	 * @return Vector di oggetti di tipo Metadata
	 */

	public static Vector<Metadata> LetturaMetadati(File file)
	{
		Vector<Metadata> v = new Vector<Metadata>();
		try
			{
				ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
				v= (Vector<Metadata>) input.readObject();
				input.close();
			}
		catch(ClassNotFoundException e)
		{
			System.out.println("Lista non trovata!");
			e.printStackTrace();
		}
		catch(IOException e)
		{
			System.out.println("Errore di I/O");
			e.printStackTrace();
		}
		return v;
		
	}
	
}
	
	


