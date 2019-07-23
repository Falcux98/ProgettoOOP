package GestioneDati;

import java.io.*;  
import java.util.*;

//Classe che gestisce il parsing e la serializzazione dei dati

public class Parsing_Serializzazione_Dati 
{
	/** Metodo static che si occupa del Parsing dei dati a partire dal file letto.
	 * @return Vector di oggetti di tipo EuropeanInformationSociety*/

	public static Vector<EuropeanInformationSociety> getData()
	{
		/* 
		 * Il delimitatore usato è rappresentato da una virgola racchiusa tra virgolette
		 * così da non provocare errori 
		 */
	
		Vector<EuropeanInformationSociety> v = new Vector<EuropeanInformationSociety>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("dataset.csv")))			
		{
			String linea;
			int iterazione = 0;
			while ((linea =br.readLine()) != null)
			{
				if(iterazione==0)
				{
					iterazione++;
					continue;
				}
			
				String[] values = linea.split("\"\",\""); //"\"\",\""
				 
				System.out.println("La lunghezza dell'array e' "+values.length);
				
				for (int i =5 ; i<5; i++)
				{
					System.out.println("entra nel ciclo");
					values[i] = values[i].replace(',','.').replace("n.d.", "0");
					
				}
				
			v.add(new EuropeanInformationSociety(Integer.parseInt(values[0]), values[1], values[2], values[3], values[4], Double.parseDouble(values[5])));
				

			}
		
			br.close();
			}
		
	
		catch (IOException i)
		{
			i.printStackTrace();
		}
		for(EuropeanInformationSociety item: v)
		{
			System.out.println(v.toString());
		}
		return v;
	}
	
	/**
	 * Metodo static che si occupa della serializzazione dei dati dal Vector di oggetti di tipo EuropeanInformationSociety
	 * @param file	File su cui vengono serializzati gli oggetti
	 * @param data	Vector di EuropeanInformationSociety da dove vengono presi i dati
	 */

	
public static void SerializzazioneDati(File file, Vector<EuropeanInformationSociety> data)
{
	try 
	{
		ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		output.writeObject(data);
		output.close();
	}
	catch(IOException e)
	{
		System.out.println("Errore di I/O! ");
		e.printStackTrace();
	}
}
	
}
