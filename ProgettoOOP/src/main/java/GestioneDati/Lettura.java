package GestioneDati;

import java.io.*;
import java.util.*;

//Classe che si occupa della lettura dei dati e dei metadati

public class Lettura 
{

	//il metodo static legge dal file in ingresso gli oggetti di tipo EuropeanInformationSociety
	
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
	
	


