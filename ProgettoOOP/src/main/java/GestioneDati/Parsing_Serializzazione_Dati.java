package GestioneDati;

import java.io.*;  
import java.util.*;
import GestioneDati.EuropeanInformationSociety;

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
		char COMMA_DELIMITER = ',';
		char APOSTR_DELIMITER = '"';
		Vector<EuropeanInformationSociety> v = new Vector<EuropeanInformationSociety>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("dataset.csv")))			
		{
			String linea;
			int iterazione, dim, j, sup;
			char[] stringaCar;
			EuropeanInformationSociety obj;
			String attributi = br.readLine();
			ArrayList<String> values; 
			while ((linea =br.readLine()) != null)
			{
				iterazione=0;
				values = new ArrayList<String>();
			while(iterazione <(linea.length()-1))
			{
				String vuoto = null;
				String supporto;
				dim=0; j=0; sup=0;
				if(linea.charAt(iterazione)== COMMA_DELIMITER || linea.charAt(iterazione)== APOSTR_DELIMITER)
				{
					iterazione++;
				}
			while((linea.charAt(iterazione)!= COMMA_DELIMITER && linea.charAt(iterazione)!= APOSTR_DELIMITER) && iterazione < (linea.length() -1))
			{
				iterazione++;
				dim++;
			}
			stringaCar= new char[dim];
			if(dim==0)
			{
				values.add(vuoto);
			}else
			{
				while(j<dim)
				{
					sup=(iterazione - dim)+j;
					stringaCar[j]= linea.charAt(sup);
					j++;
				}
				supporto= new String(stringaCar);
				values.add(supporto);
			}
			}
				
			 if(values.size() == 100) {  


				 for(int l = 0; l < 100;l++) 
				 {
					 if(values.get(l) == null )
					 {
						 values.set(l, "0");
					 }
				 }
			
			v.add(new EuropeanInformationSociety(Integer.parseInt(values.get(0)),values.get(1), values.get(2), values.get(3), values.get(4), Double.parseDouble(values.get(5))));
				

			}
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
