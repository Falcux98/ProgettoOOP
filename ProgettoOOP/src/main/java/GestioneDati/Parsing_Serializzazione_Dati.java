package GestioneDati;

import java.io.*; 
import java.util.*;
import GestioneDati.EuropeanInformationSociety;
//Classe che gestisce il parsing e la serializzazione dei dati

public class Parsing_Serializzazione_Dati 
{
	public static Vector<EuropeanInformationSociety> getData()
	{
		String DELIMITER = "\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\"";
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
				String[] values = linea.split(DELIMITER);
				
				System.out.println("La lunghezza dell'array e' "+values.length);
				
				/*for (int i =6 ; i<6; i++)
				{
					values[i] = values[i].replace(',','.').replace("n.d.", "0");
				}*/
				
				
				 
				v.add(new EuropeanInformationSociety(Integer.parseInt(values[0]), values[1],values[2], values[3], values[4], Float.parseFloat(values[5])));
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
